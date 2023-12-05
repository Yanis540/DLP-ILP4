package com.paracamplus.ilp4.partiel2021.interpreter;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTmatch;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTtag;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTmatch iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object t = iast.getDiscriminant().accept(this,lexenv);
        if(!(t instanceof IASTtag)) {
            return iast.getAlternant().accept(this, lexenv);
        }
        IASTtag tag = (IASTtag) t; 
        // tag mais pas le même nom
        if(!(iast.getTag().getName().equals(tag.getTag().getName())))
            return iast.getAlternant().accept(this, lexenv);
        
        IASTexpression[] args = tag.getExpressions();
        int argsLength = args.length;
        IASTvariable[] vars = iast.getVariables();
        int variablesLength = vars.length;

        int maxLength= argsLength>variablesLength? variablesLength : argsLength;  
        if((args.length)<variablesLength)
            throw new EvaluationException("Expected "+variablesLength+" variables but got "+args.length+" arguemnts");
        ILexicalEnvironment lexenv2 = lexenv; 
        for(int i = 0 ; i<maxLength; i++){
            Object v = args[i].accept(this, lexenv2);
            lexenv2= lexenv2.extend(vars[i],v );
        }
        return iast.getConsequence().accept(this, lexenv2); 
    }

    @Override
    public Object visit(IASTtag iast, ILexicalEnvironment lexenv) throws EvaluationException {
        IASTexpression[] expressions = iast.getExpressions();
        for(IASTexpression expr : expressions)
            expr.accept(this, lexenv);
        return iast; 
    }
    
}
