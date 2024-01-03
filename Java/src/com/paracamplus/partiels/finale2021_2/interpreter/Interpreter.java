package com.paracamplus.partiels.finale2021_2.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTmatch;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTtag;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTvisitor;
import com.paracamplus.partiels.finale2021_2.interfaces.ISum;
public class Interpreter  extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {
    public Interpreter (IGlobalVariableEnvironment globalVariableEnvironment,
            IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment ) {
		super(globalVariableEnvironment, operatorEnvironment,classEnvironment);
	}
     @Override
    public Object visit(IASTtag iast, ILexicalEnvironment lexenv) throws EvaluationException {
        List<Object> values = new ArrayList<>(); 
        for(IASTexpression expr : iast.getExpressions())
            values.add(expr.accept(this,lexenv));
        ISum s = new Sum(iast.getTag().getName(), values);
        return s; 
    }

    @Override
    public Object visit(IASTmatch iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object s = iast.getDiscriminant().accept(this,lexenv);
        if(!(s instanceof ISum))
            return iast.getAlternant().accept(this,lexenv); 
        ISum sum = (ISum) s; 
        if(!(sum.getTag().equals(iast.getTag().getName())))
            return iast.getAlternant().accept(this,lexenv); 
        if((sum.getArguments().size())< iast.getVariables().length)
            throw new EvaluationException("Less arguments than variables in match");
        ILexicalEnvironment lexenv2 = lexenv; 
        for(int i=0;i<iast.getVariables().length;i++ ){
            lexenv2=lexenv2.extend(iast.getVariables()[i],sum.getArguments().get(i));
        }
        return iast.getConsequence().accept(this,lexenv2); 
    }

   

}
