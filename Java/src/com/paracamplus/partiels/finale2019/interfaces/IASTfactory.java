package com.paracamplus.partiels.finale2019.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTautoClose newAutoClose(IASTvariable variable,IASTexpression value,IASTexpression body);
}
