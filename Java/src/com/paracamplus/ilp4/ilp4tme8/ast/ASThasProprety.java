package com.paracamplus.ilp4.ilp4tme8.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;

public class ASThasProprety extends ASTexpression implements IASThasProprety{
    public ASThasProprety (IASTexpression target, IASTexpression proprety) {
        this.target = target;
        this.proprety = proprety;
    }
    private final IASTexpression target;
    private final IASTexpression proprety;

    @Override
    public IASTexpression getTarget() {
        return this.target; 
    }

    @Override
    public IASTexpression getProprety() {
        return this.proprety; 
    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
