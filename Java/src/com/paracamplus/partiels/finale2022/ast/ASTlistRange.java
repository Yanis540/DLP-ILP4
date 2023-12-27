package com.paracamplus.partiels.finale2022.ast;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2022.interfaces.IASTlistRange;
import com.paracamplus.partiels.finale2022.interfaces.IASTvisitor;

public class ASTlistRange extends ASTexpression implements IASTlistRange{
    private IASTexpression body,max,condition;
    private IASTvariable variable; 
    private Boolean isCondition;
    public ASTlistRange(IASTexpression body,IASTvariable variable, IASTexpression max, @Nullable IASTexpression condition,Boolean isCondition){
        this.body = body; 
        this.max = max; 
        this.condition = condition; 
        this.variable = variable; 
        this.isCondition = isCondition; 
    }
     @Override
    public IASTexpression getBody() {
        return this.body; 
    }
    @Override
    public IASTvariable getVariable() {
        return this.variable; 

    }
    @Override
    public IASTexpression getMax() {
        return this.max; 
    }
    @Override
    public IASTexpression getCondition() {
                return this.condition; 

    }
    @Override
    public Boolean IsCondition() {
        return this.isCondition; 
    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
   
}
