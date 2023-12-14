package com.paracamplus.ilp4.ilp4tme10.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;

public interface INormalizationFactory extends com.paracamplus.ilp4.compiler.normalizer.INormalizationFactory {
    IASTexists newExists(IASTvariable variable);
}
