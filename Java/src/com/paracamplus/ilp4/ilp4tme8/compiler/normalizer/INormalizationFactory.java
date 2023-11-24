package com.paracamplus.ilp4.ilp4tme8.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProprety;

public interface INormalizationFactory extends com.paracamplus.ilp4.compiler.normalizer.INormalizationFactory {
    IASThasProprety newHasProprety(IASTexpression target,IASTexpression proprety);
    IASTreadProprety newReadProprety(IASTexpression target,IASTexpression proprety);
    IASTwriteProprety newWriteProprety(IASTexpression target,IASTexpression proprety,IASTexpression value);
}
