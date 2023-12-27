package com.paracamplus.partiels.finale2021.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2021.interfaces.IASTtag;
import com.paracamplus.partiels.finale2021.interfaces.IASTvisitor;

public class ASTtag extends ASTexpression implements IASTtag {
    private IASTvariable tag;
    private IASTexpression[] expressions;
    public ASTtag(IASTvariable tag,IASTexpression[] expressions){
        this.tag = tag; 
        this.expressions = expressions; 
    }
    @Override
    public IASTvariable getTag() {
        return this.tag; 
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
