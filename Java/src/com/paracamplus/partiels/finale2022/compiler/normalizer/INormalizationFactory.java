package com.paracamplus.partiels.finale2022.compiler.normalizer;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.compiler.interfaces.IASTCvariable;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.partiels.finale2022.interfaces.IASTlist;
import com.paracamplus.partiels.finale2022.interfaces.IASTlistRange;

public interface INormalizationFactory extends com.paracamplus.ilp4.compiler.normalizer.INormalizationFactory{
    IASTlist newList(IASTexpression[]expressions);
    IASTlistRange newListRange(IASTexpression body,IASTCvariable variable, IASTexpression max, @Nullable IASTexpression condition,Boolean isCondition);
}
