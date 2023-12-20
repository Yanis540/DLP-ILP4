package com.paracamplus.ilp4.partiel2018_finale.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.partiel2018_finale.ast.ASTsuperWithArgs;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;

public class NormalizationFactory extends com.paracamplus.ilp4.compiler.normalizer.NormalizationFactory implements INormalizationFactory{

    @Override
    public IASTsuperWithArgs newSuperWithArgs(IASTexpression[] arguments) {
        return new ASTsuperWithArgs(arguments);
    }
    
}
