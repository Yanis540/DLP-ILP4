package com.paracamplus.partiels.finale2022.interpreter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.partiels.finale2022.interfaces.IASTlist;
import com.paracamplus.partiels.finale2022.interfaces.IASTlistRange;
import com.paracamplus.partiels.finale2022.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTlist iast, ILexicalEnvironment lexenv) throws EvaluationException {
        List<Object> obj =new ArrayList<>();
        for(IASTexpression expr : iast.getExpressions() ){
            Object value = expr.accept(this,lexenv);
            obj.add(value);
        }
        return obj; 
    }

    @Override
    public Object visit(IASTlistRange iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object m = iast.getMax().accept(this,lexenv); 
        if(!(m instanceof BigInteger))
            throw new EvaluationException("Max should be an integer in range value");
        int max = ((BigInteger)m).intValue();
        if(max<0)
            throw new EvaluationException("Max can't be negative");
        List<Object> objects= new ArrayList<>();
        ILexicalEnvironment lexenv2 = lexenv; 
        lexenv2 = lexenv2.extend(iast.getVariable(), null);
        for(int i=0; i<max ; i++){
            lexenv2.update(iast.getVariable(), BigInteger.valueOf(i));
            Object c = null;
            if(iast.IsCondition()){
                c = iast.getCondition().accept(this,lexenv2);
            }
            Boolean condition = c instanceof Boolean ? (( Boolean ) c) == true  : false; 
            if((! iast.IsCondition()) || (condition)){
                Object value = iast.getBody().accept(this,lexenv2); 
                objects.add(value);
            }
            
        }
        return objects; 
    }
    
}
