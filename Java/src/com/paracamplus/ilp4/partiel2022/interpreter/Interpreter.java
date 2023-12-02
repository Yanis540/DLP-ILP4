package com.paracamplus.ilp4.partiel2022.interpreter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTliste;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTlisteRange;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTliste iast, ILexicalEnvironment lexenv) throws EvaluationException {
        List<Object> list = new ArrayList<>() ;
        for(IASTexpression expression : iast.getExpressions()){
            Object value = expression.accept(this, lexenv);
            list.add(value);
        }
        return list; 
    }

    @Override
    public Object visit(IASTlisteRange iast, ILexicalEnvironment lexenv) throws EvaluationException {
        List<Object> list =  new ArrayList<>();
        
        Object m = iast.getMax().accept(this, lexenv);
        if(!(m instanceof BigInteger))
            throw new EvaluationException("Max should be an integer value but recieved" + m);
        int max = ((BigInteger)m).intValue(); 
        if (max<0)
            throw new EvaluationException("Max should ba a positive integer");
        if(max ==0)
            return list; 
        
        ILexicalEnvironment lexenv2 = lexenv; 
        
        IASTvariable variable = iast.getVariable();

        lexenv2=lexenv2.extend(variable,null);


        for(int i = 0 ; i<max; i++){
            lexenv2.update(variable, (BigInteger.valueOf(i)));
            boolean isConditionPresent = iast.getIsConditionPresent();
            Object c = null; 
            if(isConditionPresent){
                c = iast.getCondition().accept(this, lexenv2);
            }
            if((!isConditionPresent) || (c instanceof Boolean && ((Boolean)c == true)  )){
                Object value = iast.getBody().accept(this,lexenv2);
                list.add(value);
            }
        }
        return list; 
    }
    
}
