package com.paracamplus.ilp4.partiel2022.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTlisteRange extends IASTexpression{
    IASTexpression getBody(); 
    IASTvariable getVariable(); 
    IASTexpression getMax(); 
    IASTexpression getCondition(); 
    boolean getIsConditionPresent();
}
