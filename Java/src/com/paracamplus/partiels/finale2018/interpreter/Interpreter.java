package com.paracamplus.partiels.finale2018.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IMethod;
import com.paracamplus.ilp4.interpreter.interfaces.ISuperCallInformation;
import com.paracamplus.partiels.finale2018.interfaces.IASTsuperWithArgs;
import com.paracamplus.partiels.finale2018.interfaces.IASTvisitor;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }
    @Override 
    public Object visit(IASTsuperWithArgs iast, ILexicalEnvironment lexenv) throws EvaluationException {
        ISuperCallInformation isci = 
        		 ((com.paracamplus.ilp4.interpreter.interfaces.ISuperCallLexicalEnvironment) lexenv).getSuperCallInformation();
        IMethod supermethod = isci.getSuperMethod();
        List<Object> args = new ArrayList<>();
        args.add(isci.getArguments()[0]); // adding self 
        for(IASTexpression expr : iast.getExpressions()){
            Object arg = expr.accept(this,lexenv);
            args.add(arg);
        }
        return supermethod.apply(this, args.toArray());
    }
    
}
