package com.paracamplus.ilp4.partiel2018.interfaces;

public interface IFunction extends com.paracamplus.ilp1.interpreter.interfaces.IFunction {
    int getCallsCount();
    Boolean isCountable(); 
    void incrementCount();
    String getFunctionName();
}
