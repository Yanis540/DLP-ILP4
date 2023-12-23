package com.paracamplus.ilp4.partiel2016.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp4.partiel2016.interpreter.Interpreter;

public interface IFunction extends com.paracamplus.ilp1.interpreter.interfaces.IFunction{
    Boolean areOptionalVariables();
    IASTvariable[] getOptionalVariables();
    IASTexpression[] getOptionalVariablesDefaultValues();
    @Override
    int getArity() ;
    Object apply(Interpreter interpreter, Object[] argument) throws EvaluationException;
}
