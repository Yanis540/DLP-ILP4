package com.paracamplus.ilp4.partiel2019.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2019.interfaces.IASTtryRessources;
import com.paracamplus.ilp4.partiel2019.interfaces.IASTvisitor;

public class ASTtryRessources extends ASTexpression implements IASTtryRessources {
    private IASTvariable variable;
    private IASTexpression expression,body;
    public ASTtryRessources(IASTvariable variable,IASTexpression expression,IASTexpression body){
        this.variable = variable; 
        this.expression = expression; 
        this.body = body; 
    }
   
    @Override
    public IASTvariable getVariable() {
        return this.variable; 
    }
    @Override
    public IASTexpression getExpression() {
        return this.expression; 
    }
    @Override
    public IASTexpression getBody() {
        return this.body; 
    }
     @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
