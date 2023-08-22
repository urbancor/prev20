package prev.phase.imcgen;

import java.util.*;

import prev.data.ast.tree.stmt.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.visitor.*;
import prev.data.mem.*;
import prev.phase.memory.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;

public class StmtGenerator implements AstVisitor<ImcStmt, Stack<MemFrame>> {

    @Override
    public ImcStmt visit(AstExprStmt exprStmt, Stack<MemFrame> frames) {
        ImcExpr expr = exprStmt.expr().accept(new ExprGenerator(), frames);
        ImcStmt stmt = new ImcESTMT(expr);
        ImcGen.stmtImc.put(exprStmt, stmt);
        return stmt;

    }

    @Override
    public ImcStmt visit(AstAssignStmt asignStmt, Stack<MemFrame> frames) {
        ImcExpr dst = asignStmt.dst().accept(new ExprGenerator(), frames);
        ImcExpr src = asignStmt.src().accept(new ExprGenerator(), frames);

        ImcMOVE stmt = new ImcMOVE(dst, src);
        ImcGen.stmtImc.put(asignStmt, stmt);
        return stmt;
    }


    /*
        if <condition> then <ifTrueStmts> else <ifFalseStmts>

        CJUMP(condition,ifTrueLabel,ifFalseLabel)
        LABEL(ifTrueLabel)
        ifTrueStmts
        JUMP(endLabel)
        LABEL(ifFalseLabel)
        ifFalseStmts
        LABEL(endLabel)
    */

    @Override
    public ImcStmt visit(AstIfStmt ifStmt, Stack<MemFrame> frames) {
        ImcExpr condition = ifStmt.cond().accept(new ExprGenerator(), frames);
        ImcStmt thenStmt = ifStmt.thenStmt().accept(this, frames);
        ImcStmt elseStmt = ifStmt.elseStmt().accept(this, frames);

        Vector<ImcStmt> ifElseStmt = new Vector<>();

        ImcLABEL ifTrue = new ImcLABEL(new MemLabel());
        ImcLABEL ifFalse = new ImcLABEL(new MemLabel());
        ImcLABEL end = new ImcLABEL(new MemLabel());

        ifElseStmt.add(new ImcCJUMP(condition, ifTrue.label, ifFalse.label));
        ifElseStmt.add(ifTrue);
        ifElseStmt.add(thenStmt);
        ifElseStmt.add(new ImcJUMP(end.label));
        ifElseStmt.add(ifFalse);
        ifElseStmt.add(elseStmt);
        ifElseStmt.add(end);

        ImcSTMTS imcStmts = new ImcSTMTS(ifElseStmt);
        ImcGen.stmtImc.put(ifStmt, imcStmts);
        return imcStmts;

    }

    /*
        while <condition> do <ifTrueStmts>

        LABEL(startLabel)
        CJUMP(condition,ifTrueLabel,ifFalseLabel)
        LABEL(ifTrueLabel)
        ifTrueStmts
        LABEL(startLabel)
        LABEL(endLabel)

    */

    @Override
    public ImcStmt visit(AstWhileStmt whileStmt, Stack<MemFrame> frames) {
        ImcExpr condition = whileStmt.cond().accept(new ExprGenerator(), frames);
        ImcStmt bodyStmt = whileStmt.bodyStmt().accept(this, frames);

        Vector<ImcStmt> whileStmts = new Vector<>();

        ImcLABEL ifTrue = new ImcLABEL(new MemLabel());
        ImcLABEL start = new ImcLABEL(new MemLabel());
        ImcLABEL end = new ImcLABEL(new MemLabel());

        whileStmts.add(start);
        whileStmts.add(new ImcCJUMP(condition, ifTrue.label, end.label));
        whileStmts.add(ifTrue);
        whileStmts.add(bodyStmt);
        whileStmts.add(new ImcJUMP(start.label));
        whileStmts.add(end);

        ImcSTMTS imcStmts = new ImcSTMTS(whileStmts);
        ImcGen.stmtImc.put(whileStmt, imcStmts);
        return imcStmts;

    }

}