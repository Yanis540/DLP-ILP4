package com.paracamplus.ilp4.partiel2022.ast;

import com.paracamplus.ilp1.ast.ASTsequence;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTliste;
import com.paracamplus.ilp4.partiel2022.interfaces.IASTvisitor;

public class ASTliste extends ASTsequence implements IASTliste {

    public ASTliste(IASTexpression[] expressions) {
        super(expressions);
        
    }

    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}
    
}
