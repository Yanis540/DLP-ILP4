package com.paracamplus.partiels.finale2019.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2019.interfaces.IASTautoClose;
import com.paracamplus.partiels.finale2019.interfaces.IASTfactory;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTautoClose newAutoClose(IASTvariable variable, IASTexpression value, IASTexpression body) {
        return new ASTautoClose(variable, value, body);
    }
    
}
