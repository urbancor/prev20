package prev.phase.asmgen;

import java.util.*;
import prev.data.imc.code.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.imc.visitor.*;
import prev.data.mem.*;
import prev.data.asm.*;
import prev.common.report.*;
import prev.data.lin.*;

/**
 * Machine code generator for ststements.
 */
public class StmtGenerator implements ImcVisitor<Vector<AsmInstr>, Object> {
	
	// TODO
	public Vector<AsmInstr> visit(ImcMOVE imcMove, Object object) {
		Vector<AsmInstr> instr = new Vector<>();
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemTemp> defs = new Vector<>();
		MemTemp dest;
		MemTemp src; //= imcMove.src.accept(new ExprGenerator(),instr);
		
		//uses.add(src);

		/*if(imcMove.dst instanceof ImcMEM) {
			/*ImcMEM mem = (ImcMEM) imcMove.dst;
			dest = mem.addr.accept(new ExprGenerator(), null);
			defs.add(dest);
			//System.out.println("TEST2");
			AsmMOVE move = new AsmMOVE("STO `s0,`d0,0",uses,defs);
			instr.add(move);*/
			/*dest = (((ImcMEM)imcMove.dst).addr).accept(new ExprGenerator(),instr);
			if(!(imcMove.src instanceof ImcMEM)) {
				src = imcMove.src.accept(new ExprGenerator(),instr);
				uses.add(src);
				uses.add(dest);
				instr.add(new AsmOPER("STO `s0,`s1,0", uses, null, null));
			} else {
				src = (((ImcMEM)imcMove.src).addr).accept(new ExprGenerator(),instr);
				uses.add(src);
				Vector<MemTemp> tmp = new Vector<>();
				MemTemp tmpTemp = new MemTemp();
				tmp.add(tmpTemp);
				tmp.add(dest);

				instr.add(new AsmOPER("LDO `d0,`s0,0", uses, tmp, null)); //iz naslova s0 v začasni register
				instr.add(new AsmOPER("STO `s0,`s1,0", tmp, null, null));
			}
		} else {
			dest  = imcMove.dst.accept(new ExprGenerator(),instr);
			defs.add(dest);
			if(!(imcMove.src instanceof ImcMEM)) {
				src = imcMove.src.accept(new ExprGenerator(),instr);
				uses.add(src);
				instr.add(new AsmMOVE("SET `d0,`s0", uses, defs));
			}else {
				src = (((ImcMEM)imcMove.src).addr).accept(new ExprGenerator(),instr);
				uses.add(src);
				instr.add(new AsmOPER("LDO `d0,`s0,0", uses, defs, null));
			}
			//System.out.println("TEST4");
			// set value in dst to src
			//AsmMOVE set = new AsmMOVE("SET `d0,`s0", uses,defs);
			//instr.add(set);
			
		}*/
		/*if(!(imcMove.dst instanceof ImcMEM)) {
			/*ImcMEM mem = (ImcMEM) imcMove.dst;
			dest = mem.addr.accept(new ExprGenerator(), null);
			defs.add(dest);
			//System.out.println("TEST2");
			AsmMOVE move = new AsmMOVE("STO `s0,`d0,0",uses,defs);
			instr.add(move);*/

			/*dest  = imcMove.dst.accept(new ExprGenerator(),instr);
			defs.add(dest);
			if(!(imcMove.src instanceof ImcMEM)) {
				src = imcMove.src.accept(new ExprGenerator(),instr);
				uses.add(src);
				instr.add(new AsmMOVE("SET `d0,`s0", uses, defs));
			}else {
				src = (((ImcMEM)imcMove.src).addr).accept(new ExprGenerator(),instr);
				uses.add(src);
				instr.add(new AsmOPER("LDO `d0,`s0,0", uses, defs, null));
			}


			
		} else {
			dest = (((ImcMEM)imcMove.dst).addr).accept(new ExprGenerator(),instr);
			if(!(imcMove.src instanceof ImcMEM)) {
				src = imcMove.src.accept(new ExprGenerator(),instr);
				uses.add(src);
				uses.add(dest);
				instr.add(new AsmOPER("STO `s0,`s1,0", uses, null, null));
			} else {
				src = (((ImcMEM)imcMove.src).addr).accept(new ExprGenerator(),instr);
				uses.add(src);
				Vector<MemTemp> tmp = new Vector<>();
				MemTemp tmpTemp = new MemTemp();
				tmp.add(tmpTemp);
				
				instr.add(new AsmOPER("LDO `d0,`s0,0", uses, tmp, null)); //iz naslova s0 v začasni register
				tmp.add(dest);
				instr.add(new AsmOPER("STO `s0,`s1,0", tmp, null, null));
			}
		}*/

		

		if(imcMove.dst instanceof ImcMEM) {
			ImcMEM mem = (ImcMEM) imcMove.dst;
			dest = mem.addr.accept(new ExprGenerator(), null);
			src = imcMove.src.accept(new ExprGenerator(),instr);
		
			uses.add(src);
			uses.add(dest);
			//System.out.println("TEST2");
			AsmOPER move = new AsmOPER("STO `s0,`s1,0",uses,null,null);
			instr.add(move);
		} else {
			dest  = imcMove.dst.accept(new ExprGenerator(),instr);
			src = imcMove.src.accept(new ExprGenerator(),instr);
		
			uses.add(src);
			defs.add(dest);
			//System.out.println("TEST4");
			// set value in dst to src
			AsmMOVE set = new AsmMOVE("SET `d0,`s0", uses,defs);
			instr.add(set);
			
		}

		return instr;
	}

	public Vector<AsmInstr> visit(ImcJUMP imcJump, Object object) {
		Vector<AsmInstr> instr = new Vector<>();
		Vector<MemTemp> uses = new Vector<>();
		Vector<MemLabel> jump = new Vector<>();
		jump.add(imcJump.label);
		
		if(object instanceof LinCodeChunk) {
			if(imcJump.label == ((LinCodeChunk)object).exitLabel) {
				uses.add(((LinCodeChunk)object).frame.RV);
			}
		}
		
		AsmOPER asmOper = new AsmOPER("JMP "+imcJump.label.name,uses, null, jump);
		instr.add(asmOper);
		return instr;
	}

	public Vector<AsmInstr> visit(ImcLABEL imcLabel, Object object) {
		Vector<AsmInstr> instr = new Vector<>();
		AsmLABEL newLabel = new AsmLABEL(imcLabel.label);
		instr.add(newLabel);
		return instr;
	}

	public Vector<AsmInstr> visit(ImcESTMT imcEStmt, Object object) {
		Vector<AsmInstr> instr = new Vector<>();
		imcEStmt.expr.accept(new ExprGenerator(),instr);
		return instr;
	}

	public Vector<AsmInstr> visit(ImcCJUMP imcCJump, Object object) {
		Vector<AsmInstr> instr = new Vector<>();
		Vector<MemTemp> uses = new Vector<>();
		//Vector<MemTemp> defs = new Vector<>();
		Vector<MemLabel> jump = new Vector<>();

		MemTemp cond = imcCJump.cond.accept(new ExprGenerator(),instr);
		uses.add(cond);
		MemLabel ifTrue = imcCJump.posLabel;
		MemLabel ifFalse = imcCJump.negLabel;
		jump.add(ifTrue);
		jump.add(ifFalse); //klara rekla je treba se to dodat
		AsmOPER jumpInstr = new AsmOPER("BP `s0,"+ifTrue.name, uses, null, jump);
		instr.add(jumpInstr);
		return instr;
	}

	public Vector<AsmInstr> visit(ImcSTMTS imcStmts, Object object) {
		Vector<AsmInstr> instr = new Vector<>();
		for(ImcStmt stmt : imcStmts.stmts()) {
			instr.addAll(stmt.accept(this,object));
		}
		return instr;
	}

}
