package com.paracamplus.partiels.finale2022.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTlist extends IASTexpression {
    IASTexpression[] getExpressions();
}
