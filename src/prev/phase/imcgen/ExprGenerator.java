package prev.phase.imcgen;

import java.util.*;

import prev.common.report.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.semtype.*;
import prev.data.mem.*;
import prev.phase.memory.*;
import prev.phase.seman.*;
import prev.data.imc.code.expr.*;
import prev.data.imc.code.stmt.*;

public class ExprGenerator implements AstVisitor<ImcExpr, Stack<MemFrame>> {
    
    @Override
    public ImcExpr visit(AstAtomExpr atomExpr, Stack<MemFrame> frames) {
        ImcExpr returnValue = null;
		switch(atomExpr.type()) {
            case INTEGER:
                returnValue = new ImcCONST(Long.parseLong(atomExpr.value()));
				ImcGen.exprImc.put(atomExpr, returnValue);
				break;
            case VOID:
                returnValue = new ImcCONST(1984);
				ImcGen.exprImc.put(atomExpr, returnValue);
				break;
            case CHAR:
                returnValue = new ImcCONST((int)atomExpr.value().charAt(1));
				ImcGen.exprImc.put(atomExpr, returnValue);
				break;
			case BOOLEAN:
				String bool = atomExpr.value();
				long saveBool = 0;
				if(bool.equals("true")){
					saveBool = 1;
                }
                returnValue = new ImcCONST(saveBool);
				ImcGen.exprImc.put(atomExpr, returnValue);
				break;
            case POINTER:
                returnValue = new ImcCONST(0);
				ImcGen.exprImc.put(atomExpr, returnValue);
				break;
            case STRING:
                ImcNAME addr = new ImcNAME(Memory.strings.get(atomExpr).label);
                returnValue = addr;
				ImcGen.exprImc.put(atomExpr, returnValue);
				break;
			default:
				break;
		}
		return returnValue;
    }
    
    @Override
    public ImcExpr visit(AstNameExpr nameExpr, Stack<MemFrame> frames) {

        // get where the nameExpr is declared at
        AstDecl declaration = (AstDecl) SemAn.declaredAt.get(nameExpr);
        MemAccess access = null;
        if(declaration instanceof AstVarDecl) {
            AstVarDecl variableDeclaration = (AstVarDecl)declaration;
            access = Memory.accesses.get(variableDeclaration);
        } else {
            AstParDecl parameterDeclaration = (AstParDecl)declaration;
            access = Memory.accesses.get(parameterDeclaration);
        }
        // get the access 
        //MemAccess access = Memory.accesses.get(declaration);
        ImcMEM mem = new ImcMEM(null);
        //check which type of access it is
        if(access instanceof MemAbsAccess) {
            MemAbsAccess absAccess = (MemAbsAccess) access;
            ImcNAME name = new ImcNAME(absAccess.label);
            mem = new ImcMEM(name);
            return mem;
        } else {
            // relative access

            MemRelAccess relAccess = (MemRelAccess) access;
            //get the frame from stack
            MemFrame currentFrame = frames.peek();
            //calculate the actual depth of the variable 
            int actualDepth = currentFrame.depth - relAccess.depth;
            MemTemp fp = currentFrame.FP; //save the frame pointer
            ImcExpr addr = new ImcTEMP(fp); // make a temporary variable of address

            //for each depth we calculate the value of ImcMEM on the address
            for(int i = 0; i < actualDepth; i++) {
                addr = new ImcMEM(addr);
            }

            //calculate the address
            ImcBINOP calculateAddr = new ImcBINOP(ImcBINOP.Oper.ADD, addr, new ImcCONST(relAccess.offset));
            mem = new ImcMEM(calculateAddr);
        }
        ImcGen.exprImc.put(nameExpr, mem);
        return mem;
    }

    @Override
    public ImcExpr visit(AstBinExpr binExpr, Stack<MemFrame> frames) {
        ImcExpr returnValue = null;
        
        ImcExpr first = binExpr.fstExpr().accept(this, frames);
        ImcExpr second = binExpr.sndExpr().accept(this, frames);

        String astOperator = binExpr.oper().name();
		ImcBINOP.Oper imcOperator = ImcBINOP.Oper.valueOf(astOperator);

        returnValue = new ImcBINOP(imcOperator, first, second);
        ImcGen.exprImc.put(binExpr,returnValue);

        return returnValue;
    }
    
    @Override
    public ImcExpr visit(AstPfxExpr pfxExpr, Stack<MemFrame> frames) {
        ImcExpr expr = pfxExpr.expr().accept(this, frames);
        
        MemLabel label = null;
        switch(pfxExpr.oper()) {
            case PTR:
                if(!(expr instanceof ImcMEM)) {
                    throw new Report.Error(pfxExpr.location()+"Napačna raba ^ pred izrazom");
                }
                ImcMEM memAccess = (ImcMEM) expr;
                ImcGen.exprImc.put(pfxExpr, memAccess.addr);
                return memAccess.addr;
            case SUB:
                //check if there is a constant, if it is change the sign
                if(expr instanceof ImcCONST) {
                    ImcCONST constant = (ImcCONST) expr;
                    ImcCONST newConstant = new ImcCONST(-constant.value);
                    ImcGen.exprImc.put(pfxExpr, newConstant);
                    return newConstant;
                } else {
                    //if it is not a constant make new ImcUNOP
                    ImcUNOP unOperator = new ImcUNOP(ImcUNOP.Oper.NEG, expr);
                    ImcGen.exprImc.put(pfxExpr, unOperator);
                    return unOperator;
                }
            case ADD:
                ImcGen.exprImc.put(pfxExpr, expr);
                return expr;
            case NOT:
                // negate the boolean value. boolean value is stored as ImcCONST(0) - false and ImcCONST(0) - true
                if(expr instanceof ImcCONST) {
                    ImcCONST constant = (ImcCONST) expr;
                    ImcCONST newConstant = new ImcCONST((constant.value+1)%2);
                    ImcGen.exprImc.put(pfxExpr, newConstant);
                    return newConstant;
                } else {
                    ImcUNOP unOperator = new ImcUNOP(ImcUNOP.Oper.NOT, expr);
                    ImcGen.exprImc.put(pfxExpr, unOperator);
                    return unOperator;
                }
            case NEW:
                //System.out.println("NEW");
                label = new MemLabel("new");

                Vector<Long> offsets1 = new Vector<Long>();
                Vector<ImcExpr> arguments1 = new Vector<ImcExpr>();
                long offset1 = 0;
                offsets1.add(offset1);
                MemFrame functionFrame1 = frames.peek();
                ImcExpr fp1 = new ImcTEMP(functionFrame1.FP);
                arguments1.add(fp1);
                //for SL
                offsets1.add(offset1+new SemPointer(new SemVoid()).size());
                
                //offsets1.add(offsets1.lastElement() + SemAn.ofType.get(pfxExpr.expr()).size());
                arguments1.add(expr);
                ImcCALL call1 = new ImcCALL(label,offsets1,arguments1);
                ImcGen.exprImc.put(pfxExpr, call1);
                return call1;
            case DEL:
                label = new MemLabel("del");

                Vector<Long> offsets = new Vector<Long>();
                Vector<ImcExpr> arguments = new Vector<ImcExpr>();
                long offset2 = 0;
                offsets.add(offset2);
                MemFrame functionFrame = frames.peek();
                ImcExpr fp = new ImcTEMP(functionFrame.FP);
                arguments.add(fp);
                //for SL
                offsets.add(offset2+new SemPointer(new SemVoid()).size());
                
                //offsets.add(offsets.lastElement() + SemAn.ofType.get(pfxExpr.expr()).size());
                arguments.add(expr);
                ImcCALL call = new ImcCALL(label,offsets,arguments);
                ImcGen.exprImc.put(pfxExpr, call);
                return call;
            default:
                throw new Report.Error("Something is wrong with the pfx operator");
        }
        
    }

    @Override
    public ImcExpr visit(AstSfxExpr sfxExpr, Stack<MemFrame> frames) {
        ImcExpr sfx = sfxExpr.expr().accept(this, frames);
        ImcMEM returnValue = new ImcMEM(sfx);
        ImcGen.exprImc.put(sfxExpr, returnValue);
        return returnValue;
    }

    @Override
    public ImcExpr visit(AstRecExpr recExpr, Stack<MemFrame> frames) {
        //visit the record
        ImcMEM record = (ImcMEM) recExpr.rec().accept(this, frames);
        // get the declaration of the component so that you can get RelAccess
        AstCompDecl component = (AstCompDecl) SemAn.declaredAt.get(recExpr.comp());
        
        MemRelAccess relAccess = (MemRelAccess) Memory.accesses.get(component);
        
        ImcBINOP calculateAddr = new ImcBINOP(ImcBINOP.Oper.ADD, record.addr, new ImcCONST(relAccess.offset));
        ImcMEM recVal = new ImcMEM(calculateAddr);
        ImcGen.exprImc.put(recExpr, recVal);
        return recVal;
    }

    @Override
    public ImcExpr visit(AstArrExpr arrExpr, Stack<MemFrame> frames) { 
        ImcMEM arrStartAddr = (ImcMEM) arrExpr.arr().accept(this, frames);
        ImcExpr index = arrExpr.idx().accept(this, frames);

        SemType arrType = SemAn.ofType.get(arrExpr.arr());
        
        ImcCONST sizeOfType = new ImcCONST(((SemArray) arrType.actualType()).elemType().size());
        ImcBINOP calculateIndexOffset = new ImcBINOP(ImcBINOP.Oper.MUL, index, sizeOfType);

        ImcBINOP calculateIndexAddr = new ImcBINOP(ImcBINOP.Oper.ADD, arrStartAddr.addr, calculateIndexOffset);
        ImcMEM mem = new ImcMEM(calculateIndexAddr);
        ImcGen.exprImc.put(arrExpr, mem);
        return mem;
    }

    @Override
    public ImcExpr visit(AstCallExpr callExpr, Stack<MemFrame> frames) {
        //get function declaration so that you can access function frame
        AstFunDecl funDecl = (AstFunDecl) SemAn.declaredAt.get(callExpr);
        //get function frame
        MemFrame funcFrame = Memory.frames.get(funDecl);
        MemFrame upperFunctionFrame = frames.peek();

        Vector<Long> offsets = new Vector<Long>();
        Vector<ImcExpr> arguments = new Vector<ImcExpr>();
         /*
            The first argument will always be the SL -> first offset will be 8
        */
        MemTemp fp = upperFunctionFrame.FP;
        ImcExpr sl;
        long off = 0;
        offsets.add(off);
        off += new SemPointer(new SemVoid()).size();
        //if function that is called is directly under the function that we are in, then it sends to the calling function SL as ImcTEMP(FP)
        if(funcFrame.depth == 1) {
            //offsets.add(new SemPointer(new SemVoid()).size());
            sl = new ImcCONST(1984);
            arguments.add(sl);
        } else {
            sl = new ImcTEMP(fp);
            //ImcMEM addr = new ImcMEM(fp);
            long depthDiff = funcFrame.depth-upperFunctionFrame.depth;
            /*for(long i = 0; i < depthDiff; i++) {
                addr = new ImcMEM(addr);
            }*/
            for(int i = 1; i> depthDiff; i--) {
                sl = new ImcMEM(sl);
            }
            arguments.add(sl);
        }
        /*
            The first argument will always be the SL
        */

        // get the offsets of the arguments and also store the calculated arguments
        if(callExpr.args() != null) {
            for(AstExpr argument : callExpr.args()) {
                ImcExpr argResult = argument.accept(this, frames);
                
                SemType argType = SemAn.ofType.get(argument);
                //long offset = offsets.lastElement()+argType.size();

                //add to vectors
                offsets.add(off);
                arguments.add(argResult);

                off += argType.size();

            }
        }

        MemLabel funLabel = Memory.frames.get(funDecl).label;

        ImcCALL funcCall = new ImcCALL(funLabel, offsets, arguments);
        ImcGen.exprImc.put(callExpr, funcCall);
        return funcCall;
        
    }

    @Override
    public ImcExpr visit(AstCastExpr castExpr, Stack<MemFrame> frames) {
        ImcExpr cast = castExpr.expr().accept(this, frames);

        if(SemAn.isType.get(castExpr.type()).actualType() instanceof SemChar) {
            cast = new ImcBINOP(ImcBINOP.Oper.MOD, cast, new ImcCONST(256));
        }

        ImcGen.exprImc.put(castExpr, cast);
        return cast;
    }

    @Override
	public ImcExpr visit(AstStmtExpr stmtExpr, Stack<MemFrame> frames) {
        Vector<ImcStmt> stmts = new Vector<ImcStmt>();
        ImcStmt last = null;
        ImcStmt sndLast = null;
        int counter = 0;
        for(AstStmt statement : stmtExpr.stmts()) {
            sndLast = last;
            last = statement.accept(new StmtGenerator(),frames);
            if(counter > 0) {
                stmts.add(sndLast);
            }
            counter++;   
        }

        
        // checking if the last statement is expr or stmt
        ImcExpr expr = new ImcCONST(198011);
        if(last instanceof ImcESTMT) {
            expr = ((ImcESTMT) last).expr;
        }else {
            stmts.add(last);
        }
        ImcSTMTS allStmts = new ImcSTMTS(stmts);
        //finaly create a statement expression and put it in exprImc
        ImcSEXPR newStmtExpr = new ImcSEXPR(allStmts, expr);
        ImcGen.exprImc.put(stmtExpr, newStmtExpr);
        return newStmtExpr;

    }

    @Override
	public ImcExpr visit(AstWhereExpr whereExpr, Stack<MemFrame> frames) {
        whereExpr.decls().accept(new CodeGenerator(), frames);
        ImcExpr returnValue = whereExpr.expr().accept(this, frames);
        ImcGen.exprImc.put(whereExpr, returnValue);
        return returnValue;
    }
}