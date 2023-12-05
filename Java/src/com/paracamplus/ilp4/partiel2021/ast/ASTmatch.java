package com.paracamplus.ilp4.partiel2021.ast;

import com.paracamplus.ilp1.ast.ASTexpression;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTmatch;
import com.paracamplus.ilp4.partiel2021.interfaces.IASTvisitor;

public class ASTmatch extends ASTexpression implements IASTmatch{
    private IASTexpression discriminant;
    private IASTvariable tag;
    private IASTvariable[] variables;
    private IASTexpression consequence,alternant;
    public ASTmatch (IASTexpression discriminant,IASTvariable tag,IASTvariable[]variables,IASTexpression consequence,IASTexpression alternant){
        this.discriminant= discriminant;
        this.tag= tag;
        this.variables= variables;
        this.consequence= consequence;
        this.alternant= alternant;
    }

    

    @Override
    public IASTexpression getDiscriminant() {
        return this.discriminant; 
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
    public IASTexpression getConsequence() {
        return this.consequence; 
    }




    @Override
    public IASTexpression getAlternant() {
        return this.alternant; 
    }
   

   
    @Override
	public <Result, Data, Anomaly extends Throwable> Result accept(
			com.paracamplus.ilp1.interfaces.IASTvisitor<Result, Data, Anomaly> visitor,
			Data data) throws Anomaly {
		return ((IASTvisitor <Result, Data, Anomaly>) visitor).visit(this, data);
	}







  
    
}
