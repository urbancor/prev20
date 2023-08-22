package prev.phase.seman;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.semtype.*;

/**
 * Address resolver.
 * 
 * The address resolver finds out which expressions denote lvalues and leaves
 * the information in {@link SemAn#isAddr}.
 */
public class AddrResolver extends AstFullVisitor<Object, Object> {

	// TODO
	// GENERAL PURPOSE
	public enum Mode {
		HEAD, BODY
	}
	
	public Object visit(AstTrees<?> trees, Object arg) {
		
		for (AstTree tree : trees) {
			//System.out.println(tree.getClass());
			tree.accept(this,null);
			
			
		}
		return null;
	}
	
	public Object visit(AstNameExpr nameExpr, Object mode) {
		
		AstDecl deklaracija = SemAn.declaredAt.get(nameExpr);
		if(deklaracija instanceof AstVarDecl || deklaracija instanceof AstParDecl) {
			
			SemAn.isAddr.put(nameExpr, true);
		}
		return null;
	}

	
	
	public Object visit(AstSfxExpr sfxExpr, Object mode) {
		sfxExpr.expr().accept(this, mode);
		if(SemAn.ofType.get(sfxExpr.expr()).actualType() instanceof SemPointer) {
			SemAn.isAddr.put(sfxExpr, true);
		}
		return null;
	}
	public Object visit(AstArrExpr arrExpr, Object mode) {
		arrExpr.arr().accept(this, mode);
		arrExpr.idx().accept(this, mode);
		Boolean tmp = SemAn.isAddr.get(arrExpr.arr()); 
		if(tmp != null && tmp) {
			SemAn.isAddr.put(arrExpr, true);
		}
		return null;
	}
	public Object visit(AstRecExpr recExpr, Object mode) {
		recExpr.rec().accept(this, mode);
		recExpr.comp().accept(this, mode);
		Boolean tmp = SemAn.isAddr.get(recExpr.rec());
		if(tmp != null && tmp) {
			SemAn.isAddr.put(recExpr, true);
		}
		return null;
	}
}
