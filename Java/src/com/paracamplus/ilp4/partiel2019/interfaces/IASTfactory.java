package com.paracamplus.ilp4.partiel2019.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory{
    IASTtryRessources newTryRessources(IASTvariable variable,IASTexpression expression,IASTexpression body);
}
