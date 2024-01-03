package com.paracamplus.partiels.finale2022_2.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2022_2.interfaces.IASTlistRange;
import  com.paracamplus.partiels.finale2022_2.interfaces.IASTvisitor;
public class ASTlistRange extends ASTexpression implements IASTlistRange{
    private IASTexpression body,  max, condition;
    private IASTvariable var;
    public ASTlistRange(IASTexpression body, IASTvariable var,IASTexpression max,IASTexpression condition){
        this.var = var; 
        this.body = body; 
        this.max = max; 
        this.condition = condition; 
    }

     @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

    @Override
    public IASTexpression getBody() {
        return this.body ; 
    }

    @Override
    public IASTvariable getVariable() {
        return this.var; 
    }

    @Override
    public IASTexpression getMax() {
        return this.max; 
    }

    @Override
    public IASTexpression getCondition() {
        return this.condition ; 
    }


}
