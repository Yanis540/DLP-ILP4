package com.paracamplus.ilp4.ilp4tme10.interpreter;

import java.util.Set;

import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTvisitor;
import com.paracamplus.ilp4.interfaces.IASTprogram;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {
    private Set<String> globals;
    public Interpreter (IGlobalVariableEnvironment globalVariableEnvironment,
        IOperatorEnvironment operatorEnvironment,IClassEnvironment classEnvironment 
    ) {
		super(globalVariableEnvironment, operatorEnvironment,classEnvironment);
        
	}
    @Override 
    public Object visit(com.paracamplus.ilp1.interfaces.IASTprogram iast, ILexicalEnvironment lexenv) throws EvaluationException {
    	return visit((IASTprogram)iast, lexenv);
    }
    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        GlobalVariableCollector gvc = new GlobalVariableCollector();
        gvc.visit(iast);
        this.globals = gvc.globals;
        return super.visit(iast,lexenv);
    }
    @Override
    public Object visit(IASTexists iast, ILexicalEnvironment lexenv) throws EvaluationException {
        IASTvariable variable = iast.getVariable();
        try{
            // getting in the local env 
            lexenv.getValue(variable);
            return Boolean.TRUE;
        }
        catch(EvaluationException e){
            // super.globalVariableEnvironment.getGlobalVariableValue(var)
            if(globals.contains(variable.getName())==true || getGlobalVariableEnvironment().getGlobalVariableValue(variable.getName())!=null)
                return Boolean.TRUE;
            else 
                return Boolean.FALSE; 

        }
    }
    
}
