package com.paracamplus.partiels.finale2022_2.interpreter;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.partiels.finale2022_2.interfaces.IASTlistRange;
import com.paracamplus.partiels.finale2022_2.interfaces.IASTvisitor;

public class Interpreter  extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
    public Interpreter (IGlobalVariableEnvironment globalVariableEnvironment,
            IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment ) {
		super(globalVariableEnvironment, operatorEnvironment,classEnvironment);
	}

    @Override
    public Object visit(IASTlistRange iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object m = iast.getMax().accept(this,lexenv);
        if(!(m instanceof BigInteger))
            throw new EvaluationException("Expected integer max value");
        int max = ((BigInteger)m).intValue();
        if(max<=0)
            throw new EvaluationException("Max value should be greather than 0");
        ILexicalEnvironment lexenv2 = lexenv;
        List<Object> objects= new ArrayList<>();
        lexenv2 = lexenv2.extend(iast.getVariable(),null); 
        for(int i=0 ; i<max; i++){
            lexenv2.update(iast.getVariable(),BigInteger.valueOf(i));
            Boolean condition =false; 
            if(iast.getCondition()!=null){
                Object c = iast.getCondition().accept(this,lexenv2); 
                if(c instanceof Boolean && ((Boolean)c)== true)
                    condition = true;  
            }
            if(iast.getCondition()== null || condition){
                Object value = iast.getBody().accept(this,lexenv2); 
                objects.add(value); 
            }
        }
        return objects; 
    }
}
