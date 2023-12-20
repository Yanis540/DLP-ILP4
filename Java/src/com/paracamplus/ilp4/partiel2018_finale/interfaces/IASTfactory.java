package com.paracamplus.ilp4.partiel2018_finale.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory{
    IASTsuperWithArgs newSuperWithArgs(IASTexpression[]arguments);
}
