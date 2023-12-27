package com.paracamplus.partiels.finale2021.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTtag extends IASTexpression {
    IASTvariable getTag();
    IASTexpression[] getExpressions(); 
}
