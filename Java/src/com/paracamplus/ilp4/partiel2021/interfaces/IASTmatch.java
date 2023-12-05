package com.paracamplus.ilp4.partiel2021.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTmatch extends IASTexpression {
    IASTexpression getDiscriminant();
    IASTvariable getTag();
    IASTvariable[] getVariables();
	IASTexpression getConsequence();
	IASTexpression getAlternant();
}
