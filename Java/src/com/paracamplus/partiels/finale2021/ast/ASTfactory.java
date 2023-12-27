package com.paracamplus.partiels.finale2021.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2021.interfaces.IASTfactory;
import com.paracamplus.partiels.finale2021.interfaces.IASTmatch;
import com.paracamplus.partiels.finale2021.interfaces.IASTtag;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTmatch newMatch(IASTexpression disc, IASTvariable tag, IASTvariable[] variables,IASTexpression body,IASTexpression alternant) {
        return new ASTmatch(disc, tag, variables,body,alternant);
    }

    @Override
    public IASTtag newTag(IASTvariable tag, IASTexpression[] expressions) {
        return new ASTtag(tag, expressions);
    }
    
}
