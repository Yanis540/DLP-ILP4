package com.paracamplus.partiels.finale2022.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2022.interfaces.IASTfactory;
import com.paracamplus.partiels.finale2022.interfaces.IASTlist;
import com.paracamplus.partiels.finale2022.interfaces.IASTlistRange;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTlist newList(IASTexpression[] expressions) {
        return new ASTlist(expressions);
    }

    @Override
    public IASTlistRange newListRange(IASTexpression body, IASTvariable variable, IASTexpression max,
            IASTexpression condition, Boolean isCondition) {
        return new ASTlistRange(body, variable, max, condition, isCondition);
    }
    
}
