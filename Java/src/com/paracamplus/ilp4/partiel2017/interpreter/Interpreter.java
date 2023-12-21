package com.paracamplus.ilp4.partiel2017.interpreter;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.partiel2017.interfaces.IASTswitch;
import com.paracamplus.ilp4.partiel2017.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTswitch iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object argValue = iast.getArgument().accept(this, lexenv);
        
        IASTexpression[] cases = iast.getCases();
        IASTexpression[] casesBody = iast.getCasesBody();
        if(cases.length!=casesBody.length)
            throw new EvaluationException("Should not occur");
        int i = 0 ;
        for(IASTexpression cas : cases ){
            Object caseValue = cas.accept(this, lexenv);
            if(!caseValue.equals(argValue) ){
                i++;
                continue ; 
            }
            IASTexpression casBody = casesBody[i]; 
            return casBody.accept(this, lexenv);
        }
        IASTexpression otherwise = iast.getOtherwise();
        if(otherwise == null)
            return Boolean.FALSE; 
         
        return otherwise.accept(this, lexenv); 
    }
    
}
