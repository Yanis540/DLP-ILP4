package com.paracamplus.ilp4.ilp4tme8.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASThasProprety newHasProprety(IASTexpression object,IASTexpression proprety);
    IASTreadProprety newReadProprety(IASTexpression object,IASTexpression proprety);
    IASTwriteProprety newWriteProprety(IASTexpression object,IASTexpression proprety,IASTexpression value);
}
