package prev.phase.seman;

import prev.common.report.*;
import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.tree.stmt.*;
import prev.data.ast.visitor.*;
import prev.data.semtype.*;
import java.util.ArrayList;
import java.util.HashMap;

public class TypeResolver extends AstFullVisitor <Object, TypeResolver.Mode> {

    public enum Mode {
        FIRST, SECOND, THIRD, FOURTH, FIFTH
    }

    private HashMap<SemRecord, SymbTable> symbolTables = new HashMap<>();

    // GENERAL PURPOSE

    public Object visit(AstTrees<?> trees, Mode arg) {
        //System.out.println("FIRST:");
        for (AstTree tree : trees) {
            if(tree instanceof AstTypeDecl) {
                tree.accept(this, Mode.FIRST);
            }
        }
        //System.out.println("SECOND:");
        
        for (AstTree tree : trees) {
            
            if(tree instanceof AstTypeDecl)
                tree.accept(this, Mode.SECOND);
        }
        //System.out.println("THIRD:");
        for (AstTree tree : trees) {
            
            if(tree instanceof AstVarDecl | tree instanceof AstTypeDecl)
                tree.accept(this, Mode.THIRD);
        }
        //System.out.println("FOURTH:");
        for (AstTree tree : trees) {
            
            if(tree instanceof AstFunDecl)
                tree.accept(this, Mode.FOURTH);
        }
        //System.out.println("FIFTH:");
        for (AstTree tree : trees) {
            
            if(tree instanceof AstFunDecl)
                tree.accept(this, Mode.FIFTH);
        }
        //System.out.println("END");
        return null;
    }
    /*
        TYPE DECLARATIONS
    */
    @Override
    public Object visit(AstTypeDecl typeDecl, Mode mode) {
        switch (mode) {
            case FIRST:
                
                SemAn.declaresType.put(typeDecl, new SemName(typeDecl.name()));
                
                //typeDecl.type().accept(this,Mode.FIRST);
                break;
        
            case SECOND:
                /*
                    First we have to visit all nodes under the typeDecl
                */
                typeDecl.type().accept(this, mode);
                /*
                    First we get the declaration of the type from the step one from the declaresType 
                    table then we define the type by looking into the isType table
                */
                
                SemAn.declaresType.get(typeDecl).define(SemAn.isType.get(typeDecl.type()));
                
                break;
            case THIRD:
                typeDecl.type().accept(this, mode);
            default:
                break;
        }
        return null;
    }
    
    @Override
    public Object visit(AstFunDecl funDecl, Mode mode){
        switch(mode) {
            case FOURTH:
                for(AstParDecl param : funDecl.pars()) {
                    param.accept(this, mode);
                } 
                funDecl.type().accept(this, Mode.SECOND);
                SemType funcType = SemAn.isType.get(funDecl.type());
                if(!(funcType.actualType() instanceof SemInteger | funcType.actualType() instanceof SemChar | funcType.actualType() instanceof SemVoid | 
                    funcType.actualType() instanceof SemBoolean | funcType.actualType() instanceof SemPointer)) {
                    throw new Report.Error("Function of invalid type");
                }

                break;
            case FIFTH:
                funDecl.expr().accept(this, mode);
                
                if(SemAn.isType.get(funDecl.type()).actualType().getClass() != SemAn.ofType.get(funDecl.expr()).actualType().getClass()){
                    throw new Report.Error(funDecl.name()+"Function type and expression type do not match");
                }
                break;
            default:
                break;
        }
        return null;
    }
    
    @Override
    public Object visit(AstParDecl parDecl, Mode mode) {
        switch(mode){
            case FOURTH:
            
                parDecl.type().accept(this, Mode.SECOND);
                
                SemType parType = SemAn.isType.get(parDecl.type());
                
                if(!(parType.actualType() instanceof SemInteger | parType.actualType() instanceof SemChar | parType.actualType() instanceof SemBoolean 
                    | parType.actualType() instanceof SemPointer)){
                        
                    throw new Report.Error("wrong type of parameter");
                }
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstArrType arrTyp, Mode mode) {
        switch(mode) {
            case SECOND:
                
                arrTyp.elemType().accept(this, mode);
                
                arrTyp.numElems().accept(this, mode);
                
                if((SemAn.isType.get(arrTyp.elemType()) instanceof SemName && ((SemName)SemAn.isType.get(arrTyp.elemType())).type() != null) && (SemAn.isType.get(arrTyp.elemType()).actualType() instanceof SemVoid))  {
                    throw new Report.Error("Array type cannot be of the type void");
                    //SemArray realArrType = new SemArray()
                }
                if(!(arrTyp.numElems() instanceof AstAtomExpr) && ((AstAtomExpr) arrTyp.numElems()).type() == AstAtomExpr.Type.INTEGER) {
                    throw new Report.Error("Expression in array decl has to be of type integer");
                }

                long numElems = Long.parseLong(((AstAtomExpr) arrTyp.numElems()).value());
                if (numElems > Math.pow(2, 63)) {
                    throw new Report.Error("The integer in array expression is too big");
                }
                else if (numElems <= 0) {
                    throw new Report.Error("The integer in array expression has to be a positive number");
                }
                
                SemAn.isType.put(arrTyp, new SemArray(SemAn.isType.get(arrTyp.elemType()), numElems));
               
                break;
            case THIRD:
                if(SemAn.isType.get(arrTyp.elemType()).actualType() instanceof SemVoid) {
                    throw new Report.Error("Array type cannot be of the type void");
                }
            default:
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstRecType recTyp, Mode mode) {

        SymbTable symbolTable = new SymbTable();

        switch(mode) {
            case SECOND:
                ArrayList<SemType> types = new ArrayList<SemType>();

                for(AstCompDecl cmp: recTyp.comps()){

                    try {
                        symbolTable.ins(cmp.name(), cmp);
                    } catch (SymbTable.CannotInsNameException __) {
                        throw new Report.Error("Cannot redefine '" + (cmp.name()) + "'.");
                    }

                    cmp.type().accept(this, mode);
                    SemType currentType = SemAn.isType.get(cmp.type());
                    
                    if((currentType instanceof SemName && ((SemName)currentType).type() != null) && (currentType.actualType() instanceof SemVoid)){
                        throw new Report.Error("Record type cannot include a type void");
                    } else {
                        types.add(currentType);
                    }
                }
                SemRecord recType = new SemRecord(types);
                symbolTables.put(recType, symbolTable);
                SemAn.isType.put(recTyp, recType);
                break;
            case THIRD:
               
                for(AstCompDecl cmp: recTyp.comps()){
                    SemType currentType = SemAn.isType.get(cmp.type());
                    if(currentType.actualType() instanceof SemVoid){
                        throw new Report.Error("Record type cannot include a type void");
                    }
                }
                break;
            default:
                break;
        }  
        return null;
    }

    @Override
    public Object visit(AstAtomType atomType, Mode mode) {
        switch(mode) {
            case SECOND:
                switch(atomType.type()) {
                    case VOID: 
                        SemAn.isType.put(atomType, new SemVoid());
                        break;
                    case INTEGER:
                        SemAn.isType.put(atomType, new SemInteger());
                        break;
                    case CHAR:
                        SemAn.isType.put(atomType, new SemChar());
                        break;
                    case BOOLEAN:
                        SemAn.isType.put(atomType, new SemBoolean());
                        break;
                    default:
                        throw new Report.InternalError();
                }
                break;
            default:
                break;
        }

        return null;
    }
    @Override
    public Object visit(AstNameType nameType, Mode mode) {
        switch(mode) {
            case SECOND:
                AstDecl decl = SemAn.declaredAt.get(nameType);

                if(!(decl instanceof AstTypeDecl)) 
                    throw new Report.Error(nameType.name() + " is not a type.");
                
                SemAn.isType.put(nameType, SemAn.declaresType.get((AstTypeDecl)decl));
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstPtrType ptrType, Mode mode) {
        switch(mode) {
            case SECOND:
                ptrType.baseType().accept(this, mode);
                SemType newBaseType = SemAn.isType.get(ptrType.baseType());
                
                SemAn.isType.put(ptrType, new SemPointer(newBaseType));
               
                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstVarDecl varDecl, Mode mode) {

        switch(mode) {

            case SECOND:
                /*varDecl.type().accept(this, mode);
                */
                break;
            case THIRD:
                varDecl.type().accept(this, Mode.SECOND);
                
                if(SemAn.isType.get(varDecl.type()).actualType() instanceof SemVoid){
                    throw new Report.Error("Variable cannot be of the type void");
                }

                //SemAn.ofType.put(varDecl, SemAn.isType.get(varDecl.type()));

                break;
            default:
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstAtomExpr atomExpr, Mode mode) {
        //switch(mode) {
            //case SECOND:
                switch(atomExpr.type()) {
                    case VOID:
                        SemAn.ofType.put(atomExpr, new SemVoid());
                        break;
                    case CHAR:
                        SemAn.ofType.put(atomExpr, new SemChar());
                        break;
                    case INTEGER:
                        SemAn.ofType.put(atomExpr, new SemInteger());
                        break;
                    case BOOLEAN:
                        SemAn.ofType.put(atomExpr, new SemBoolean());
                        break;
                    case POINTER:
                        SemAn.ofType.put(atomExpr, new SemPointer(new SemVoid()));
                        break;
                    case STRING:
                        SemAn.ofType.put(atomExpr, new SemPointer(new SemChar()));
                        break;
                    default:
                        break;
                }
                /*break;
            default:
                break;
        }*/
        return null;
    }

    @Override
    public Object visit(AstRecExpr recExpr, Mode mode) {
        recExpr.rec().accept(this, mode);
        
        if(!(SemAn.ofType.get(recExpr.rec()).actualType() instanceof SemRecord)){
            throw new Report.Error("record expression is not of record type!");
        }
        SymbTable symbolTable = symbolTables.get(SemAn.ofType.get(recExpr.rec()).actualType());
        
        try {
            AstDecl recTypeDecl = symbolTable.fnd(recExpr.comp().name());
            if (!(recTypeDecl instanceof AstCompDecl))
                throw new Report.Error("Record type does not include "+"'" + (recExpr.comp().name()) + "' !");
            
            AstCompDecl cmpDecl = (AstCompDecl) recTypeDecl;
            AstType componentType = cmpDecl.type();

            SemAn.declaredAt.put(recExpr.comp(), recTypeDecl);

            SemAn.ofType.put(recExpr, SemAn.isType.get(componentType));

            return null;
            
        } catch (SymbTable.CannotFndNameException __) {
            throw new Report.Error("Record has no component named " + (recExpr.comp().name()) + "!");
        }

    }
    @Override
    public Object visit(AstSfxExpr sfxExpr, Mode mode) {
        sfxExpr.expr().accept(this,mode);
        if(!(SemAn.ofType.get(sfxExpr.expr()).actualType() instanceof SemPointer)) {
            throw new Report.Error("before '^' there should be an expression of the type pointer!");
        }
       
        SemAn.ofType.put(sfxExpr, ((SemPointer) SemAn.ofType.get(sfxExpr.expr()).actualType()).baseType().actualType());
        return null;
    }

    @Override
    public Object visit(AstPfxExpr prefixExpr, Mode mode) {

        prefixExpr.expr().accept(this, mode);

        switch(prefixExpr.oper()) {
            case NOT:
                if(SemAn.ofType.get(prefixExpr.expr()).actualType() instanceof SemBoolean) {
                    SemAn.ofType.put(prefixExpr, new SemBoolean());
                }
                else {
                    throw new Report.Error("After '!' there should be a boolean");
                }
                break;
            case ADD:
                if(SemAn.ofType.get(prefixExpr.expr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(prefixExpr, new SemInteger());
                } else {
                    throw new Report.Error("After '+' there should be an integer");
                }
                break;
            case SUB:
                if(SemAn.ofType.get(prefixExpr.expr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(prefixExpr, new SemInteger());
                } else {
                    throw new Report.Error("After '-' there should be an integer");
                }
                break;
            case PTR:
                //System.out.println("pointer");
                SemType typeOfPointer = SemAn.ofType.get(prefixExpr.expr());
                //System.out.println(typeOfPointer);
                SemAn.ofType.put(prefixExpr, new SemPointer(typeOfPointer));
                break;
            case NEW:
                if(SemAn.ofType.get(prefixExpr.expr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(prefixExpr, new SemPointer(new SemVoid()));
                } else {
                    throw new Report.Error("After 'new' there should be an integer");
                }
                break;
            case DEL:
                //System.out.println(SemAn.ofType.get(prefixExpr.expr()).actualType());
                if(SemAn.ofType.get(prefixExpr.expr()).actualType() instanceof SemPointer){
                    SemAn.ofType.put(prefixExpr, new SemVoid());
                } else {
                    throw new Report.Error(prefixExpr.location()+"After 'del' there should be a pointer");
                }
                break;
            default: break;
        }

        return null;

    }

    @Override
    public Object visit(AstBinExpr binExpr, Mode mode) {

        binExpr.fstExpr().accept(this, mode);
        binExpr.sndExpr().accept(this, mode);

        switch(binExpr.oper()) {
            case OR:
                if(SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemBoolean && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemBoolean){
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '|' !");
                }
                break;
            case AND:
                if(SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemBoolean && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemBoolean){
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '&' !");
                }
                break;
            case ADD:
                
                if(SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(binExpr, new SemInteger());
                } else {
                    throw new Report.Error("there should be integer values on the both sides of '+' !");
                }
                break;
            case SUB:
                if(SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(binExpr, new SemInteger());
                } else {
                    throw new Report.Error("there should be integer values on the both sides of '-' !");
                }
                break;
            case MUL:
                if(SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(binExpr, new SemInteger());
                } else {
                    throw new Report.Error("there should be integer values on the both sides of '*' !");
                }
                break;
            case DIV:
                if(SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(binExpr, new SemInteger());
                } else {
                    throw new Report.Error("there should be integer values on the both sides of '/' !");
                }
                break;
            case MOD:
                if(SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger){
                    SemAn.ofType.put(binExpr, new SemInteger());
                } else {
                    throw new Report.Error("there should be integer values on the both sides of '%' !");
                }
                break;
            case EQU:
                if((SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemChar && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemChar) | 
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemBoolean && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemBoolean) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemPointer && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemPointer)) {
                    
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '==' !");
                }
                break;
            case NEQ:
                if((SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemChar && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemChar) | 
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemBoolean && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemBoolean) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemPointer && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemPointer)) {
                    
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '!=' !");
                }
                break;
            case LTH:
                if((SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemChar && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemChar) | 
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemPointer && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemPointer)) {
                    
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '<' !");
                }
                break;
            case GTH:
                if((SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemChar && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemChar) | 
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemPointer && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemPointer)) {
                    
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '>' !");
                }
                break;
            case LEQ:
                if((SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemChar && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemChar) | 
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemPointer && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemPointer)) {
                    
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '<=' !");
                }
                break;
            case GEQ:
                if((SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemInteger && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemInteger) |
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemChar && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemChar) | 
                    (SemAn.ofType.get(binExpr.fstExpr()).actualType() instanceof SemPointer && SemAn.ofType.get(binExpr.sndExpr()).actualType() instanceof SemPointer)) {
                    
                    SemAn.ofType.put(binExpr, new SemBoolean());
                } else {
                    throw new Report.Error("there should be boolean values on the both sides of '>=' !");
                }
                break;
        }
        return null;
    }

    @Override
    public Object visit(AstArrExpr arrExpr, Mode mode) {

        arrExpr.arr().accept(this, mode);
        arrExpr.idx().accept(this, mode);

        if(!(SemAn.ofType.get(arrExpr.arr()).actualType() instanceof SemArray)) {
            throw new Report.Error("Before [] there should be variable of type Array");
        }
        if(!(SemAn.ofType.get(arrExpr.idx()).actualType() instanceof SemInteger)) {
            throw new Report.Error("inside [] there should be an expression of type integer");
        }
        
        SemAn.ofType.put(arrExpr, ((SemArray)SemAn.ofType.get(arrExpr.arr()).actualType()).elemType());
        return null;
    }

    @Override
    public Object visit(AstCastExpr castExpr, Mode mode){

        castExpr.expr().accept(this,mode);
        castExpr.type().accept(this,Mode.SECOND);

        SemType exprType = SemAn.ofType.get(castExpr.expr());
        
        SemType typeType = SemAn.isType.get(castExpr.type());
        
        if(!(exprType.actualType() instanceof SemInteger | exprType.actualType() instanceof SemChar | exprType.actualType() instanceof SemPointer)){
            throw new Report.Error(castExpr.location()+"Expression in type casting has to be of type integer, char or pointer");
        }
        if(!(typeType.actualType() instanceof SemInteger | typeType.actualType() instanceof SemChar | typeType.actualType() instanceof SemPointer)){
            throw new Report.Error(castExpr.location()+"Expression in type casting has to be of type integer, char or pointer");
        }

        SemAn.ofType.put(castExpr, typeType);

        return null;
    }

    @Override
    public SemType visit(AstCallExpr functionCall, Mode mode) {
        AstDecl funcDecl = SemAn.declaredAt.get(functionCall);

        if(!(funcDecl instanceof AstFunDecl)) {
            throw new Report.Error(functionCall.name() + "should be a function!");
        }
        AstFunDecl functionDecl = (AstFunDecl) funcDecl;
        // dobr je prevert najprej če je sploh isto število argumentov zato da pol ne iščeš s prevelikim indeksom in dobiš error
        int noOfArgs = 0;
        if(functionCall.args() != null) {
            noOfArgs = functionCall.args().size();
        } 
        int noOfPars = 0;
        if(functionDecl.pars() != null) {
            noOfPars = functionDecl.pars().size();
        }

        if(noOfArgs != noOfPars){
            throw new Report.Error("Wrong number of arguments in a function call "+functionCall.name());
        }
        //za vsak argument preveriš če je istiga tipa kt tip vsakega parametra pri definiciji funkcije
        for(int i = 0; i < noOfArgs; i++) {
            AstExpr argument = functionCall.args().get(i);
            argument.accept(this, mode);
            SemType argType = SemAn.ofType.get(argument);

            AstParDecl parDecl = functionDecl.pars().get(i);
            SemType parType = SemAn.isType.get(parDecl.type());

            if(argType.actualType().getClass() != parType.actualType().getClass()) {
                throw new Report.Error("Argument of invalid type");
            }
        }
        //ker računamo type damo SECOND ker majo vsi tipi notr switch kdaj računajo tip (pri second)
        functionDecl.type().accept(this, Mode.SECOND);
        SemAn.ofType.put(functionCall, SemAn.isType.get(functionDecl.type()));

        return null;
    }

    @Override
    public Object visit(AstNameExpr nameExpr, Mode mode) {
        if(SemAn.declaredAt.get(nameExpr) instanceof AstVarDecl){
            AstVarDecl declAt = (AstVarDecl) SemAn.declaredAt.get(nameExpr);
            SemType exprType = SemAn.isType.get(declAt.type());
            SemAn.ofType.put(nameExpr, exprType);
        }else if(SemAn.declaredAt.get(nameExpr) instanceof AstParDecl){
            AstParDecl declAt = (AstParDecl)SemAn.declaredAt.get(nameExpr);
            SemType exprType = SemAn.isType.get(declAt.type());
            SemAn.ofType.put(nameExpr, exprType);
        } else if(SemAn.declaredAt.get(nameExpr) instanceof AstCompDecl) {
            AstCompDecl declAt = (AstCompDecl)SemAn.declaredAt.get(nameExpr);
            SemType exprType = SemAn.isType.get(declAt.type());
            SemAn.ofType.put(nameExpr, exprType);
        } else {
            throw new Report.Error(nameExpr, "Unexpected type in name expression.");
        }
        /*SemType exprType = SemAn.isType.get(declAt.type());
        SemAn.ofType.put(nameExpr, exprType);*/
        return null;
    }

    @Override
    public Object visit(AstStmtExpr stmtExpr, Mode mode) {
        SemType statementsType = null;
        for(AstStmt stmt : stmtExpr.stmts()){
            stmt.accept(this, mode);
            statementsType = SemAn.ofType.get(stmt);
        }
        SemAn.ofType.put(stmtExpr, statementsType);
        return null;
    }

    @Override
    public Object visit(AstAssignStmt asignStmt, Mode mode) {
        asignStmt.dst().accept(this, mode);
        asignStmt.src().accept(this, mode);

        if((SemAn.ofType.get(asignStmt.dst()).actualType() instanceof SemInteger && SemAn.ofType.get(asignStmt.src()).actualType() instanceof SemInteger) |
            (SemAn.ofType.get(asignStmt.dst()).actualType() instanceof SemChar && SemAn.ofType.get(asignStmt.src()).actualType() instanceof SemChar) | 
            (SemAn.ofType.get(asignStmt.dst()).actualType() instanceof SemBoolean && SemAn.ofType.get(asignStmt.src()).actualType() instanceof SemBoolean) |
            (SemAn.ofType.get(asignStmt.dst()).actualType() instanceof SemPointer && SemAn.ofType.get(asignStmt.src()).actualType() instanceof SemPointer)) {
            
            SemAn.ofType.put(asignStmt, new SemVoid());
        }
        return null;
    }

    @Override
    public Object visit(AstIfStmt ifStmt, Mode mode) {
        ifStmt.cond().accept(this, mode);
        if(!(SemAn.ofType.get(ifStmt.cond()).actualType() instanceof SemBoolean)) {
            throw new Report.Error("Condition in if statement should be of the type boolean");
        } 

        ifStmt.thenStmt().accept(this, mode);
        ifStmt.elseStmt().accept(this, mode);

        SemAn.ofType.put(ifStmt, new SemVoid());
        return null;
    }


    @Override
    public Object visit(AstWhileStmt whileStmt, Mode mode) {

        whileStmt.cond().accept(this, mode);
        if(!(SemAn.ofType.get(whileStmt.cond()).actualType() instanceof SemBoolean)) {
            throw new Report.Error("Condition in while statement should be of the type boolean");
        } 
        whileStmt.bodyStmt().accept(this,mode);
        SemAn.ofType.put(whileStmt, new SemVoid());
        return null;
    }

    @Override
	public Object visit(AstWhereExpr whereExpr, Mode mode) {
        //System.out.println("WHERE DECLS:");
        whereExpr.decls().accept(this, Mode.SECOND);
        //System.out.println("END OF WHERE DECLS");
        whereExpr.expr().accept(this, mode);
        //System.out.println(SemAn.ofType.get(whereExpr.expr()));
        SemAn.ofType.put(whereExpr, SemAn.ofType.get(whereExpr.expr()));

        return null;
    }

    @Override
    public Object visit(AstExprStmt exprStmt, Mode mode) {
        exprStmt.expr().accept(this, mode);
        SemAn.ofType.put(exprStmt, SemAn.ofType.get(exprStmt.expr()));
        return null;
    }
}