package prev.phase.imclin;

import java.util.*;

import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;
import prev.data.lin.*;
import prev.phase.imcgen.ImcGen;
import prev.phase.memory.*;

public class ChunkGenerator extends AstFullVisitor<Object, Object> {

	@Override
	public Object visit(AstAtomExpr atomExpr, Object arg) {
		switch (atomExpr.type()) {
		case STRING:
			MemAbsAccess absAccess = Memory.strings.get(atomExpr);
			ImcLin.addDataChunk(new LinDataChunk(absAccess));
			break;
		default:
			break;
		}
		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Object arg) {
		funDecl.expr().accept(this, arg);

		MemFrame frame = Memory.frames.get(funDecl);
		MemLabel entryLabel = new MemLabel();
		MemLabel exitLabel = new MemLabel();
		
		Vector<ImcStmt> canonStmts = new Vector<ImcStmt>();
		canonStmts.add(new ImcLABEL(entryLabel));
		ImcExpr bodyExpr = ImcGen.exprImc.get(funDecl.expr());
		ImcStmt bodyStmt = new ImcMOVE(new ImcTEMP(frame.RV), bodyExpr);
		canonStmts.addAll(bodyStmt.accept(new StmtCanonizer(), null));
		canonStmts.add(new ImcJUMP(exitLabel));
		
		Vector<ImcStmt> linearStmts = linearize (canonStmts);
		/*for(ImcStmt stmt : linearStmts){
			System.out.println(stmt);
		}*/
		ImcLin.addCodeChunk(new LinCodeChunk(frame, linearStmts, entryLabel, exitLabel));
		
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Object arg) {
		MemAccess access = Memory.accesses.get(varDecl);
		if (access instanceof MemAbsAccess) {
			MemAbsAccess absAccess = (MemAbsAccess) access;
			ImcLin.addDataChunk(new LinDataChunk(absAccess));
		}
		return null;
	}
	
	private Vector<ImcStmt> linearize(Vector<ImcStmt> stmts) {
		// TODO
		Vector<ImcStmt> result = new Vector<ImcStmt>();

		for(int i = 0; i < stmts.size(); i++) {
			ImcStmt stmt = stmts.get(i);
			//System.out.println(stmt);
			if(stmt instanceof ImcCJUMP) {
				ImcCJUMP cJump = (ImcCJUMP) stmt;

				ImcLABEL vmesnaLabela = new ImcLABEL(new MemLabel());
				/*System.out.println(vmesnaLabela.label);
				System.out.println(cJump.posLabel);
				System.out.println(cJump.negLabel);*/
				ImcCJUMP novCJump = new ImcCJUMP(cJump.cond, cJump.posLabel, vmesnaLabela.label);
				result.add(novCJump);
				result.add(vmesnaLabela);
				ImcJUMP jumpNaNegLabel = new ImcJUMP(cJump.negLabel);
				
				result.add(jumpNaNegLabel);
				

			} 
			else {

				result.add(stmt);

			}

			//System.out.println(stmt);
			
			/*if(stmt instanceof ImcCJUMP){
				ImcCJUMP cjump = (ImcCJUMP) stmt;
				MemLabel label = cjump.negLabel;
				int j = i+1;
				
				while(j < stmts.size()) {
					ImcStmt tmpStmt = stmts.get(j);
					if(tmpStmt instanceof ImcLABEL) {
						ImcLABEL labelStmt = (ImcLABEL) tmpStmt;
						if(label.equals(labelStmt.label)) {
							int k = j;
							result.add(stmts.get(k));
							stmts.remove(k);
							k++;
							ImcStmt tmp = stmts.get(k);
							while(!(tmp instanceof ImcJUMP)){
								result.add(tmp);
								stmts.remove(tmp);
								k++;
								tmp = stmts.get(k);
							}
							result.add(tmp);
							stmts.remove(tmp);
							j = stmts.size();
						}
					}
					j++;
					
				}

			} else {
				result.add(stmt);
			}*/
		}


		return result;
	}

}