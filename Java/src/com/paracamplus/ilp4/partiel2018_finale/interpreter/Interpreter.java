package com.paracamplus.ilp4.partiel2018_finale.interpreter;

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
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTvisitor;


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
        args.add(isci.getArguments()[0]);
        for(IASTexpression a : iast.getArguments()){
            Object arg = a.accept(this, lexenv);
            args.add(arg);
        }
        return supermethod.apply(this, args.toArray());
    }
    
}
