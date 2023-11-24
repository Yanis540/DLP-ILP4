package com.paracamplus.ilp4.ilp4tme8.compiler.normalizer;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ilp4tme8.ast.ASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.ast.ASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.ast.ASTwriteProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProprety;

public class NormalizationFactory extends com.paracamplus.ilp4.compiler.normalizer.NormalizationFactory
implements INormalizationFactory {

    @Override
    public IASThasProprety newHasProprety(IASTexpression target, IASTexpression proprety) {
        return new ASThasProprety(target, proprety);
    }

    @Override
    public IASTreadProprety newReadProprety(IASTexpression target, IASTexpression proprety) {
        return new ASTreadProprety(target, proprety);
    }

    @Override
    public IASTwriteProprety newWriteProprety(IASTexpression target, IASTexpression proprety, IASTexpression value) {
        return new ASTwriteProprety(target, proprety, value);
    }
    
}
