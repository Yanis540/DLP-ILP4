package com.paracamplus.ilp4.partiel2019.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2019.interfaces.IASTfactory;
import com.paracamplus.ilp4.partiel2019.interfaces.IASTtryRessources;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTtryRessources newTryRessources(IASTvariable variable, IASTexpression expression, IASTexpression body) {
        return new ASTtryRessources(variable, expression, body);
    }
    
}
