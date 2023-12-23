package com.paracamplus.ilp4.partiel2016.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTprogram;

public class ASTprogram extends com.paracamplus.ilp4.ast.ASTprogram implements IASTprogram {
    private IASTfunctionDefinition[] functionsPartiel; 
    public ASTprogram(IASTfunctionDefinition[] functions, IASTclassDefinition[] clazzes, IASTexpression expression) {
        super(functions, clazzes, expression);
        this.functionsPartiel = functions ; 
    }

    @Override
    public IASTfunctionDefinition[] getFunctionDefinitions(){
        return this.functionsPartiel;
    };
    
}
