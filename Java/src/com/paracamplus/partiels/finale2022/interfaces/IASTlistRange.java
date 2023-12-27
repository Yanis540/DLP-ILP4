package com.paracamplus.partiels.finale2022.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTlistRange extends IASTexpression {
    IASTexpression getBody();
    IASTvariable getVariable();
    IASTexpression getMax();
    IASTexpression getCondition();
    Boolean IsCondition();
}
