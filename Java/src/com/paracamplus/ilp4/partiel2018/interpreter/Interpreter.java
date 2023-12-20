package com.paracamplus.ilp4.partiel2018.interpreter;


import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTprogram;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTvisitor;
import com.paracamplus.ilp4.partiel2018.interfaces.IFunction;

public class Interpreter  extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {
    List<IFunction> functions = new ArrayList<>();
    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }
    public Object visit(com.paracamplus.ilp1.interfaces.IASTprogram iast, ILexicalEnvironment lexenv) throws EvaluationException {
    	return visit((IASTprogram)iast, lexenv);
    }

    public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
            Object f = this.visit(fd, lexenv);
            functions.add((IFunction)f);
            String v = fd.getName();
            getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
        }
        try {
            Object value = iast.getBody().accept(this, lexenv);
            printFunctionCallsCount();
            return value; 
       } catch (Exception exc) {
            return exc;
        }
    }

    public void printFunctionCallsCount(){
        for(IFunction func : functions){
            if(func.isCountable())
                System.out.println("Function : "+func.getFunctionName()+" Got called : "+func.getCallsCount()+" times");
            else
                System.out.println("Function : "+func.getFunctionName()+" is not countable");
        }
    }

    public IFunction visit(IASTfunctionDefinition iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {

        IFunction fun = new Function(
            iast.getVariables(),
            iast.getBody(),
            new EmptyLexicalEnvironment(),
            iast.isCountable(), 
            iast.getName()
        );
        return fun;
    }

    @Override
	public Object visit(IASTinvocation iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        Object func = iast.getFunction().accept(this, lexenv);  
        if(func instanceof IFunction)
                ((IFunction)func).incrementCount();
        return super.visit(iast, lexenv);


    }
    
}
