package com.paracamplus.ilp4.partiel2017.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.partiel2017.interfaces.IASTfactory;
import com.paracamplus.ilp4.partiel2017.interfaces.IASTswitch;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTswitch newSwitch(IASTexpression arg, IASTexpression[] cases, IASTexpression[] casesBody,
            IASTexpression otherwise) {
        return new ASTswitch(arg, cases, casesBody, otherwise);
    }
    
}
