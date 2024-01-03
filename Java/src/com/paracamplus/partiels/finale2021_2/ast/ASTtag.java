package com.paracamplus.partiels.finale2021_2.ast;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTtag;
import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2021_2.interfaces.IASTvisitor;

public class ASTtag extends ASTexpression implements IASTtag{
    private IASTvariable tag;
    private IASTexpression[]exprs;
    public ASTtag(IASTvariable tag,IASTexpression[]exprs){
        this.tag = tag; 
        this.exprs = exprs; 
    }
    
    @Override
    public IASTvariable getTag() {
        return this.tag; 
    }
    @Override
    public IASTexpression[] getExpressions() {
        return this.exprs; 
    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
}
