package com.paracamplus.ilp4.ilp4tme8.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASThasProprety newHasProprety(IASTexpression target,IASTexpression proprety);
    IASTreadProprety newReadProprety(IASTexpression target,IASTexpression proprety);
    IASTwriteProprety newWriteProprety(IASTexpression target,IASTexpression proprety,IASTexpression value);
}
