package prev.phase.asmgen;

import java.util.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.visitor.*;
import prev.data.asm.*;
import prev.Compiler;

/**
 * Machine code generator for expressions.
 */
public class ExprGenerator implements ImcVisitor<MemTemp, Vector<AsmInstr>> {

	// TODO
	public MemTemp visit(ImcTEMP imcTemp, Vector<AsmInstr> instr) {
		return imcTemp.temp;
	}

	public MemTemp visit(ImcUNOP unOp, Vector<AsmInstr> instr) {
		MemTemp subExpr = unOp.subExpr.accept(this, instr);
		Vector<MemTemp> uses = new Vector<MemTemp>();
		uses.add(subExpr);
		AsmOPER asmOp;
		switch(unOp.oper) {
			case NOT:
				asmOp = new AsmOPER("NOR `d0,`s0,0",uses,uses,null);
				instr.add(asmOp);
				break;
			case NEG:
				asmOp = new AsmOPER("NEG `d0,0,`s0", uses, uses, null);
				instr.add(asmOp);
				break;
		}
		return subExpr;
	}

	public MemTemp visit(ImcBINOP binOp, Vector<AsmInstr> instr) {
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();

		MemTemp fst = binOp.fstExpr.accept(this, instr);
		MemTemp snd = binOp.sndExpr.accept(this, instr);

		MemTemp destReg = new MemTemp();
		defs.add(destReg);

		uses.add(fst);
		uses.add(snd);
		AsmOPER asmOp = null;
		AsmOPER compare = null;
		AsmOPER checkResult = null;
		switch(binOp.oper) {
			case OR:
				asmOp = new AsmOPER("OR `d0,`s0,`s1", uses, defs, null);
				instr.add(asmOp);
				break;
			case AND:
				asmOp = new AsmOPER("AND `d0,`s0,`s1", uses, defs, null);
				instr.add(asmOp);
				break;
			case EQU:
				//checks if the value of the CMP is zero if it is the register is set to 1 (true) otherwise is 0 (false)
				compare = new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null); // this returns 0 if s0 is equal to s1
				checkResult = new AsmOPER("ZSZ `d0,`s0,1", defs, defs, null); // zero or set if zero
				instr.add(compare);
				instr.add(checkResult);
				break;
			case NEQ:
				compare = new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null); // this returns 0 if s0 is equal to s1
				checkResult = new AsmOPER("AND `d0,`s0,1", defs, defs, null); // zero or set if nonzero
				instr.add(compare);
				instr.add(checkResult);
				break;
			case LTH:
				//checks if the value of the CMP is negative if it is the register is set to 1 (true) otherwise is 0
				asmOp = new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null); // this returns -1 if s0 < s1
				checkResult = new AsmOPER("ZSN `d0,`s0,1", defs, defs, null); // zero or set if negative
				instr.add(asmOp);
				instr.add(checkResult);
				break;
			case GTH:
				asmOp = new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null); // this returns 1 if s0 > s1
				checkResult = new AsmOPER("ZSP `d0,`s0,1", defs, defs, null); // zero or set if positive
				instr.add(asmOp);
				instr.add(checkResult);
				break;
			case LEQ:
				asmOp = new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null);
				checkResult = new AsmOPER("ZSNP `d0,`s0,1", defs, defs, null); // zero or set if nonpositive
				instr.add(asmOp);
				instr.add(checkResult);
				break;
			case GEQ:
				asmOp = new AsmOPER("CMP `d0,`s0,`s1", uses, defs, null);
				checkResult = new AsmOPER("ZSNN `d0,`s0,1", defs, defs, null); // zero or set if nonnegative
				instr.add(asmOp);
				instr.add(checkResult);
				break;
			case ADD:
				asmOp = new AsmOPER("ADD `d0,`s0,`s1", uses, defs, null);
				instr.add(asmOp);
				break;
			case SUB:
				asmOp = new AsmOPER("SUB `d0,`s0,`s1", uses, defs, null);
				instr.add(asmOp);
				break;
			case MUL:
				asmOp = new AsmOPER("MUL `d0,`s0,`s1", uses, defs, null);
				instr.add(asmOp);
				break;
			case DIV:
				asmOp = new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null);
				instr.add(asmOp);
				break;
			case MOD:
				asmOp = new AsmOPER("DIV `d0,`s0,`s1", uses, defs, null); // normal division because the remainder is stored in special register rR
				AsmOPER res = new AsmOPER("GET `d0,rR", null, defs, null); // 6 is the number of the rR regiser
				instr.add(asmOp);
				instr.add(res);
				break;
		}
		//we return the MemTemp of the destination reg
		return destReg;
	}

	public MemTemp visit(ImcNAME imcName, Vector<AsmInstr> instr) {
		Vector<MemTemp> defs = new Vector<>();
		MemTemp tempToLoadAddr = new MemTemp();
		defs.add(tempToLoadAddr);
		AsmOPER load = new AsmOPER("LDA `d0,"+imcName.label.name,null, defs, null);
		instr.add(load);
		return tempToLoadAddr;
	}

	public MemTemp visit(ImcCONST imcConst, Vector<AsmInstr> instr) {
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();
		long constValue = Math.abs(imcConst.value);
		MemTemp destReg = new MemTemp();
		defs.add(destReg);

		int tmp = (short) (constValue & 0xFFFF);
		AsmOPER setl = new AsmOPER("SETL `d0,"+tmp, null, defs, null);
		instr.add(setl);
		constValue = constValue >> 16; // na predavanjih je bilo receno, da je dobro preverjati ce je po shiftu 0 ali je vecje, da ne nastavljamo po nepotrebnem
		uses.add(destReg);
		tmp = (short) (constValue & 0xFFFF);
		if(tmp > 0) {
			AsmOPER incml = new AsmOPER("INCML `d0,"+tmp, uses, defs, null);
			instr.add(incml);
		}
		constValue = constValue >> 16;
		tmp = (short) (constValue & 0xFFFF);
		if(tmp > 0) {
			AsmOPER incmh = new AsmOPER("INCMH `d0,"+tmp, uses, defs, null);
			instr.add(incmh);
		}
		constValue = constValue >> 16;
		tmp = (short) (constValue & 0xFFFF);
		if(tmp > 0) {
			AsmOPER inch = new AsmOPER("INCH `d0,"+tmp, uses, defs, null);
			instr.add(inch);
			//constValue = constValue >> 16;
		}
		if(imcConst.value<0) {
			instr.add(new AsmOPER("NEG `d0,`s0",uses,defs,null));
		}

		return destReg;
	}

	public MemTemp visit(ImcCALL imcCall, Vector<AsmInstr> instr) {
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();
		Vector<ImcExpr> args = imcCall.args();
		Vector<MemLabel> jump = new Vector<>();
		jump.add(imcCall.label);
		
		int i = 0;
		//System.out.println("Args size: "+args.size());
		for(ImcExpr arg : args) {
			MemTemp argTemp = arg.accept(this, instr);
			long offset = imcCall.offs().get(i);
			Vector<MemTemp> tmp = new Vector<MemTemp>();
			tmp.add(argTemp);
			instr.add(new AsmOPER("STO `s0,$254,"+offset, tmp, null, null)); // storing the calculated args on the stack
			i++;
		}
		
		AsmOPER asmOPER = new AsmOPER("PUSHJ $"+Compiler.numOfRegs+","+imcCall.label.name, null, null, jump); // pushing the registers on the stack
		instr.add(asmOPER); // pushing the registers on the stack
		MemTemp destReg = new MemTemp(); //register for return value
		defs.add(destReg);
		instr.add(new AsmOPER("LDO `d0,$254,0", null, defs, null)); //load the return value from the SP
		return destReg;
	}

	public MemTemp visit(ImcSEXPR imcSexpr, Vector<AsmInstr> instr) {
		instr.addAll(imcSexpr.stmt.accept(new StmtGenerator(),null));
		return imcSexpr.expr.accept(this, instr);
	}

	public MemTemp visit(ImcMEM imcMem, Vector<AsmInstr> instr) {
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();

		MemTemp tmp = imcMem.addr.accept(this, instr);
		uses.add(tmp);

		MemTemp destReg = new MemTemp();
		defs.add(destReg);
		
		AsmOPER load = new AsmOPER("LDO `d0,`s0,0",uses, defs, null);
		instr.add(load);
		return destReg;
	}
}
