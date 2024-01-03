package com.paracamplus.partiels.finale2021_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTmatch extends IASTexpression {
    IASTexpression getDiscriminant();
    IASTvariable getTag(); 
    IASTvariable[] getVariables(); 
    IASTexpression getConsequence(); 
    IASTexpression getAlternant();  
}
