package com.paracamplus.ilp4.ilp4tme8.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProprety;

public class ASTwriteProprety extends ASTexpression  implements IASTwriteProprety{
    public ASTwriteProprety (IASTexpression object, IASTexpression proprety,IASTexpression value) {
        this.object = object;
        this.proprety = proprety;
        this.value = value;
    }
    private final IASTexpression object;
    private final IASTexpression proprety;
    private final IASTexpression value;

    @Override
    public IASTexpression getObject() {
        return this.object; 
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

    @Override
    public IASTexpression getValue() {
        return this.value; 
    }
}
