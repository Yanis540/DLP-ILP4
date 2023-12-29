package com.paracamplus.partiels.finale2018.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.partiels.finale2018.interfaces.IASTfactory;
import com.paracamplus.partiels.finale2018.interfaces.IASTsuperWithArgs;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory{
    public IASTsuperWithArgs newSuperWithArgs(IASTexpression[] expressions){
        return new ASTsuperWithArgs(expressions);
    }

}
