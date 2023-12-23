package com.paracamplus.ilp4.partiel2016.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTfunctionDefinition;
public class ASTfunctionDefinition extends com.paracamplus.ilp2.ast.ASTfunctionDefinition implements IASTfunctionDefinition {
    private Boolean areOptionalVariablesB ;  
    private IASTvariable[] optionalVariables ;  
    private IASTexpression[] defaultOptionaVariablesValues ;  
    public ASTfunctionDefinition(IASTvariable functionVariable,IASTvariable[] variables, IASTexpression body,
        Boolean areOptionalVariables, 
        IASTvariable[] optionalVariables,
        IASTexpression[] defaultOptionaVariablesValues) 
    {
        super(functionVariable, variables, body);
        this.areOptionalVariablesB = areOptionalVariables; 
        this.optionalVariables = optionalVariables; 
        this.defaultOptionaVariablesValues = defaultOptionaVariablesValues; 
    }
    @Override
    public Boolean areOptionalVariables() {
        return this.areOptionalVariablesB; 
    }
    @Override
    public IASTvariable[] getOptionalVariables() {
        return this.optionalVariables;
    }
    @Override
    public IASTexpression[] getOptionalVariablesDefaultValues() {
        return this.defaultOptionaVariablesValues;
    }

	
}
