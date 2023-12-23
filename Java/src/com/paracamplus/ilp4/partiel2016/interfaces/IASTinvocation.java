package com.paracamplus.ilp4.partiel2016.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTinvocation extends com.paracamplus.ilp1.interfaces.IASTinvocation {
    IASTvariable[] getOptionalVariables();
    IASTexpression[] getOptionalArgumentsValues();
}
