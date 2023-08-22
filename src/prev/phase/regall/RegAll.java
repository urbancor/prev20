package prev.phase.regall;

import java.util.*;

import prev.data.mem.*;
import prev.data.asm.*;
import prev.*;
import prev.phase.*;
import prev.phase.asmgen.*;
import prev.phase.regall.*;
import prev.phase.livean.*;

/**
 * Register allocation.
 */
public class RegAll extends Phase {
	
	/** Mapping of temporary variables to registers. */
	public final HashMap<MemTemp, Integer> tempToReg = new HashMap<MemTemp, Integer>();
	private Stack<Node> stack = new Stack<>();
	private static int R;
	public RegAll() {
		super("regall");
	}

	public void mapTempsToRegs(InterferenceGraph ig, Code code) {
		for(Node n : ig.nodes().values()) {
			tempToReg.put(n.getTemp(),n.getColor());
		}
		tempToReg.put(code.frame.FP,253);
	}

	public void testIzpis(HashSet<Node> hs) {
		Iterator<Node> i = hs.iterator();
		System.out.print("NODES: ");
		while(i.hasNext()) {
			System.out.print(i.next().getTemp().toString()+", ");
		}
		System.out.println();
	}

	public InterferenceGraph buildPhase(Code code) {

		InterferenceGraph interferenceGraph = new InterferenceGraph();

		for(AsmInstr instr : code.instrs) {
			HashSet<MemTemp> liveTemps = new HashSet<MemTemp>();

			liveTemps.addAll(new HashSet<MemTemp>(instr.uses()));
			liveTemps.addAll(new HashSet<MemTemp>(instr.defs()));
			liveTemps.addAll(instr.in());
			liveTemps.addAll(instr.out());
			liveTemps.remove(code.frame.FP); //FP ne rabimo barvat, ker je v registru $253
			HashSet<Node> liveNodes = new HashSet<>();
			for(MemTemp temp : liveTemps) {
				if(interferenceGraph.getNode(temp) == null) {
					Node newNode = new Node(temp);
					interferenceGraph.addNode(temp, newNode);
					liveNodes.add(newNode);
				} else {
					liveNodes.add(interferenceGraph.getNode(temp));
				}
			}
			Iterator<Node> i = liveNodes.iterator();
			while(i.hasNext()){

				Node node = i.next();

				HashSet<Node> tmpSet = new HashSet<>();
				tmpSet.addAll(liveNodes);
				tmpSet.remove(node);
				node.addNeighbours(tmpSet);
				//testIzpis(liveNodes);
			}
		}
		return interferenceGraph;
	}
	public Node checkForNodeWithLessThenR(InterferenceGraph interferenceGraph) {
		
		for(Node i : interferenceGraph.nodes().values()) {
			if(i.noOfNeighbours() < R) {
				return i;
			}
		}
		return null;
	}
	public void simplifyPhase(InterferenceGraph interferenceGraph) {
		Node n = checkForNodeWithLessThenR(interferenceGraph);
		while(n != null) {

			for(Node i : n.neighbours()) {
				i.neighbours().remove(n);
			}
			stack.push(n);
			interferenceGraph.remove(n.getTemp());
			n = checkForNodeWithLessThenR(interferenceGraph);
		}
	}

	public void spillPhase(InterferenceGraph interferenceGraph) {
		for(Node n : interferenceGraph.nodes().values()) {
			for(Node i : n.neighbours()) {
				i.neighbours().remove(n);
			}
			n.setPotentialSpill();
			stack.push(n);
			interferenceGraph.remove(n.getTemp());
			return;
		}
	}

	public boolean colorNode(Node n) {
		int color = 0;
		while(color < R) {
			boolean canColor = true;
			for(Node i : n.neighbours()) {
				if(i.getColor() == color) {
					canColor = false;
					break;
				}
			}
			if(canColor) {
				n.setColor(color);
				break;
			}
			color++;
		}
		if(color == R) {
			n.setActualSpill();
			return false;

		} else {
			return true;
		}
	}
	static boolean spilled = false;
	public Code selectPhase(Code code, InterferenceGraph interferenceGraph) {
		//int stackSize = stack.size();
		//InterferenceGraph interferenceGraph = new InterferenceGraph();
		spilled = false;
		while(!stack.isEmpty()) {
			Node n = (Node) stack.pop();

			interferenceGraph.addNode(n.getTemp(), n);
			HashSet<Node> tmp = new HashSet<>();
			tmp.add(n);
			for(Node i : n.neighbours()) {
				i.addNeighbours(tmp);
			}
			//n.addNeighbours(neighbours);
			if(colorNode(n)) {
				//it's ok we can continue with adding the next node
			} else {
				//we have to make an actual spill
				/*System.out.println("ACTUAL SPILL");
				System.out.println(n.getTemp().toString());*/
				n.setActualSpill();
				spilled = true;
				//break;
			}

		}
		if(spilled) {
			code = makeSpill(interferenceGraph, code);
		}
		return code;
	}

	/***public Code makeSpill(InterferenceGraph interferenceGraph, Code code) {
		Vector<AsmInstr> instrs = code.instrs;
		Vector<AsmInstr> newInstrs = new Vector<AsmInstr>();

		for(AsmInstr instr : instrs) {
			newInstrs.add(instr);
		}

		long offset = -code.frame.locsSize-24-code.tempSize;
		long noOfTemps = 0;

		for(Node n : interferenceGraph.nodes().values()) {
			int size = newInstrs.size();
			if(n.isActualSpill()) {
				System.out.print(n.noOfNeighbours()+" zamenjal bomo: "+n.getTemp().toString()+", ");
				for(int k = 0; k < size; k++){
					
					AsmInstr j = newInstrs.get(k);
					AsmOPER instr = (AsmOPER) j;
					
					if(instr.defs().contains(n.getTemp())) {
						//System.out.print("Ukaz: "+instr.toString()+", ");
						MemTemp tmpTemp = new MemTemp();
						//System.out.print("defs: "+tmpTemp.toString()+", ");
						//System.out.print("DEFS, ");
						if(!n.offsetWasSet()) {
							n.setOffset(offset-noOfTemps*8);
							noOfTemps++;
						}
						MemTemp setReg = new MemTemp();
						Vector<MemTemp> defsForSet = new Vector<>();
						defsForSet.add(setReg);
						//AsmOPER subForOffset = new AsmOPER("SUB `d0,0,"+Math.abs(n.getOffset()),null,defsForSet,null);
						//AsmOPER setRegOp = new AsmOPER("SET `d0,"+Math.abs(n.getOffset()),null,defsForSet,null);
						Vector<AsmInstr> setConstant = constantIntoRegister(setReg,n.getOffset());
						//MemTemp tmpTemp = new MemTemp(); // memtemp that will replace node.temp in instruction
						//instr.defs().remove(n.getTemp());
						Vector<MemTemp> defsForStore = new Vector<>();
						defsForStore.add(tmpTemp);
						defsForStore.add(setReg);
						Vector<MemTemp> newDefs = new Vector<>();
						newDefs.addAll(instr.defs());
						
						int indexOfOldTemp = newDefs.indexOf(n.getTemp());
						
						newDefs.set(indexOfOldTemp,tmpTemp);
						
						AsmOPER storeInstr = new AsmOPER("STO `s0,$253,`s1",defsForStore,null,null);
						AsmOPER newInstr = new AsmOPER(instr.instr(),instr.uses(), newDefs,instr.jumps());
						int indexOfInstr = newInstrs.indexOf(instr);
						newInstrs.set(indexOfInstr, newInstr);
						newInstrs.addAll(indexOfInstr+1,setConstant);
						newInstrs.add(indexOfInstr+2, storeInstr);
						instr = newInstr;
						//k+=2;
						size=size+1+setConstant.size();
					} 
					/*if(instr.uses().contains(n.getTemp())) {
						System.out.print("USES: ");
						//System.out.println(instr.toString());
						//n.setOffset(offset);
						MemTemp setReg = new MemTemp();
						Vector<MemTemp> defsForSet = new Vector<>();
						defsForSet.add(setReg);
						AsmOPER setRegOp = new AsmOPER("SET `d0,"+n.getOffset(),null,defsForSet,null);
						
						Vector<MemTemp> loadDefs = new Vector<>();
						loadDefs.add(tmpTemp);
						AsmOPER loadInstr = new AsmOPER("LDO `d0,$253,`s0",defsForSet,loadDefs,null);
						Vector<MemTemp> newUses = new Vector<>();
						newUses.addAll(instr.uses());
						//Collections.replaceAll(newUses,n.getTemp(),tmpTemp);
						int indexOfOldTemp = newUses.indexOf(n.getTemp());
						newUses.set(indexOfOldTemp,tmpTemp);
						AsmOPER newInstr = new AsmOPER(instr.instr(),newUses, instr.defs(), instr.jumps());
						int indexOfInstr = newInstrs.indexOf(instr);
						newInstrs.set(indexOfInstr, newInstr);
						newInstrs.add(indexOfInstr, loadInstr);
						newInstrs.add(indexOfInstr, setRegOp);
						//k+=2;
						size+=2;
					}*/
					
				/***}
				size = newInstrs.size();
				for(int k = 0; k < size; k++){
					
					AsmInstr j = newInstrs.get(k);
					//System.out.println(j.toString());
					AsmOPER instr = (AsmOPER) j;
					
					if(instr.uses().contains(n.getTemp())) {
						//System.out.print("Ukaz: "+instr.toString()+", ");
						MemTemp tmpTemp = new MemTemp();
						//System.out.print("uses: "+tmpTemp.toString()+", ");
						//System.out.print("USES: ");
						//System.out.println(instr.toString());
						//n.setOffset(offset);
						if(!n.offsetWasSet()) {
							n.setOffset(offset-noOfTemps*8);
							noOfTemps++;
						}
						MemTemp setReg = new MemTemp();
						Vector<MemTemp> defsForSet = new Vector<>();
						defsForSet.add(setReg);
						//AsmOPER subForOffset = new AsmOPER("SUB `d0,0,"+Math.abs(n.getOffset()),null,defsForSet,null);
						//AsmOPER setRegOp = new AsmOPER("SET `d0,"+Math.abs(n.getOffset()),null,defsForSet,null);
						/*AsmOPER negation = null;
						if(n.getOffset()<0) {
							negation = new AsmOPER("NEG `d0,`s0",defsForSet,defsForSet, null);
						}*/
						/***Vector<AsmInstr> setConstant = constantIntoRegister(setReg,n.getOffset());
						Vector<MemTemp> loadDefs = new Vector<>();
						loadDefs.add(tmpTemp);
						AsmOPER loadInstr = new AsmOPER("LDO `d0,$253,`s0",defsForSet,loadDefs,null);
						Vector<MemTemp> newUses = new Vector<>();
						newUses.addAll(instr.uses());
						//Collections.replaceAll(newUses,n.getTemp(),tmpTemp);
						int indexOfOldTemp = newUses.indexOf(n.getTemp());
						newUses.set(indexOfOldTemp,tmpTemp);
						AsmOPER newInstr = new AsmOPER(instr.instr(),newUses, instr.defs(), instr.jumps());
						int indexOfInstr = newInstrs.indexOf(instr);
						newInstrs.set(indexOfInstr, newInstr);
						
						/*if(negation != null) {
							newInstr.add(indexOfInstr,negation);
						}*/
						
						/***newInstrs.add(indexOfInstr, loadInstr);
						newInstrs.addAll(indexOfInstr, setConstant);
						//k+=2;
						size=size+1+setConstant.size();
					}
					
				}
				//System.out.println();
				break;
			} 
			
		}

		Code newCode = new Code(code.frame, code.entryLabel, code.exitLabel, newInstrs);
		newCode.tempSize = code.tempSize+noOfTemps*8;
		return newCode;
	}*/

	public Code makeSpill(InterferenceGraph interferenceGraph, Code code) {
		Vector<AsmInstr> instrs = code.instrs;
		Vector<AsmInstr> newInstrs = new Vector<AsmInstr>();

		for(AsmInstr instr : instrs) {
			newInstrs.add(instr);
		}

		long offset = -code.frame.locsSize-24-code.tempSize;
		long noOfTemps = 0;

		for(Node n : interferenceGraph.nodes().values()) {
			if(n.isActualSpill()) {
				int size = newInstrs.size();
				System.out.print("SPILL: "+n.noOfNeighbours()+" zamenjal bomo: "+n.getTemp().toString()+", ");
				for(int k = 0;k<size;k++) {
					AsmInstr j = newInstrs.get(k);
					AsmOPER instr = (AsmOPER) j;

					if(instr.uses().contains(n.getTemp())) {
						if(!n.offsetWasSet()) {
							n.setOffset(offset-noOfTemps*8);
							noOfTemps++;
						}
						MemTemp tmpTemp = new MemTemp(); // the renamed temp

						MemTemp setReg = new MemTemp();
						Vector<MemTemp> defsForSet = new Vector<>();
						defsForSet.add(setReg);

						Vector<AsmInstr> setConstant = constantIntoRegister(setReg,n.getOffset());
						Vector<MemTemp> loadDefs = new Vector<>();
						loadDefs.add(tmpTemp);
						defsForSet.add(setReg);
						AsmOPER loadInstr = new AsmOPER("LDO `d0,$253,`s0",defsForSet,loadDefs,null);

						Vector<MemTemp> newUses = new Vector<>();
						newUses.addAll(instr.uses());
						
						int indexOfOldTemp = newUses.indexOf(n.getTemp());
						newUses.set(indexOfOldTemp,tmpTemp); //replace old temp with new temp
						
						int indexOfInstr = newInstrs.indexOf(instr);

						if(instr.defs().contains(n.getTemp())) {
							Vector<MemTemp> newDefs = new Vector<>();
							newDefs.add(tmpTemp);
							Vector<MemTemp> usesForStore = new Vector<>();
							usesForStore.add(tmpTemp);
							usesForStore.add(setReg);
							AsmOPER storeNewTemp = new AsmOPER("STO `s0,$253,`s1",usesForStore,null,null);
							AsmOPER newInstr = new AsmOPER(instr.instr(),newUses, newDefs, instr.jumps());
							newInstrs.set(indexOfInstr, newInstr);
							newInstrs.add(indexOfInstr+1, storeNewTemp);
							newInstrs.add(indexOfInstr, loadInstr);
							newInstrs.addAll(indexOfInstr, setConstant);

							size=size+2+setConstant.size();
						} else {
							AsmOPER newInstr = new AsmOPER(instr.instr(),newUses, instr.defs(), instr.jumps());
							newInstrs.set(indexOfInstr, newInstr);
							newInstrs.add(indexOfInstr, loadInstr);
							newInstrs.addAll(indexOfInstr, setConstant);
						
							size=size + 1+setConstant.size();
						}

					} else if (instr.defs().contains(n.getTemp())) {
						MemTemp tmpTemp = new MemTemp();
						//System.out.print("defs: "+tmpTemp.toString()+", ");
						//System.out.print("DEFS, ");
						if(!n.offsetWasSet()) {
							n.setOffset(offset-noOfTemps*8);
							noOfTemps++;
						}
						MemTemp setReg = new MemTemp();
						Vector<MemTemp> defsForSet = new Vector<>();
						defsForSet.add(setReg);
						//AsmOPER subForOffset = new AsmOPER("SUB `d0,0,"+Math.abs(n.getOffset()),null,defsForSet,null);
						//AsmOPER setRegOp = new AsmOPER("SET `d0,"+Math.abs(n.getOffset()),null,defsForSet,null);
						Vector<AsmInstr> setConstant = constantIntoRegister(setReg,n.getOffset());
						//MemTemp tmpTemp = new MemTemp(); // memtemp that will replace node.temp in instruction
						//instr.defs().remove(n.getTemp());
						Vector<MemTemp> defsForStore = new Vector<>();
						defsForStore.add(tmpTemp);
						defsForStore.add(setReg);
						Vector<MemTemp> newDefs = new Vector<>();
						newDefs.addAll(instr.defs());
						
						int indexOfOldTemp = newDefs.indexOf(n.getTemp());
						
						newDefs.set(indexOfOldTemp,tmpTemp);
						
						AsmOPER storeInstr = new AsmOPER("STO `s0,$253,`s1",defsForStore,null,null);
						AsmOPER newInstr = new AsmOPER(instr.instr(),instr.uses(), newDefs,instr.jumps());
						int indexOfInstr = newInstrs.indexOf(instr);
						newInstrs.set(indexOfInstr, newInstr);
						newInstrs.addAll(indexOfInstr+1,setConstant);
						newInstrs.add(indexOfInstr+1+setConstant.size(), storeInstr);
						//instr = newInstr;
						//k+=2;
						size=size+1+setConstant.size();
					} else {
						//System.out.println("TESTIRAM3");
					}
				}
				break;
			}
		}
		Code newCode = new Code(code.frame, code.entryLabel, code.exitLabel, newInstrs);
		newCode.tempSize = code.tempSize+noOfTemps*8;
		return newCode;
	}

	public void allocate(int noOfRegs) {
		// TODO
		LiveAn liveAn = new LiveAn();
		R = noOfRegs;
		//System.out.println("R:"+R);
		
		for(int r = 0; r < AsmGen.codes.size(); r++) {
			
			Code code = AsmGen.codes.get(r);
			//System.out.println(code.frame.label.toString()	);
			InterferenceGraph finalGraph = new InterferenceGraph();
			spilled = true;
			int stevec = 0;
			while(spilled/* && stevec < 3*/) {
				liveAn.analysis();
				//System.out.println(stevec);
				InterferenceGraph interferenceGraph = buildPhase(code);
				/*System.out.println("IZPIS GRAFA: ");
				interferenceGraph.toString();	*/
				//interferenceGraph.toString();
				boolean neki = true;
				while(neki) {
					simplifyPhase(interferenceGraph);
					spillPhase(interferenceGraph);
					if(interferenceGraph.size() == 0) {
						neki = false;
					}
				}
				InterferenceGraph newInterferenceGraph = new InterferenceGraph(); 
				/*System.out.println("Pred spremembo ukazov:");
				for(AsmInstr i : code.instrs) {
					System.out.println(i.toString());
				}*/
				code = selectPhase(code, newInterferenceGraph);
				/*System.out.println("Po spremembi ukazov:");
				for(AsmInstr i : code.instrs) {
					System.out.println(i.toString());
				}*/
				AsmGen.codes.set(r, code);
				/*System.out.println("IZPIS GRAFA: ");
				newInterferenceGraph.toString();*/
				
				//if(spilled) {
					for(AsmInstr i : code.instrs) {
						AsmOPER asmOp = (AsmOPER)i;
						asmOp.removeAllFromIn();
						asmOp.removeAllFromOut();
					}
				//}
				//break;
				finalGraph = newInterferenceGraph;
				stevec++;
			}
			mapTempsToRegs(finalGraph, code);
			/*System.out.println("IZPIS GRAFA: ");
			finalGraph.toString();*/
			AsmGen.codes.set(r, code);
			liveAn.analysis();
		}

	}

	public void izpisStacka() {
		//System.out.println("Stack size: "+stack.size());
		int size = stack.size();
		for(int i = 0; i < size; i++) {
			//System.out.println("i: "+i);
			Node n = (Node) stack.pop();
			System.out.println(n.getTemp().toString());
			
		}
	}

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
	
	public void log() {
		if (logger == null)
			return;
		for (Code code : AsmGen.codes) {
			logger.begElement("code");
			logger.addAttribute("entrylabel", code.entryLabel.name);
			logger.addAttribute("exitlabel", code.exitLabel.name);
			logger.addAttribute("tempsize", Long.toString(code.tempSize));
			code.frame.log(logger);
			logger.begElement("instructions");
			for (AsmInstr instr : code.instrs) {
				logger.begElement("instruction");
				logger.addAttribute("code", instr.toString(tempToReg));
				logger.begElement("temps");
				logger.addAttribute("name", "use");
				for (MemTemp temp : instr.uses()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "def");
				for (MemTemp temp : instr.defs()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "in");
				for (MemTemp temp : instr.in()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.begElement("temps");
				logger.addAttribute("name", "out");
				for (MemTemp temp : instr.out()) {
					logger.begElement("temp");
					logger.addAttribute("name", temp.toString());
					logger.endElement();
				}
				logger.endElement();
				logger.endElement();
			}
			logger.endElement();
			logger.endElement();
		}
	}

}
