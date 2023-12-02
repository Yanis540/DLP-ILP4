package com.paracamplus.ilp4.partiel2022.ast;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTlisteRange;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTvisitor;

public class ASTlisteRange extends ASTexpression implements IASTlisteRange {
    private IASTexpression body,max ,condition; 
    private IASTvariable variable ; 
    public ASTlisteRange(IASTexpression body, IASTvariable variable, IASTexpression max,@Nullable IASTexpression condition){
        this.body = body; 
        this.max = max; 
        this.condition = condition; 
        this.variable = variable; 
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
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

}
