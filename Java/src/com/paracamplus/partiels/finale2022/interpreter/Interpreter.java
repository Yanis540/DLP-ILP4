package com.paracamplus.partiels.finale2022.interpreter;

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
    public Object visit(IASTlist iast, ILexicalEnvironment data) throws EvaluationException {
        return 1; 
    }

    @Override
    public Object visit(IASTlistRange iast, ILexicalEnvironment data) throws EvaluationException {
        return 2; 
    }
    
}
