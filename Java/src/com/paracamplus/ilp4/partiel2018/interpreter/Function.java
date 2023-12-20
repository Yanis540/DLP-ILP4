package com.paracamplus.ilp4.partiel2018.interpreter;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp4.partiel2018.interfaces.IFunction;

public class Function extends com.paracamplus.ilp1.interpreter.Function implements IFunction {
    private Boolean countable ; 
    private int callCount; 
    private String functionName; 
    public Function(IASTvariable[] variables, IASTexpression body, ILexicalEnvironment lexenv,Boolean countable,String functionName) {
        super(variables, body, lexenv);
        this.countable = countable; 
        this.callCount = 0 ; 
        this.functionName = functionName; 
    }
    @Override
    public int getCallsCount() {
        return this.callCount;
    }
    @Override
    public Boolean isCountable() {
        return this.countable; 
    }
    public void incrementCount(){
        if(countable)
            this.callCount +=1; 
    }
    @Override
    public String getFunctionName() {
        return this.functionName;
    }
    
}
