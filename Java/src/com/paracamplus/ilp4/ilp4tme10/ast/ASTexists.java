package com.paracamplus.ilp4.ilp4tme10.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTvisitor;

public class ASTexists extends ASTexpression implements IASTexists {
    private IASTvariable variable; 
    public ASTexists(IASTvariable variable){
        this.variable = variable ; 
    }
   
    @Override
    public IASTvariable getVariable() {
        return this.variable; 
    }

    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

    
}
