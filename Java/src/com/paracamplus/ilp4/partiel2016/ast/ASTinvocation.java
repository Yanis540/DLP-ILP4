package com.paracamplus.ilp4.partiel2016.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTinvocation;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTvisitor;

public class ASTinvocation extends com.paracamplus.ilp1.ast.ASTinvocation implements IASTinvocation {
    private IASTvariable[] optionalVariables;
    private IASTexpression[] optionalArgumentsValues;
    public ASTinvocation(IASTexpression function, IASTexpression[] arguments,IASTvariable[] optionalVariables,IASTexpression[] optionalArgumentsValues ) {
        super(function, arguments);
        this.optionalVariables = optionalVariables;
        this.optionalArgumentsValues = optionalArgumentsValues;
    } 
    

   


    @Override
    public IASTvariable[] getOptionalVariables() {
        return this.optionalVariables; 
    }


    @Override
    public IASTexpression[] getOptionalArgumentsValues() {
        return this.optionalArgumentsValues;
    }

    @Override
    public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
