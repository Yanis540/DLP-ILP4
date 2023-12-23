package com.paracamplus.ilp4.partiel2016.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTinvocation newInvocation(IASTexpression function,IASTexpression[]arguments,IASTvariable[] optionalVariables,IASTexpression[] optionalArgumentsValues );
    IASTfunctionDefinition newFunctionDefinition(
        IASTvariable functionVariable,
        IASTvariable[] variables,
        IASTexpression body, 
        Boolean areOptionalVariables, 
        IASTvariable[] optionalVariables,
        IASTexpression[] defaultOptionaVariablesValues
    );
    IASTprogram newProgram(IASTfunctionDefinition[] functions,
    		IASTclassDefinition[] clazzes,
            IASTexpression expression);
}
