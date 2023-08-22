parser grammar PrevParser;

@header {

	package prev.phase.synan;
	
	import java.util.*;
	
	import prev.common.report.*;
	import prev.phase.lexan.*;

	import prev.phase.lexan.LexAn.PrevToken;
	import prev.data.ast.tree.*;
	import prev.data.ast.tree.expr.*;
	import prev.data.ast.tree.stmt.*;
	import prev.data.ast.tree.decl.*;
	import prev.data.ast.tree.type.*;

}

options{
    tokenVocab=PrevLexer;
}


source 
	returns [AstTrees<AstDecl> ast]
	locals [Vector<AstDecl> dcls = new Vector<AstDecl>();]
	: (decl { 
		$dcls.add($decl.ast);
		$ast = new AstTrees<AstDecl>($dcls);
	})+ EOF 
;

/* 
	declarating a type
*/
type_decl
	returns [AstTypeDecl ast]
	: typ=TYPE name=IDENTIFIER ASIGN type {
		Location loc = new Location(((PrevToken) $typ).location(), $type.ast.location());
		$ast = new AstTypeDecl(loc, $name.getText(), $type.ast);
	}
;
/* 
	declarating a variable
*/
var_decl
	returns[AstVarDecl ast]
	: var=VARIABLE name=IDENTIFIER COL type {
		Location loc = new Location(((PrevToken) $var).location(), $type.ast.location());
		$ast = new AstVarDecl(loc, $name.getText(), $type.ast);
	}
;

/* 
	declarating a function
*/
func_decl
	returns[AstFunDecl ast]
	: fun=FUNCTION name=IDENTIFIER LEFTBRAC decl_ RIGHTBRAC COL type ASIGN expr {
		
		Location loc = new Location(((PrevToken) $fun).location(), $type.ast.location());
		$ast = new AstFunDecl(loc, $name.getText(), $decl_.ast, $type.ast, $expr.ast);
		
	}
;

/*
	all types of declarations
*/
decl 
	returns [AstDecl ast]
	: type_decl { $ast = $type_decl.ast; }
	| var_decl { $ast = $var_decl.ast; }
	| func_decl { $ast = $func_decl.ast;}
;
/*
	declaration of parameters in function
 */
/*decls
	returns[AstTrees<AstParDecl> ast]
	: decl_ { 
		
		
		$ast = new AstTrees<AstParDecl>($decl_.ast);}
		
;*/
decl_ 
	returns [AstTrees<AstParDecl> ast]
	locals [Vector<AstParDecl> parDecls = new Vector<AstParDecl>();]
	: name1=IDENTIFIER COL t1=type {
		Location parLoc = new Location(((PrevToken) $name1).location(), $t1.ast.location());
		
		$parDecls.add(new AstParDecl(parLoc, $name1.getText(), $t1.ast));
	} (comma=COMMA name2=IDENTIFIER COL t2=type{
		Location parLoc1 = new Location(((PrevToken) $comma).location(), $t2.ast.location());
		$parDecls.add(new AstParDecl(parLoc1, $name2.getText(), $t2.ast));
	})*  {
		
		$ast = new AstTrees<AstParDecl>(new Vector<AstParDecl>($parDecls));
	} 
	| { 
		
		$ast = new AstTrees<AstParDecl>(); 
		
		} 
;

atomicType
	returns [AstAtomType ast]
	: aType=(VOID | CHAR | INTEGER | BOOLEAN){
		
		Location loc = ((PrevToken) $aType).location();
		AstAtomType.Type tokenType = AstAtomType.Type.valueOf($aType.getText().toUpperCase());
		$ast = new AstAtomType(loc, tokenType);
		
	} 
;

namedType
	returns [AstNameType ast]
	: id=IDENTIFIER {
		Location loc = ((PrevToken) $id).location();
		$ast = new AstNameType(loc, $id.getText());
	}
;
 
arrayType
	returns [AstArrType ast]
	: LEFTSQR expr rsqr=RIGHTSQR atomicType {
		Location location = new Location($atomicType.ast.location(), ((PrevToken) $rsqr).location());
		$ast = new AstArrType(location, $atomicType.ast, $expr.ast);
	}
	| LEFTSQR expr rsqr=RIGHTSQR namedType {
		Location location = new Location($namedType.ast.location(), ((PrevToken) $rsqr).location());
		$ast = new AstArrType(location, $namedType.ast, $expr.ast);
	}
	
	| LEFTSQR expr rsqr=RIGHTSQR recordType {
		Location location = new Location($recordType.ast.location(), ((PrevToken) $rsqr).location());
		$ast = new AstArrType(location, $recordType.ast, $expr.ast);
	}
	| LEFTSQR expr rsqr=RIGHTSQR enclosedType {
		Location location = new Location($enclosedType.ast.location(), ((PrevToken) $rsqr).location());
		$ast = new AstArrType(location, $enclosedType.ast, $expr.ast);
	}
	| LEFTSQR expr rsqr=RIGHTSQR arrType=arrayType {
		Location location = new Location($arrType.ast.location(), ((PrevToken) $rsqr).location());
		$ast = new AstArrType(location, $arrType.ast, $expr.ast);
	}
;

pointerType
	returns [AstPtrType ast]
	: car=CARET type {
		Location loc = new Location(((PrevToken) $car).location(), $type.ast.location());
		$ast = new AstPtrType(loc, $type.ast);
	}
;

recordType
	returns [AstRecType ast]
	locals [Vector<AstCompDecl> allRecs = new Vector<AstCompDecl>();]
	: lcurl=LEFTCURL id=IDENTIFIER COL type {
		
		Location loc = new Location(((PrevToken) $id).location(), $type.ast.location());
		AstCompDecl comp = new AstCompDecl(loc, $id.getText(), $type.ast);
		$allRecs.add(comp);

	} type_ {
		
		
	} rcurl=RIGHTCURL{
		
		Location recordLocation = new Location(((PrevToken) $lcurl).location(), ((PrevToken) $rcurl).location());
		
		
		if($type_.ast != null) {
			$allRecs.addAll($type_.ast);
		}
		$ast = new AstRecType(recordLocation, new AstTrees<AstCompDecl>($allRecs));
	}
;

enclosedType
	returns [AstType ast]
	: lbrac=LEFTBRAC type rbrac=RIGHTBRAC {
		Location loc = new Location(((PrevToken) $lbrac).location(), ((PrevToken) $rbrac).location());
		$ast = $type.ast;
		$ast.relocate(loc);
	}
;

type
	returns[AstType ast]
	: atomicType { $ast = $atomicType.ast; }
	| namedType { $ast = $namedType.ast; }
	| arrayType { $ast = $arrayType.ast; }
	| pointerType { $ast = $pointerType.ast; }
	| recordType { $ast = $recordType.ast; }
	| enclosedType { $ast = $enclosedType.ast; }
;
/*type : VOID | CHAR | INTEGER | BOOLEAN | IDENTIFIER | type LEFTSQR expr RIGHTSQR | CARET type | LEFTCURL IDENTIFIER COL type type_ RIGHTCURL | LEFTBRAC type RIGHTBRAC;*/
type_
	returns[Vector<AstCompDecl> ast]
	locals[Vector<AstCompDecl> moreRecords = new Vector<AstCompDecl>();]
	: (comma=COMMA id=IDENTIFIER COL type {
		Location loc = new Location(((PrevToken) $comma).location(), $type.ast.location());
		AstCompDecl comp = new AstCompDecl(loc, $id	.getText(), $type.ast);
		$moreRecords.add(comp);
		$ast = $moreRecords;
	})* /*type_[$moreRecords] {
		$allRecords.addAll($type_.ast);
		$ast = new AstRecType(loc, new AstTrees<AstCompDecl>($allRecords));
	}
	| { $ast = null; }*/
;

expr 
	returns [AstExpr ast]
	locals [Vector<AstExpr> allExpr = new Vector<AstExpr>();]
	: e1=expr WHERE whereexpr {
		Location whereLoc = new Location( $e1.ast.location(), $whereexpr.ast.location());
		$ast = new AstWhereExpr(whereLoc, $e1.ast, $whereexpr.ast);

	}
	| stmt_ {
		$ast= $stmt_.ast;
	}
	/*| cnst {
		$ast = $cnst.ast;
	}*/
	| a {
		$allExpr.add($a.ast);
	}orexpr[$a.ast] {
		
		$ast = $orexpr.ast;
	}
;
orexpr [AstExpr leftTree]
	returns[AstExpr ast]
	locals[AstExpr subTree, Vector<AstExpr> allExpr = new Vector<AstExpr>();]
	: OR a {
		Location location = new Location($leftTree.location(), $a.ast.location());
		$subTree = new AstBinExpr(location, AstBinExpr.Oper.OR, $leftTree, $a.ast);
		$allExpr.add($subTree);
	} orexpr[$subTree] {
		
		$ast = $orexpr.ast;
		
	}
	| {
		$ast=$leftTree;
	}
;
a 
	returns[AstExpr ast]
	: b andexpr[$b.ast] {
		$ast = $andexpr.ast;
	}
;
andexpr [AstExpr leftTree]
	returns[AstExpr ast]
	locals[AstExpr subTree, Vector<AstExpr> allExpr = new Vector<AstExpr>();]
	: AND b {
		Location location = new Location($leftTree.location(), $b.ast.location());
		$subTree = new AstBinExpr(location, AstBinExpr.Oper.AND, $leftTree, $b.ast);
		$allExpr.add($subTree);
	} andexpr[$subTree] {
		$ast = $andexpr.ast;
	} 
	| {
		$ast=$leftTree;
	}
;
b 
	returns[AstExpr ast]
	: c relationalexpr[$c.ast] {
		$ast = $relationalexpr.ast;
	}
	
;
relationalexpr [AstExpr leftTree]
	returns[AstExpr ast]
	: EQL c { 
		Location location = new Location($leftTree.location(), $c.ast.location());
		$ast= new AstBinExpr(location, AstBinExpr.Oper.EQU, $leftTree, $c.ast);
	}
	| NEQ c { 
		Location location = new Location($leftTree.location(), $c.ast.location());
		$ast= new AstBinExpr(location, AstBinExpr.Oper.NEQ, $leftTree, $c.ast);
	}
	| LT c { 
		Location location = new Location($leftTree.location(), $c.ast.location());
		$ast= new AstBinExpr(location, AstBinExpr.Oper.LTH, $leftTree, $c.ast);
	}
	| GT c { 
		Location location = new Location($leftTree.location(), $c.ast.location());
		$ast= new AstBinExpr(location, AstBinExpr.Oper.GTH, $leftTree, $c.ast);
	}
	| LTE c { 
		Location location = new Location($leftTree.location(), $c.ast.location());
		$ast= new AstBinExpr(location, AstBinExpr.Oper.LEQ, $leftTree, $c.ast);
	}
	| GTE c { 
		Location location = new Location($leftTree.location(), $c.ast.location());
		$ast= new AstBinExpr(location, AstBinExpr.Oper.GEQ, $leftTree, $c.ast);
	}
	| {
		$ast=$leftTree;
	}
;
c 
	returns[AstExpr ast]
	: d c1[$d.ast] {
		$ast = $c1.ast;
	} 	
;
c1[AstExpr leftTree] 
	returns[AstExpr ast]
	: aditiveexpr[$leftTree] {
		$ast = $aditiveexpr.ast;
	} 
	| {
		$ast=$leftTree;
	}
;
aditiveexpr[AstExpr leftTree] 
	returns[AstExpr ast]
	locals[AstExpr subTree]
	: PLUS d { 
		Location location = new Location($leftTree.location(), $d.ast.location());
		$subTree = new AstBinExpr(location, AstBinExpr.Oper.ADD, $leftTree, $d.ast);
	} c1[$subTree] {
		$ast = $c1.ast;
	} 
	| MINUS d { 
		Location location = new Location($leftTree.location(), $d.ast.location());
		$subTree = new AstBinExpr(location, AstBinExpr.Oper.SUB, $leftTree, $d.ast);
	} c1[$subTree] {
		$ast = $c1.ast;
	} 
;
d 
	returns[AstExpr ast]
	: prefixexpr multiplicativeexpr[$prefixexpr.ast] {
		$ast = $multiplicativeexpr.ast;
	}
	
;
multiplicativeexpr[AstExpr leftTree]
	returns[AstExpr ast]
	locals[AstExpr subTree]
	: STAR prefixexpr { 
		Location location = new Location($leftTree.location(), $prefixexpr.ast.location());
		$subTree = new AstBinExpr(location, AstBinExpr.Oper.MUL, $leftTree, $prefixexpr.ast);
	} multiplicativeexpr[$subTree] {
		$ast = $multiplicativeexpr.ast;
	}
	| SLASH prefixexpr { 
		Location location = new Location($leftTree.location(), $prefixexpr.ast.location());
		$subTree = new AstBinExpr(location, AstBinExpr.Oper.DIV, $leftTree, $prefixexpr.ast);
	} multiplicativeexpr[$subTree] {
		$ast = $multiplicativeexpr.ast;
	} 
	| MOD prefixexpr { 
		Location location = new Location($leftTree.location(), $prefixexpr.ast.location());
		$subTree = new AstBinExpr(location, AstBinExpr.Oper.MOD, $leftTree, $prefixexpr.ast);
	} multiplicativeexpr[$subTree] {
		$ast = $multiplicativeexpr.ast;
	}
	| {
		$ast=$leftTree;
	}
;
prefixexpr
	returns[AstExpr ast]
	: f {
		
		$ast = $f.ast;
	}
	| zac=EXCL prefixexpr {
		
		Location location = new Location(((PrevToken) $zac).location(), $prefixexpr.ast.location());
		$ast = new AstPfxExpr(location, AstPfxExpr.Oper.NOT, $prefixexpr.ast);
	}
	| zac=PLUS prefixexpr {
		Location location = new Location(((PrevToken) $zac).location(), $prefixexpr.ast.location());
		$ast = new AstPfxExpr(location, AstPfxExpr.Oper.ADD, $prefixexpr.ast);
	}
	| zac=MINUS prefixexpr {
		Location location = new Location(((PrevToken) $zac).location(), $prefixexpr.ast.location());
		$ast = new AstPfxExpr(location, AstPfxExpr.Oper.SUB, $prefixexpr.ast);
	}
	| zac=CARET prefixexpr {
		Location location = new Location(((PrevToken) $zac).location(), $prefixexpr.ast.location());
		$ast = new AstPfxExpr(location, AstPfxExpr.Oper.PTR, $prefixexpr.ast);
	}
	| zac=NEW prefixexpr{
		Location location = new Location(((PrevToken) $zac).location(), $prefixexpr.ast.location());
		$ast = new AstPfxExpr(location, AstPfxExpr.Oper.NEW, $prefixexpr.ast);
	}
	| zac=DEL prefixexpr{
		Location location = new Location(((PrevToken) $zac).location(), $prefixexpr.ast.location());
		$ast = new AstPfxExpr(location, AstPfxExpr.Oper.DEL, $prefixexpr.ast);
	}
;
/*prefixexpr1 : prefixexpr | f ;*/
f 
	returns[AstExpr ast]
	: primaryexpr postfixexpr[$primaryexpr.ast] {
		$ast = $postfixexpr.ast;
	}
	/*cnst {
		$ast = $cnst.ast;
	} */
;
postfixexpr[AstExpr leftTree]
	returns[AstExpr ast]
	locals[AstExpr subTree]
	: LEFTSQR expr5 end=RIGHTSQR {
		Location loc = new Location($leftTree.location(), ((PrevToken) $end).location());
		$subTree = new AstArrExpr(loc, $leftTree, $expr5.ast);
	} postfixexpr[$subTree] {
		$ast = $postfixexpr.ast;
	}
	| end=CARET {
		Location loc = new Location($leftTree.location(), ((PrevToken) $end).location());
		$subTree = new AstSfxExpr(loc, AstSfxExpr.Oper.PTR, $leftTree);
	} postfixexpr[$subTree] {
		$ast = $postfixexpr.ast;
	}
	| DOT name=IDENTIFIER {
		Location loc = new Location($leftTree.location(), ((PrevToken) $name).location());
		AstNameExpr nameExpression = new AstNameExpr(((PrevToken) $name).location(), $name.getText());
		$subTree = new AstRecExpr(loc, $leftTree, nameExpression);
	} postfixexpr[$subTree] {
		$ast = $postfixexpr.ast;
	}
	| {
		$ast = $leftTree;
	}
;
primaryexpr 
	returns[AstExpr ast]
	: /*IDENTIFIER expr1 | cnst | LEFTBRAC expr expr4 */
	cnst {
		$ast = $cnst.ast;
	} 
	| idExpr { $ast = $idExpr.ast; }
	| funcCall { $ast = $funcCall.ast; }
	| bracExpr { $ast = $bracExpr.ast; }
	| castExpr { $ast = $castExpr.ast; }
;
idExpr 
	returns[AstExpr ast]
	: id=IDENTIFIER {
		$ast = new AstNameExpr(((PrevToken) $id).location(), $id.getText());
	}
;
funcCall
	returns[AstExpr ast]
	: name=IDENTIFIER LEFTBRAC args rbrac=RIGHTBRAC {
		Location loc = new Location(((PrevToken) $name).location(), ((PrevToken) $rbrac).location());
		$ast = new AstCallExpr(loc, $name.getText(), $args.ast);
	}
;
args
	returns[AstTrees<AstExpr> ast]
	locals [Vector<AstExpr> allExpr = new Vector<AstExpr>()]
	: expr {
		$allExpr.add($expr.ast);
		$ast = new AstTrees<AstExpr>($allExpr);
	} (COMMA expr {
		$allExpr.add($expr.ast);
		$ast = new AstTrees<AstExpr>($allExpr);
	})*
	| { $ast = null; }
;
bracExpr
	returns[AstExpr ast]
	: LEFTBRAC expr RIGHTBRAC {
		$ast = $expr.ast;
	}
;
castExpr
	returns[AstExpr ast]
	: lbrac=LEFTBRAC expr COL type rbrac=RIGHTBRAC{
		Location loc = new Location(((PrevToken) $lbrac).location(), ((PrevToken) $rbrac).location());
		$ast = new AstCastExpr(loc, $expr.ast, $type.ast);
	}
;
expr1 : LEFTBRAC expr3 RIGHTBRAC | ;
expr3 : expr expr3 | COMMA expr expr3 | ;
expr4 : COL type RIGHTBRAC | RIGHTBRAC ;
expr5 
	returns[AstExpr ast]
	: expr {
		$ast = $expr.ast;
	} 
	| {
		$ast = null;
	}
;
whereexpr 
	returns[AstTrees<AstDecl> ast]
	locals [Vector<AstDecl> dcls = new Vector<AstDecl>();]
	: lcurl=LEFTCURL (decl {
		$dcls.add($decl.ast);
		$ast = new AstTrees<AstDecl>($dcls);
	})+ RIGHTCURL
	| {
		$ast=null;
	};
cnst returns [AstAtomExpr ast] 
	: truecnst=TRUE { $ast = new AstAtomExpr(((PrevToken) $truecnst).location(), AstAtomExpr.Type.BOOLEAN, $truecnst.getText()); }
	| falsecnst=FALSE { $ast = new AstAtomExpr(((PrevToken) $falsecnst).location(), AstAtomExpr.Type.BOOLEAN, $falsecnst.getText()); }
	| intcnst=INTCONST { $ast = new AstAtomExpr(((PrevToken) $intcnst).location(), AstAtomExpr.Type.INTEGER, $intcnst.getText()); }
	| charcnst=CHARACTERCONST { $ast = new AstAtomExpr(((PrevToken) $charcnst).location(), AstAtomExpr.Type.CHAR, $charcnst.getText()); }
	| stringcnst=STRINGCONST { $ast = new AstAtomExpr(((PrevToken) $stringcnst).location(), AstAtomExpr.Type.STRING, $stringcnst.getText()); }
	| nonecnst=NONE { $ast = new AstAtomExpr(((PrevToken) $nonecnst).location(), AstAtomExpr.Type.VOID, $nonecnst.getText()); }
	| ponitercnst=POINTER { $ast = new AstAtomExpr(((PrevToken) $ponitercnst).location(), AstAtomExpr.Type.POINTER, $ponitercnst.getText()); }
;
stmt
	returns [AstStmt ast]
	: ifword=IF expr THEN thenstmt=stmt ELSE elsestmt=stmt {
		Location loc = new Location(((PrevToken) $ifword).location(), $elsestmt.ast.location());
		$ast = new AstIfStmt(loc, $expr.ast, $thenstmt.ast, $elsestmt.ast);
	}
	| whileword=WHILE expr DO stmt {
		Location loc = new Location(((PrevToken) $whileword).location(), $stmt.ast.location());
		$ast = new AstWhileStmt(loc, $expr.ast, $stmt.ast);
	}
	| expr { $ast = new AstExprStmt($expr.ast.location(), $expr.ast); } 
	| firstexpr=expr ASIGN secondexpr=expr {
		Location location = new Location($firstexpr.ast.location(), $secondexpr.ast.location());
		$ast = new AstAssignStmt(location, $firstexpr.ast, $secondexpr.ast);
	}
;
stmt_
	returns[AstExpr ast]
	locals[Vector<AstStmt> allStatements = new Vector<AstStmt>();]
	: lsqr=LEFTCURL (stmt {
		$allStatements.add($stmt.ast);
	} SEMCOL)+ rsqr=RIGHTCURL {
		Location loc = new Location(((PrevToken) $lsqr).location(), ((PrevToken) $rsqr).location());
		AstTrees<AstStmt> parsedStatements = new AstTrees<AstStmt>($allStatements);
		$ast = new AstStmtExpr(loc, parsedStatements);
	}
;
/*stmt1 : ASIGN expr | ;*/