package com.paracamplus.ilp4.ilp4tme10.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.ilp4tme10.ast.ASTexists;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;

public class NormalizationFactory extends com.paracamplus.ilp4.compiler.normalizer.NormalizationFactory implements INormalizationFactory {

    @Override
    public IASTexists newExists(IASTvariable variable) {
        return new ASTexists(variable);
    }
    
}
