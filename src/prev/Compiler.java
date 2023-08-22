package prev;

import java.util.*;

import org.antlr.v4.runtime.*;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.mem.*;
import prev.data.asm.*;
import prev.phase.lexan.*;
import prev.phase.synan.*;
import prev.phase.abstr.*;
import prev.phase.seman.*;
import prev.phase.memory.*;
import prev.phase.imcgen.*;
import prev.phase.imclin.*;
import prev.phase.asmgen.*;
import prev.phase.livean.*;
import prev.phase.regall.*;

import prev.data.asm.*;
import prev.data.semtype.*;
import prev.data.mem.*;
import prev.data.lin.*;
import java.io.*;

/**
 * The compiler.
 */
public class Compiler {

	// COMMAND LINE ARGUMENTS

	/** All valid phases of the compiler. */
	private static final String phases = "none|lexan|synan|abstr|seman|memory|imcgen|imclin|asmgen|livean|regall";

	/** Values of command line arguments. */
	private static HashMap<String, String> cmdLine = new HashMap<String, String>();

	public static int numOfRegs;

	/**
	 * Returns the value of a command line argument.
	 * 
	 * @param cmdLineArgName The name of the command line argument.
	 * @return The value of the specified command line argument or {@code null} if
	 *         the specified command line argument has not been used.
	 */
	public static String cmdLineArgValue(String cmdLineArgName) {
		return cmdLine.get(cmdLineArgName);
	}

	// THE COMPILER'S STARTUP METHOD

	/**
	 * The compiler's startup method.
	 * 
	 * @param args Command line arguments (see {@link prev.Compiler}).
	 */
	public static void main(String[] args) {
		try {
			Report.info("This is PREV'20 compiler:");

			// Scan the command line.
			for (int argc = 0; argc < args.length; argc++) {
				if (args[argc].startsWith("--")) {
					// Command-line switch.
					if (args[argc].matches("--src-file-name=.*")) {
						if (cmdLine.get("--src-file-name") == null) {
							cmdLine.put("--src-file-name", args[argc]);
							continue;
						}
					}
					if (args[argc].matches("--dst-file-name=.*")) {
						if (cmdLine.get("--dst-file-name") == null) {
							cmdLine.put("--dst-file-name", args[argc]);
							continue;
						}
					}
					if (args[argc].matches("--target-phase=(" + phases + "|all)")) {
						if (cmdLine.get("--target-phase") == null) {
							cmdLine.put("--target-phase", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--logged-phase=(" + phases + "|all)")) {
						if (cmdLine.get("--logged-phase") == null) {
							cmdLine.put("--logged-phase", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--xml=.*")) {
						if (cmdLine.get("--xml") == null) {
							cmdLine.put("--xml", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					if (args[argc].matches("--xsl=.*")) {
						if (cmdLine.get("--xsl") == null) {
							cmdLine.put("--xsl", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					//JST DODAL
					if (args[argc].matches("--num-regs=.*")) {
						if (cmdLine.get("--num-regs") == null) {
							cmdLine.put("--num-regs", args[argc].replaceFirst("^[^=]*=", ""));
							continue;
						}
					}
					Report.warning("Command line argument '" + args[argc] + "' ignored.");
				} else {
					// Source file name.
					if (cmdLine.get("--src-file-name") == null) {
						cmdLine.put("--src-file-name", args[argc]);
					} else {
						Report.warning("Source file '" + args[argc] + "' ignored.");
					}
				}
			}
			if (cmdLine.get("--src-file-name") == null) {
				throw new Report.Error("Source file not specified.");
			}
			if (cmdLine.get("--dst-file-name") == null) {
				cmdLine.put("--dst-file-name", cmdLine.get("--src-file-name").replaceFirst("\\.[^./]*$", "") + ".mmix");
			}
			if (cmdLine.get("--target-phase") == null) {
				cmdLine.put("--target-phase", phases.replaceFirst("^.*\\|", ""));
			}

			// Compilation process carried out phase by phase.
			while (true) {

				// Lexical analysis.
				if (Compiler.cmdLineArgValue("--target-phase").equals("lexan"))
					try (LexAn lexan = new LexAn()) {
						while (lexan.lexer.nextToken().getType() != Token.EOF) {
						}
						break;
					}

				// Syntax analysis.
				try (LexAn lexan = new LexAn(); SynAn synan = new SynAn(lexan)) {
					SynAn.tree = synan.parser.source();
					synan.log(SynAn.tree);
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("synan"))
					break;

				// Abstract syntax tree construction.
				try (Abstr abstr = new Abstr()) {
					Abstr.tree = SynAn.tree.ast;
					AstNode.lock();
					AbsLogger logger = new AbsLogger(abstr.logger);
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("abstr"))
					break;

				// Semantic analysis.
				try (SemAn seman = new SemAn()) {
					Abstr.tree.accept(new NameResolver(), null);
					Abstr.tree.accept(new TypeResolver(), null);
					Abstr.tree.accept(new AddrResolver(), null);
					SemAn.declaredAt.lock();
					SemAn.declaresType.lock();
					SemAn.isType.lock();
					SemAn.ofType.lock();
					SemAn.isAddr.lock();
					AbsLogger logger = new AbsLogger(seman.logger);
					logger.addSubvisitor(new SemLogger(seman.logger));
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("seman"))
					break;

				// Memory layout.
				try (Memory memory = new Memory()) {
					Abstr.tree.accept(new MemEvaluator(), null);
					Memory.frames.lock();
					Memory.accesses.lock();
					Memory.strings.lock();
					AbsLogger logger = new AbsLogger(memory.logger);
					logger.addSubvisitor(new SemLogger(memory.logger));
					logger.addSubvisitor(new MemLogger(memory.logger));
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("memory"))
					break;

				// Intermediate code generation.
				try (ImcGen imcgen = new ImcGen()) {
					Abstr.tree.accept(new CodeGenerator(), null);
					ImcGen.exprImc.lock();
					ImcGen.stmtImc.lock();
					AbsLogger logger = new AbsLogger(imcgen.logger);
					logger.addSubvisitor(new SemLogger(imcgen.logger));
					logger.addSubvisitor(new MemLogger(imcgen.logger));
					logger.addSubvisitor(new ImcLogger(imcgen.logger));
					Abstr.tree.accept(logger, "Decls");
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("imcgen"))
					break;

				// Linearization of intermediate code.
				try (ImcLin imclin = new ImcLin()) {
					Abstr.tree.accept(new ChunkGenerator(), null);
					imclin.log();

					Interpreter interpreter = new Interpreter(ImcLin.dataChunks(), ImcLin.codeChunks());
					System.out.println("EXIT CODE: " + interpreter.run("_main"));
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("imclin"))
					break;
				
				// Machine code generation.
				try (AsmGen asmgen = new AsmGen()) {
					
					numOfRegs = Integer.parseInt(Compiler.cmdLineArgValue("--num-regs"));
					asmgen.genAsmCodes();
					asmgen.log();
					
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("acmgen"))
					break;

				// Liveness analysis.
				try (LiveAn livean = new LiveAn()) {
					livean.analysis();
					livean.log();
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("livean"))
					break;

				// Register allocation.
				try (RegAll regall = new RegAll()) {
					//System.out.println("TEST1");
					int noOfRegs = Integer.parseInt(Compiler.cmdLineArgValue("--num-regs"));
					regall.allocate(noOfRegs);
					regall.log();
					//System.out.println("TEST1");
					lastPhase(regall);
					/*addPrologue();
					addEpilogue();
					testIzpis(regall);*/
				}
				if (Compiler.cmdLineArgValue("--target-phase").equals("regall"))
					break;
				
				break;
			}

			Report.info("Done.");
		} catch (Report.Error __) {
			System.exit(1);
		}
	}
	/**
	 * ADDING A PROLOGUE OF THE FUNCTIONS
	 * 1. we create a function Call by:
	 * 	- [SP-offset oldFP] <- FP
	 * 	- FP <- SP
	 * 	- SP <- SP - func Call size
	 * 2. save retrun address (from register rJ)
	 * 	- [FP - offset RA] <- rJ (rJ is register 4, offset RA is -locsSize-sizeofOldFP(which is 8))
	 * 3. jump in function ...
	 */
	public static Vector<AsmInstr> constantIntoRegister(MemTemp dstRegister, long constant) {
		Vector<AsmInstr> loadingConstant = new Vector<>();
		Vector<MemTemp>uses = new Vector<>();
		Vector<MemTemp>defs = new Vector<>();
		defs.add(dstRegister);
		long tmpConstant = constant;
		constant=Math.abs(constant);
		short value = (short) (constant & 0xFFFF);
		loadingConstant.add(new AsmOPER("SETL `d0,"+value, null, defs, null));
		uses.add(dstRegister);
		constant = constant >> 16;
		value = (short) (constant & 0xFFFF);
		if(value > 0) {
			AsmOPER incml = new AsmOPER("INCML `d0,"+value, uses, defs, null);
			loadingConstant.add(incml);
		}
		constant = constant >> 16;
		value = (short) (constant & 0xFFFF);
		if(value > 0) {
			AsmOPER incmh = new AsmOPER("INCMH `d0,"+value, uses, defs, null);
			loadingConstant.add(incmh);
		}
		constant = constant >> 16;
		value = (short) (constant & 0xFFFF);
		if(value > 0) {
			AsmOPER inch = new AsmOPER("INCH `d0,"+value, uses, defs, null);
			loadingConstant.add(inch);
			//constValue = constValue >> 16;
		}
		if(tmpConstant<0) {
			loadingConstant.add(new AsmOPER("NEG `d0,`s0",uses,defs,null));
		}
		return loadingConstant;
	}
	public static void addPrologue(Code code,RegAll regall) {
		/*//for(Code code : AsmGen.codes) {
		Vector<AsmInstr> newInstructions = new Vector<>();
		newInstructions.add(new AsmLABEL(code.frame.label));
		//newInstructions.add(new AsmOPER("% start prlogue",null,null,null));
		MemTemp temp = new MemTemp();
		regall.tempToReg.put(temp,0);
		// 1. :
		newInstructions.addAll(constantIntoRegister(temp,code.frame.locsSize+8));
		AsmOPER subOffsetFromSP = new AsmOPER("SUB $0,SP,$0",null,null,null	);
		newInstructions.add(subOffsetFromSP);
		AsmOPER storeFP = new AsmOPER("STO FP,$0,0", null, null, null);
		newInstructions.add(storeFP);
		
		newInstructions.add(new AsmOPER("SUB $0,$0,8", null, null, null));
		newInstructions.add(new AsmOPER("GET $1,rJ",null,null,null));
		AsmOPER storeRV = new AsmOPER("STO $1,$0,0", null,null,null);
		newInstructions.add(storeRV);

		long funcCallSize = (code.frame.size+code.tempSize); 
		AsmOPER setFP = new AsmOPER("SET FP,SP", null, null, null);
		newInstructions.add(setFP);
		newInstructions.addAll(constantIntoRegister(temp,funcCallSize));
		AsmOPER setSP = new AsmOPER("SUB SP,SP,$0",null,null,null);
		newInstructions.add(setSP);
		// 2. :
		//long returnAddressOffset = (code.frame.locsSize-8); // -8 is for old FP
		//newInstructions.addAll(constantIntoRegister(0,returnAddressOffset));
		
		// 3. :
		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(code.entryLabel);
		AsmOPER jumpToFunc = new AsmOPER("JMP "+code.entryLabel.name, null, null, jumps);
		newInstructions.add(jumpToFunc);
		//newInstructions.add(new AsmOPER("% end prlogue",null,null,null));

		// now we add instructions to the begining (position 0) of the code instructions in the reverse order, to get correct order
		code.instrs.addAll(0,newInstructions);
		//}*/

		Vector<AsmInstr> newInstructions = new Vector<>();
		newInstructions.add(new AsmLABEL(code.frame.label));
		//newInstructions.add(new AsmOPER("% start prlogue",null,null,null));
		MemTemp temp = new MemTemp();
		regall.tempToReg.put(temp,0);
		// 1. :
		newInstructions.addAll(constantIntoRegister(temp,code.frame.locsSize+8));
		AsmOPER subOffsetFromSP = new AsmOPER("SUB $0,SP,$0",null,null,null	);
		newInstructions.add(subOffsetFromSP);
		AsmOPER storeFP = new AsmOPER("STO FP,$0,0", null, null, null);
		newInstructions.add(storeFP);
		
		
		AsmOPER setFP = new AsmOPER("SET FP,SP", null, null, null);
		newInstructions.add(setFP);
		long funcCallSize = (code.frame.size+code.tempSize); 
		newInstructions.addAll(constantIntoRegister(temp,funcCallSize));
		AsmOPER setSP = new AsmOPER("SUB SP,SP,$0",null,null,null);
		newInstructions.add(setSP);
		newInstructions.addAll(constantIntoRegister(temp,code.frame.locsSize+16));
		newInstructions.add(new AsmOPER("SUB $1,FP,$0", null, null, null));
		newInstructions.add(new AsmOPER("GET $0,rJ",null,null,null));
		AsmOPER storeRV = new AsmOPER("STO $0,$1,0", null,null,null);
		newInstructions.add(storeRV);

		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(code.entryLabel);
		AsmOPER jumpToFunc = new AsmOPER("JMP "+code.entryLabel.name, null, null, jumps);
		newInstructions.add(jumpToFunc);
		code.instrs.addAll(0,newInstructions);
	}
	/**
	 * ADDING AN EPILOGUE OF THE FUNCTIONS
	 * 1. store result
	 * 	- [FP] <- RV
	 * 2. get back return address
	 * 	- rJ <- [FP - offset RA]
	 * 3. decompose fuction call
	 * 	- SP <- SP + func call size
	 * 	- FP <- [SP-offset oldFP]
	 * 4. POP registers
	 */
	public static void addEpilogue(Code code, RegAll regall) {
		/*
		//for(Code code : AsmGen.codes) {
		Vector<AsmInstr> newInstructions = new Vector<>();
		//newInstructions.addAll(code.instrs);
		newInstructions.add(new AsmLABEL(code.exitLabel));
		//newInstructions.add(new AsmOPER("% start epilogue",null,null,null));
		// 1. : 
		Vector<MemTemp> uses = new Vector<>();
		uses.add(code.frame.RV);
		//int rv = regall.tempToReg.get(code.frame.RV);
		newInstructions.add(new AsmOPER("STO `s0,FP,0",uses,null,null));
		AsmOPER setSP = new AsmOPER("SET SP,FP", null, null, null);
		newInstructions.add(setSP);
		// 2. :
		MemTemp temp = new MemTemp();
		regall.tempToReg.put(temp,0);
		long oldFPOffset = code.frame.locsSize+8;//(-code.frame.locsSize-1)*8;
		newInstructions.addAll(constantIntoRegister(temp,oldFPOffset));
		newInstructions.add(new AsmOPER("SUB $0,SP,$0", null, null,null));
		AsmOPER loadInFPreg = new AsmOPER("LDO FP,$0,0", null, null, null);
		newInstructions.add(loadInFPreg);

		//newInstructions.addAll(constantIntoRegister(temp,returnAddressOffset));
		newInstructions.add(new AsmOPER("SUB $0,$0,8", null, null,null));
		AsmOPER loadRJ = new AsmOPER("LDO $0,$0,0",null,null,null);
		newInstructions.add(loadRJ);
		AsmOPER putInRj = new AsmOPER("PUT rJ,$0",null,null,null);
		newInstructions.add(putInRj);
		// 3. :
	
		//newInstructions.addAll(constantIntoRegister(temp,returnAddressOffset-8));
		
		// 4. :
		AsmOPER pop = new AsmOPER("POP "+numOfRegs+",0", null, null, null);
		newInstructions.add(pop);
		// now we add them at the end of the code in the right order
		//code.instrs.add(); // for the first instruction that is currently missing TODO
		//code.instrs.add(loadRJ);
		//code.instrs.add(setSP);
		//code.instrs.add(loadInFPreg);
		//code.instrs.add(pop);
		
		code.instrs.addAll(newInstructions);*/
		//for(Code code : AsmGen.codes) {
			Vector<AsmInstr> newInstructions = new Vector<>();
			//newInstructions.addAll(code.instrs);
			newInstructions.add(new AsmLABEL(code.exitLabel));
			//newInstructions.add(new AsmOPER("% start epilogue",null,null,null));
			// 1. : 
			Vector<MemTemp> uses = new Vector<>();
			uses.add(code.frame.RV);
			//int rv = regall.tempToReg.get(code.frame.RV);
			newInstructions.add(new AsmOPER("STO `s0,FP,0",uses,null,null));
			//AsmOPER setSP = new AsmOPER("SET SP,FP", null, null, null);
			//newInstructions.add(setSP);
			// 2. :
			MemTemp temp2 = new MemTemp();
			regall.tempToReg.put(temp2,1);
			long offsetRA = code.frame.locsSize+2*8;
			newInstructions.addAll(constantIntoRegister(temp2,offsetRA));
			newInstructions.add(new AsmOPER("SUB $1,FP,$1", null, null, null)); 
			AsmOPER loadRJ = new AsmOPER("LDO $0,$1,0",null,null,null);
			newInstructions.add(loadRJ);
			AsmOPER putInRj = new AsmOPER("PUT rJ,$0",null,null,null);
			newInstructions.add(putInRj);
			long sizeOfStack = code.frame.size+code.tempSize;
			MemTemp temp = new MemTemp();
			regall.tempToReg.put(temp,0);
			//long oldFPOffset = code.frame.locsSize+8;//(-code.frame.locsSize-1)*8;
			newInstructions.addAll(constantIntoRegister(temp,sizeOfStack));
			newInstructions.add(new AsmOPER("ADD SP,SP,$0", null, null,null));
			long sizeOfPointer =new SemPointer(new SemVoid()).size();
			AsmOPER loadInFPreg = new AsmOPER("LDO FP,$1,"+sizeOfPointer, null, null, null);
			newInstructions.add(loadInFPreg);
	
			//newInstructions.addAll(constantIntoRegister(temp,returnAddressOffset));
			//newInstructions.add(new AsmOPER("SUB $0,$0,8", null, null,null));
			
			// 3. :
		
			//newInstructions.addAll(constantIntoRegister(temp,returnAddressOffset-8));
			
			// 4. :
			AsmOPER pop = new AsmOPER("POP "+numOfRegs+",0", null, null, null);
			newInstructions.add(pop);
			// now we add them at the end of the code in the right order
			//code.instrs.add(); // for the first instruction that is currently missing TODO
			//code.instrs.add(loadRJ);
			//code.instrs.add(setSP);
			//code.instrs.add(loadInFPreg);
			//code.instrs.add(pop);
			
			code.instrs.addAll(newInstructions);
		//}
	}

	public static Vector<String> addPutChar(RegAll regall) {
		Vector<AsmInstr> instruction = new Vector<>();
		//we have to create new Code - for that we also need frame
		MemFrame funFrame = new MemFrame(new MemLabel("putChar"),0,0,0);
		MemLabel entryLabel = new MemLabel();
		//System.out.println(entryLabel.name);
		MemLabel exitLabel = new MemLabel();
		
		AsmLABEL tmp = new AsmLABEL(entryLabel);
		//System.out.println(tmp.toString());
		//System.out.println(code.entryLabel.name);
		instruction.add(tmp);
		long offset = 8;
		instruction.add(new AsmOPER("LDO $0,FP,"+offset, null,null,null));
		/*instruction.add(new AsmOPER("SETL $1,0", null,null,null)); //setting the bits above the char to 0*/
		AsmOPER storeChar = new AsmOPER("STB $0,FP,"+offset, null,null,null);
		instruction.add(storeChar);

		AsmOPER getCharAddr = new AsmOPER("ADD $255,FP,15", null,null,null);
		instruction.add(getCharAddr);
		AsmOPER putChar = new AsmOPER("TRAP 0,Fputs,StdOut", null,null,null);
		instruction.add(putChar);
		instruction.add(new AsmOPER("STO $0,FP,"+offset, null,null,null));
		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(exitLabel);
		instruction.add(new AsmOPER("JMP "+exitLabel.name,null,null,jumps));
		Code code = new Code(funFrame,entryLabel,exitLabel,instruction);
		regall.tempToReg.put(code.frame.RV, 255); 
		//add prologue and epilogue
		
		addPrologue(code,regall);
		addEpilogue(code,regall);
		//add return
		return strigifyCode(code, regall);
	}

	public static Vector<String> addGetChar(RegAll regall) {
		Vector<AsmInstr> instruction = new Vector<>();
		//we have to create new Code - for that we also need frame
		MemFrame funFrame = new MemFrame(new MemLabel("getChar"),0,0,0);
		MemLabel entryLabel = new MemLabel();
		MemLabel exitLabel = new MemLabel();
		//Code code = new Code(funFrame,entryLabel,exitLabel,instruction);
		instruction.add(new AsmLABEL(entryLabel));

		//we load address of buffer pointer in one register, value of buffer pointer in another, we compare than and 
		//branch if value is less thenaddress 
		AsmOPER bufferAddr = new AsmOPER("LDA\t$1,BufferPosition",null,null,null);
		AsmOPER bufferValue = new AsmOPER("LDO\t$0,BufferPosition",null,null,null);
		instruction.add(bufferAddr);
		instruction.add(bufferValue);

		AsmOPER compare = new AsmOPER("CMP\t$2,$0,$1",null,null,null);
		AsmOPER zeroOrSetIfNegative = new AsmOPER("ZSN\t$2,$2,1", null,null,null);
		instruction.add(compare);
		instruction.add(zeroOrSetIfNegative);
		MemLabel posLabel = new MemLabel();
		AsmOPER branchIfPos = new AsmOPER("BP\t$2,"+posLabel.name,null,null,null);
		instruction.add(branchIfPos);
		//if we didnt brach
		instruction.add(new AsmOPER("LDA\t$255,InputArgs",null,null,null));
		instruction.add(new AsmOPER("TRAP\t0,Fgets,StdIn", null,null,null)); //we read from standard input into buffer 
		instruction.add(new AsmOPER("LDA\t$0,InputBuffer",null,null,null));
		instruction.add(new AsmOPER("LDA\t$1,BufferPosition",null,null,null));
		instruction.add(new AsmOPER("STO\t$0,$1,0",null,null,null));
		//the branch part
		instruction.add(new AsmLABEL(posLabel));
		instruction.add(new AsmOPER("LDO\t$0,BufferPosition",null,null,null)); 
		instruction.add(new AsmOPER("LDB\t$255,$0,0",null,null,null)); //load the byte from buffer position
		instruction.add(new AsmOPER("SETL\t$1,0",null,null,null));
		instruction.add(new AsmOPER("STB\t$1,$0",null,null,null));
		//we have to change buffer position for 1 byte
		instruction.add(new AsmOPER("ADD\t$0,$0,1",null,null,null));
		instruction.add(new AsmOPER("LDA\t$1,BufferPosition",null,null,null));
		instruction.add(new AsmOPER("STO\t$0,$1,0",null,null,null));

		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(exitLabel);
		instruction.add(new AsmOPER("JMP "+exitLabel.name,null,null,jumps));
		Code code = new Code(funFrame,entryLabel,exitLabel,instruction);
		regall.tempToReg.put(code.frame.RV, 255); 
		//add prologue and epilogue
		
		addPrologue(code,regall);
		addEpilogue(code,regall);
		//add return
		return strigifyCode(code, regall);

		
		/*instruction.add(new AsmLABEL(entryLabel));

		instruction.add(new AsmOPER("LDA\t$255,ArgGet",null,null,null));
		instruction.add(new AsmOPER("TRAP\t0,Fgets,StdIn", null,null,null)); //we read from standard input into buffer 
		instruction.add(new AsmOPER("LDA\t$0,Buffer",null,null,null));
		instruction.add(new AsmOPER("LDB\t$0,$0,0",null,null,null));

		Vector<MemLabel> jumps = new Vector<>();
		jumps.add(exitLabel);
		instruction.add(new AsmOPER("JMP "+exitLabel.name,null,null,jumps));
		Code code = new Code(funFrame,entryLabel,exitLabel,instruction);
		regall.tempToReg.put(code.frame.RV, 255); 
		//add prologue and epilogue
		
		addPrologue(code,regall);
		addEpilogue(code,regall);
		//add return
		return strigifyCode(code, regall);*/

	}

	public static Vector<String> addStdLib() {
		Vector<String> instructions = new Vector<>();
		//new "function" -> we put the object on heap (Heap pointer(HP) shows us the top of heap) and then we have to increase the HP
		instructions.add("_new\tLDO\t$0,SP,8");
		instructions.add("\tSTO\tHP,SP,0");
		instructions.add("\tADD\tHP,HP,$0");
		instructions.add("\tPOP\t"+numOfRegs+",0\n");

		//del "function" -> does nothing but we still need to POP
		instructions.add("_del\tPOP\t"+numOfRegs+",0\n");

		//exit
		instructions.add("_exit\tSET\t$255,1");
		instructions.add("\tTRAP\t0,Halt,0");
		
		return instructions;
	}

	public static Vector<String> addBootstrapRoutine() {
		Vector<String> instructions = new Vector<>();
		/**
		 * SP(stack pointer) -> register $254
		 * FP(frame pointer) -> register $253
		 * HP(heap pointer) -> register $252
		 */
		instructions.add("SP\tGREG\t#4000000000000000");
		instructions.add("FP\tGREG\t0\n");
		//static data segment is needed here
		instructions.add("\tLOC\tData_Segment");
		/*instructions.add("Buffer OCTA 0");
		instructions.add("BufferTwo OCTA 0");
		instructions.add("ArgGet OCTA Buffer,2");*/
		//base address (for variable addressing)
		instructions.add("\tGREG\t@");
		//this is for reading text
		instructions.add("InputSize\tIS\t64");
		instructions.add("InputBuffer\tOCTA\t0");
		instructions.add("\tLOC\tInputBuffer+InputSize");
		instructions.add("BufferPosition\tOCTA\t@");
		instructions.add("InputArgs\tOCTA\tInputBuffer,InputSize\n");

		//instructions.add("\tLOC Data_Segment");
		
		//instructions.add("ArgPut OCTA Buffer,2");

		for(LinDataChunk dataChunk : ImcLin.dataChunks()) {
			if(dataChunk.init == null) {
				String sizeOfData = dataChunk.label.name+"\tOCTA\t";
				for(int i = 0; i <  dataChunk.size;i = i+8) {
					if(i > 0) {
						sizeOfData += ",";
					}
					sizeOfData += "0";
				}
				instructions.add(sizeOfData);
			} else {
				//it is a string
				instructions.add(dataChunk.label.name+"\tOCTA\t"+dataChunk.init+",0");
			}
		}

		//after we deal with static(global) variables we initiate heap to current locationt @
		instructions.add("HP\tGREG\t@\n");

		//program instructions should start after #100
		instructions.add("\tLOC\t#100\n");
		//we have to add main label that will run first (and it will call our main (which is labeled _main))
		instructions.add("Main\tPUSHJ\t"+Compiler.cmdLineArgValue("--num-regs")+",_main");
		//we have to return the result from main -> put it into $255 register
		instructions.add("\tLDO\t$255,$254,0");
		//finally exit instructions
		instructions.add("\tTRAP\t0,Halt,0\n");

		return instructions;
	}

	public static Vector<String> strigifyCode(Code code, RegAll regall) {
		Vector<String> instructions = new Vector<>();

		AsmLABEL label = null;
		for(AsmInstr i : code.instrs) {
			if(i instanceof AsmLABEL) {
				if(label != null) {
					instructions.add(label.toString(regall.tempToReg)+"\tADD $1,$1,0");
				}
				label = (AsmLABEL)i;
			} else {
				if(label == null) {
					instructions.add("\t"+i.toString(regall.tempToReg));
				} else {
					instructions.add(label.toString(regall.tempToReg)+"\t"+i.toString(regall.tempToReg));
					label = null;
				}
			}
		}
		return instructions;
	}

	public static Vector<String> generateFinalCode(RegAll regall) {
		Vector<String> instructions = new Vector<>();

		for(Code code : AsmGen.codes) {
			if(code.frame.label.name.equals("_putChar") || code.frame.label.name.equals("_getChar") || code.frame.label.name.equals("_exit")) {
				continue; //we skip them as we have added mmix implementation
			} else {
				
				addPrologue(code,regall);
				addEpilogue(code,regall);

				instructions.addAll(strigifyCode(code,regall));
			}
		}

		return instructions;
	}

	public static void lastPhase(RegAll regall) {
		try {
			File myFile = new File(Compiler.cmdLineArgValue("--dst-file-name"));
			BufferedWriter bw = new BufferedWriter(new FileWriter(myFile));

			Vector<String> bootstrap = addBootstrapRoutine();
			for(String i : bootstrap) {
				bw.write(i+"\n");
			}
			Vector<String> stdLib = addStdLib();
			for(String i : stdLib) {
				bw.write(i+"\n");
			}
			Vector<String> putChar = addPutChar(regall);
			for(String i : putChar) {
				bw.write(i+"\n");
			}
			Vector<String> getChar = addGetChar(regall);
			for(String i : getChar) {
				bw.write(i+"\n");
			}
			Vector<String> allCode = generateFinalCode(regall);
			for(String i : allCode) {
				bw.write(i+"\n");
			}
			bw.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void testIzpis(RegAll regall) {
		for(Code code : AsmGen.codes) {
			for(AsmInstr i : code.instrs) {
				System.out.println(i.toString(regall.tempToReg));
			}
			System.out.println();
		}
	}
}
