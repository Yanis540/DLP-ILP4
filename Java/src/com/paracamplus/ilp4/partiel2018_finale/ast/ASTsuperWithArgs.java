package com.paracamplus.ilp4.partiel2018_finale.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.ast.ASTsuper;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTvisitor;

public class ASTsuperWithArgs extends ASTsuper implements IASTsuperWithArgs {
    private IASTexpression[] arguments;
    public ASTsuperWithArgs(IASTexpression[]arguments){
        this.arguments = arguments; 
    }
    @Override
    public IASTexpression[] getArguments() {
        return this.arguments; 
    }

    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

    
}
