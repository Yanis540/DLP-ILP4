package com.paracamplus.partiels.finale2022_2.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2022_2.interfaces.IASTlistRange;

public class ASTfactory extends  com.paracamplus.ilp4.ast.ASTfactory 
implements  com.paracamplus.partiels.finale2022_2.interfaces.IASTfactory {

    @Override
    public IASTlistRange newListRange(IASTexpression body, IASTvariable var, IASTexpression max,
            IASTexpression condition) {
        return new ASTlistRange(body, var, max, condition);
    }
    
}
