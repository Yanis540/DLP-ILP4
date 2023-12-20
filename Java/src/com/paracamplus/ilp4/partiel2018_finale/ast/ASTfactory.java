package com.paracamplus.ilp4.partiel2018_finale.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTfactory;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTsuperWithArgs newSuperWithArgs(IASTexpression[] arguments) {
        return new ASTsuperWithArgs(arguments);
    }
    
}
