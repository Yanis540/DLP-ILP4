package com.paracamplus.partiels.finale2018.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTsuperWithArgs newSuperWithArgs(IASTexpression[] expressions);
}
