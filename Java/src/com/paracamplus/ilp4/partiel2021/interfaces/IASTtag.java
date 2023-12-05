package com.paracamplus.ilp4.partiel2021.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTtag extends IASTexpression {
    IASTexpression[] getExpressions();
    IASTvariable getTag();
}
