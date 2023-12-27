package com.paracamplus.partiels.finale2019.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2019.interfaces.IASTautoClose;
import com.paracamplus.partiels.finale2019.interfaces.IASTvisitor;

public class ASTautoClose extends ASTexpression implements IASTautoClose{
    private IASTvariable variable ;
    private IASTexpression body,value;
    public ASTautoClose(IASTvariable variable,IASTexpression value,IASTexpression body){
        this.variable = variable; 
        this.body = body; 
        this.value = value; 
    }
        @Override
    public IASTvariable getVariable() {
        return this.variable; 
    }

    @Override
    public IASTexpression getValue() {
        return this.value; 
    }

    @Override
    public IASTexpression getBody() {
        return this.body; 
    }
    
   @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}


}
