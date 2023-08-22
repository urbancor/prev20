package prev.phase.memory;

import prev.data.ast.tree.*;
import prev.data.ast.tree.decl.*;
import prev.data.ast.tree.expr.*;
import prev.data.ast.tree.type.*;
import prev.data.ast.visitor.*;
import prev.data.semtype.*;
import prev.data.mem.*;
import prev.phase.seman.*;

/**
 * Computing memory layout: frames and accesses.
 */
public class MemEvaluator extends AstFullVisitor<Object, MemEvaluator.Context> {

	/**
	 * The context {@link MemEvaluator} uses while computing function frames and
	 * variable accesses.
	 */
	protected abstract class Context {
	}

	/**
	 * Functional context, i.e., used when traversing function and building a new
	 * frame, parameter acceses and variable acceses.
	 */
	private class FunContext extends Context {
		public int depth = 0;
		public long locsSize = 0;
		public long argsSize = 0;
		public long parsSize = new SemPointer(new SemVoid()).size();
	}

	/**
	 * Record context, i.e., used when traversing record definition and computing
	 * record component acceses.
	 */
	private class RecContext extends Context {
		public long compsSize = 0;
	}

	// TODO
	@Override
	public Object visit(AstAtomExpr atomExpr, Context context) {
		if(atomExpr.type() == AstAtomExpr.Type.STRING){
			int length = atomExpr.value().length();
			long size = length * ((new SemChar()).size()-1); //size in SemChar.java is 8 
			MemAbsAccess absAccess = new MemAbsAccess(size, new MemLabel(), atomExpr.value());
			Memory.strings.put(atomExpr, absAccess);

		}
		return null;
	}

	@Override
	public Object visit(AstPfxExpr pfxExpr, Context context) {
		if(pfxExpr.oper() ==  AstPfxExpr.Oper.NEW || pfxExpr.oper()== AstPfxExpr.Oper.DEL) {
			//System.out.println("NEKI1");
			if(context instanceof FunContext) {
				//System.out.println("NEKI2");
				FunContext funContext = (FunContext) context;
				long pfxArgsSize = 16;
				funContext.argsSize = Math.max(funContext.argsSize,pfxArgsSize);
			}
		}

		return null;
	}

	@Override
	public Object visit(AstFunDecl funDecl, Context context) {

		FunContext newFunContext = new FunContext();
		MemLabel labela;
		if(context != null) {
			newFunContext.depth = ((FunContext)context).depth+1;
			labela = new MemLabel();
		} else {
			newFunContext.depth = 1;
			labela= new MemLabel(funDecl.name());
		}

		if(funDecl.pars() != null) {
			//funDecl.pars().accept(this, context);
			
			for(AstParDecl par : funDecl.pars()) {
				par.accept(this, newFunContext);
			}
		}
		funDecl.type().accept(this, newFunContext);
		funDecl.expr().accept(this, newFunContext);
		/*long argumentsSize = 0;
		if(newFunContext.argsSize > 0) {
			argumentsSize = newFunContext.argsSize + new SemPointer(new SemVoid()).size();
		}*/
		
		MemFrame memFrame = new MemFrame(labela, newFunContext.depth, newFunContext.locsSize, newFunContext.argsSize);
		Memory.frames.put(funDecl, memFrame);
		return null;
	}

	@Override
	public Object visit(AstParDecl parDecl, Context context) {
		parDecl.type().accept(this, context);

		/*if(!(context instanceof FunContext)) {
			throw new Report.Error("Parameter declaration should recieve a function context");
		}*/
		FunContext funContext = (FunContext) context;

		long sizeOfType = SemAn.isType.get(parDecl.type()).size();
		MemRelAccess relAccess = new MemRelAccess(sizeOfType, funContext.parsSize, funContext.depth);
		Memory.accesses.put(parDecl, relAccess);

		funContext.parsSize += sizeOfType;
		return null;
	}

	@Override
	public Object visit(AstVarDecl varDecl, Context context) {

		varDecl.type().accept(this, context);
		long sizeOfType = SemAn.isType.get(varDecl.type()).size();
		if(context != null) {
			//je potem funkcijski kontekst
			//System.out.println("Context ni null"+varDecl.name());
			FunContext funContext = (FunContext) context;
			
			MemRelAccess relAccess = new MemRelAccess(sizeOfType, -funContext.locsSize-sizeOfType, funContext.depth);
			Memory.accesses.put(varDecl, relAccess);
			funContext.locsSize += sizeOfType;
		} else {
			//System.out.println("Context je null"+varDecl.name());
			MemAbsAccess absAccess = new MemAbsAccess(sizeOfType, new MemLabel(varDecl.name()));
			Memory.accesses.put(varDecl, absAccess);
		}

		return null;
	}

	@Override
	public Object visit(AstRecType recType, Context context) {

		RecContext recContext = new RecContext();



		for(AstCompDecl comp : recType.comps()) {
			comp.accept(this, recContext);
		}
		//MemAbsAccess absAccess = new MemAbsAccess(recContext.compsSize, new MemLabel());
		return null;

	}
	@Override
	public Object visit(AstCompDecl compDecl, Context context) {

		compDecl.type().accept(this, context);
		RecContext recContext = (RecContext)context;

		long sizeOfType = SemAn.isType.get(compDecl.type()).size();
		MemRelAccess relAccess = new MemRelAccess(sizeOfType, recContext.compsSize, 0);
		recContext.compsSize += sizeOfType;
		Memory.accesses.put(compDecl, relAccess);
		return null;
	}

	@Override
	public Object visit(AstCallExpr callExpr, Context context) {
		FunContext funContext = (FunContext) context;
		if(callExpr.args() != null) {
			callExpr.args().accept(this, context);
			long args = 0;
			for(AstExpr arg : callExpr.args()) {
				long sizeOfType = SemAn.ofType.get(arg).size();
				args += sizeOfType;
			}

			funContext.argsSize = Math.max(funContext.argsSize, args+new SemPointer(new SemVoid()).size());
			
		}

		return null;
	}

}