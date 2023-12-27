package com.paracamplus.partiels.finale2022.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.partiels.finale2022.interfaces.IASTlist;
import com.paracamplus.partiels.finale2022.interfaces.IASTvisitor;

public class ASTlist extends ASTexpression implements IASTlist{
    private IASTexpression[]expressions; 
    public ASTlist(IASTexpression[]expressions){
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
