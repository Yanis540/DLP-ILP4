package com.paracamplus.ilp4.partiel2016.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfunctionDefinition extends com.paracamplus.ilp2.interfaces.IASTfunctionDefinition {
    Boolean areOptionalVariables();
    IASTvariable[] getOptionalVariables();
    IASTexpression[] getOptionalVariablesDefaultValues();
}
