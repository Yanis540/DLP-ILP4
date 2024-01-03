package com.paracamplus.partiels.finale2021_2.ast;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTfactory;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTmatch;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTtag;
public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTtag newTag(IASTvariable tag, IASTexpression[] exprs) {
        return new ASTtag(tag, exprs);
    }

    @Override
    public IASTmatch newMatch(IASTexpression disc, IASTvariable tag, IASTexpression[] arguments,
            IASTexpression consequence, IASTexpression alternant) {
        return new ASTmatch(disc, tag, arguments, consequence, alternant);
    }
    
}
