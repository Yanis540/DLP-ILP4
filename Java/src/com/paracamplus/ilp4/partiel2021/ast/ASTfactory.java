package com.paracamplus.ilp4.partiel2021.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTfactory;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTmatch;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTtag;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory{

    @Override
    public IASTmatch newMatch(IASTexpression discriminant, IASTvariable tag, IASTvariable[] variables,
            IASTexpression consequence, IASTexpression alternant) {
        return new ASTmatch(discriminant, tag, variables, consequence, alternant);
    }

    @Override
    public IASTtag newTag(IASTvariable tag, IASTexpression[] expressions) {
        return new ASTtag(tag, expressions);
    }
    
}
