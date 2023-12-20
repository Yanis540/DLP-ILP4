package com.paracamplus.ilp4.partiel2018.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTprogram;

public class ASTprogram extends com.paracamplus.ilp4.ast.ASTprogram implements IASTprogram {
    private IASTfunctionDefinition[] functions;
    public ASTprogram(IASTfunctionDefinition[] functions, IASTclassDefinition[] clazzes, IASTexpression expression) {
        super(functions, clazzes, expression);
        this.functions = functions; 
    }

    @Override
    public  IASTfunctionDefinition[] getFunctionDefinitions(){
        return this.functions; 
    }

    
}
