package com.paracamplus.ilp4.partiel2018.interfaces;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTprogram newProgram(
        IASTfunctionDefinition[] functions,
        IASTclassDefinition[] clazzes,
        IASTexpression expression
    );
    IASTfunctionDefinition newFunctionDefinition(
        IASTvariable functionVariable,
        IASTvariable[] variables,
        IASTexpression body,@Nullable Boolean countable  
    );

   
}
