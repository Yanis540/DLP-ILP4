package com.paracamplus.partiels.finale2021.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.partiels.finale2021.interfaces.IASTmatch;
import com.paracamplus.partiels.finale2021.interfaces.IASTtag;
import com.paracamplus.partiels.finale2021.interfaces.IASTvisitor;
import com.paracamplus.partiels.finale2021.interfaces.ISum;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTtag iast, ILexicalEnvironment lexenv) throws EvaluationException {
        List<Object> values = new ArrayList<>();
        for(IASTexpression expr : iast.getExpressions()){
            Object value = expr.accept(this, lexenv);
            values.add(value);
        }
        ISum sum = new Sum(iast.getTag().getName(), values);
        return sum; 
    }

    @Override
    public Object visit(IASTmatch iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object v = iast.getDiscriminant().accept(this,lexenv);
        if(!(v instanceof ISum))
            return iast.getAlternant().accept(this, lexenv);
        ISum sum = (ISum) v; 
        if(!(sum.getTag().equals(iast.getTag().getName())))
            return iast.getAlternant().accept(this, lexenv);
        if((sum.getValues().size())< iast.getVariables().length)
            throw new EvaluationException("Expected " + iast.getVariables().length + " arguements for tag : " + sum.getTag()+"  but got " + sum.getValues().size() );
        ILexicalEnvironment lexenv2 = lexenv; 
        // sum.size()>=variables.length
        int maxLength = iast.getVariables().length;
        IASTvariable[] variables = iast.getVariables();
        List<Object> values =sum.getValues();

        for(int i = 0 ; i<maxLength; i++ ){
            lexenv2= lexenv2.extend(variables[i],values.get(i));
        }
        return iast.getBody().accept(this,lexenv2); 
    }
    
}
