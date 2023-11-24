package com.paracamplus.ilp4.ilp4tme8.interpreter;

import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProprety;
import com.paracamplus.ilp4.interfaces.IASTfieldRead;
import com.paracamplus.ilp4.interfaces.IASTinstantiation;
import com.paracamplus.ilp4.interpreter.interfaces.IClass;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;

public class Interpreter  extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTreadProprety iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object t = iast.getTarget().accept(this,lexenv);
        Object p = iast.getProprety().accept(this, lexenv);
        if (!( t instanceof ILPInstance ) ) 
            throw new EvaluationException( "Not an ILP instances " + t);
        if(!(p instanceof String))
            throw new EvaluationException("Proprety should be String value but recieved " + p.getClass());
        
        ILPInstance target = (ILPInstance)t;
        String proprety = (String)p; 

        return target.read(proprety);
    }

    @Override
    public Object visit(IASTwriteProprety iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object t = iast.getTarget().accept(this,lexenv);
        Object p = iast.getProprety().accept(this, lexenv);
        Object value = iast.getValue().accept(this, lexenv);
        if (!( t instanceof ILPInstance ) ) 
            throw new EvaluationException( "Not an ILP instances " + t);
        if(!(p instanceof String))
            throw new EvaluationException("Proprety should be String value but recieved " + p.getClass());
        ILPInstance target = (ILPInstance)t;
        String proprety = (String)p; 
        Object oldValue = target.write(proprety, value);
        return oldValue;
    }

    @Override
    public Object visit(IASThasProprety iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object t = iast.getTarget().accept(this,lexenv);
        Object p = iast.getProprety().accept(this, lexenv);
        if (!( t instanceof ILPInstance ) ) 
            throw new EvaluationException( "Not an ILP instances " + t);
        if(!(p instanceof String))
            throw new EvaluationException("Proprety should be String value but recieved " + p.getClass());

        ILPInstance target = (ILPInstance)t;
        String proprety = (String)p; 
        try{
            target.read(proprety);
            return Boolean.TRUE;
        }
        catch(EvaluationException e){
            return Boolean.FALSE; 
        }
    }

    @Override
	public Object visit(IASTinstantiation iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        IClass clazz = getClassEnvironment().getILPClass(iast.getClassName());
        List<Object> args = new Vector<Object>();
        for ( IASTexpression arg : iast.getArguments() ) {
            Object value = arg.accept(this, lexenv);
            args.add(value);
        }
        return new ILPInstance(clazz, args.toArray());
    }    
     
    @Override
	public Object visit(IASTfieldRead iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        String fieldName = iast.getFieldName();
        Object target = iast.getTarget().accept(this, lexenv);
        if ( target instanceof ILPInstance ) {
            return ((ILPInstance) target).read(fieldName);
        } else {
            String msg = "Not an ILP instance " + target;
            throw new EvaluationException(msg);
        }
    }
    

    
}
