package com.paracamplus.partiels.finale2018.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.partiels.finale2018.interfaces.IASTsuperWithArgs;
import com.paracamplus.partiels.finale2018.interfaces.IASTvisitor;

public class ASTsuperWithArgs  extends ASTexpression implements IASTsuperWithArgs{
    private IASTexpression[] expressions; 
    public ASTsuperWithArgs(IASTexpression[] expressions){
        this.expressions = expressions; 
    }
    @Override
    public IASTexpression[] getExpressions() {
        return this.expressions; 
    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
    
}
