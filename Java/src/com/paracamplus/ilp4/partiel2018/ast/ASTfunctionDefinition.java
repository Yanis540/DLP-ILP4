package com.paracamplus.ilp4.partiel2018.ast;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTfunctionDefinition;


public class ASTfunctionDefinition extends com.paracamplus.ilp2.ast.ASTfunctionDefinition implements IASTfunctionDefinition{
    private Boolean countable; 
    public ASTfunctionDefinition(IASTvariable functionVariable, IASTvariable[] variables, IASTexpression body,@Nullable Boolean countable) {
        super(functionVariable, variables, body);
        this.countable = countable == true ? true : false;  
    }

    @Override
    public Boolean isCountable() {
        return this.countable; 
    }

  
}
