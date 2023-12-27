package com.paracamplus.partiels.finale2022.compiler.normalizer;

import com.paracamplus.ilp1.compiler.interfaces.IASTCvariable;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.partiels.finale2022.ast.ASTlist;
import com.paracamplus.partiels.finale2022.ast.ASTlistRange;
import com.paracamplus.partiels.finale2022.interfaces.IASTlist;
import com.paracamplus.partiels.finale2022.interfaces.IASTlistRange;

public class NormalizationFactory 
extends com.paracamplus.ilp4.compiler.normalizer.NormalizationFactory implements INormalizationFactory {

    @Override
    public IASTlist newList(IASTexpression[] expressions) {
        return new ASTlist(expressions);
    }

    @Override
    public IASTlistRange newListRange(IASTexpression body, IASTCvariable variable, IASTexpression max,
            IASTexpression condition, Boolean isCondition) {
        return new ASTlistRange(body, variable, max, condition, isCondition);
    }
    
}
