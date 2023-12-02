package com.paracamplus.ilp4.partiel2022.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTfactory;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTliste;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTlisteRange;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory 
implements IASTfactory {

    @Override
    public IASTliste newListe(IASTexpression[] expressions) {
        return new ASTliste(expressions);
    }

    @Override
    public IASTlisteRange newListeRange(IASTexpression body, IASTvariable variable, IASTexpression max,
            IASTexpression condition) {
        return new ASTlisteRange(body, variable, max, condition);
    }
    
}
