package com.paracamplus.ilp4.partiel2018_finale.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;

public interface INormalizationFactory extends com.paracamplus.ilp4.compiler.normalizer.INormalizationFactory{
    IASTsuperWithArgs newSuperWithArgs(IASTexpression[]arguments);

}
