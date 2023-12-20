package com.paracamplus.ilp4.partiel2018_finale.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.interfaces.IASTsuper;

public interface IASTsuperWithArgs extends IASTsuper{
    IASTexpression[] getArguments();
}
