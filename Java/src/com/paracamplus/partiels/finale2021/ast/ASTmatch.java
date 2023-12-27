package com.paracamplus.partiels.finale2021.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.partiels.finale2021.interfaces.IASTmatch;
import com.paracamplus.partiels.finale2021.interfaces.IASTvisitor;

public class ASTmatch extends ASTexpression implements IASTmatch{
    private IASTexpression disc,alternant;
    private IASTvariable tag;
    private IASTvariable[] variables; 
    public ASTmatch(IASTexpression disc, IASTvariable tag, IASTvariable[] variables,IASTexpression alternant){
        this.tag = tag; 
        this.disc = disc; 
        this.variables = variables; 
        this.alternant = alternant; 
    }
    
    @Override
    public IASTexpression getDiscriminant() {
        return this.disc; 
    }
    @Override
    public IASTexpression getAlternant() {
        return this.alternant; 
    }

    @Override
    public IASTvariable getTag() {
        return this.tag; 
    }

    @Override
    public IASTvariable[] getVariables() {
        return this.variables; 
    }

    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}

}
