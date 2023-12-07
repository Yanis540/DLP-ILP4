package com.paracamplus.ilp4.partiel2019.interpreter;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.ILPInstance;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.partiel2019.interfaces.IASTtryRessources;
import com.paracamplus.ilp4.partiel2019.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTtryRessources iast, ILexicalEnvironment lexenv) throws EvaluationException {
        ILexicalEnvironment lexenv2 = lexenv; 
        Object v = iast.getExpression().accept(this, lexenv2);
        if(!(v instanceof ILPInstance))
            throw new EvaluationException("Expected ILPinstance but recieved " + v.getClass().getSimpleName());
        ILPInstance i = (ILPInstance)v; 
        lexenv2 = lexenv2.extend(iast.getVariable(),i);
        try{
            Object value = iast.getBody().accept(this, lexenv2);
            i.send(this, "close",new Object[0]);
            return value;
        }
        catch(Exception e){
            i.send(this, "close",new Object[0]);
            throw new EvaluationException(e.getMessage());
        }
    }
    
}
