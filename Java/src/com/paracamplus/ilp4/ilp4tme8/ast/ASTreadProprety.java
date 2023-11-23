package com.paracamplus.ilp4.ilp4tme8.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;

public class ASTreadProprety extends ASTexpression implements IASTreadProprety {

    public ASTreadProprety (IASTexpression object, IASTexpression proprety) {
        this.object = object;
        this.proprety = proprety;
    }
    private final IASTexpression object;
    private final IASTexpression proprety;

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
    
}
