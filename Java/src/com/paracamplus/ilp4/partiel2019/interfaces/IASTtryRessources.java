package com.paracamplus.ilp4.partiel2019.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTtryRessources extends IASTexpression{
    IASTvariable getVariable();
    IASTexpression getExpression();
    IASTexpression getBody();
}
