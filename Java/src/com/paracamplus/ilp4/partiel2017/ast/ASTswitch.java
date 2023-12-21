package com.paracamplus.ilp4.partiel2017.ast;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.partiel2017.interfaces.IASTswitch;
import com.paracamplus.ilp4.partiel2017.interfaces.IASTvisitor;

public class ASTswitch extends ASTexpression implements IASTswitch{
    private IASTexpression arg,otherwise; 
    private IASTexpression[]cases,casesBody;
    public ASTswitch(    IASTexpression arg, IASTexpression[] cases,IASTexpression[] casesBody,@Nullable IASTexpression otherwise){
        this.cases= cases;
        this.casesBody= casesBody;
        this.arg= arg;
        this.otherwise= otherwise;
    }
     @Override
    public IASTexpression getArgument() {
        return this.arg; 
    }
    @Override
    public IASTexpression[] getCases() {
        return this.cases;
    }
    @Override
    public IASTexpression[] getCasesBody() {
        return this.casesBody;

    }
    @Override
    public IASTexpression getOtherwise() {
        return this.otherwise;

    }
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
   
}
