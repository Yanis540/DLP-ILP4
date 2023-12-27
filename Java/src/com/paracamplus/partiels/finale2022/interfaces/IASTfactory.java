package com.paracamplus.partiels.finale2022.interfaces;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTlist newList(IASTexpression[]expressions);
    IASTlistRange newListRange(IASTexpression body,IASTvariable variable, IASTexpression max, @Nullable IASTexpression condition,Boolean isCondition);
}
    