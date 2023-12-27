package com.paracamplus.partiels.finale2019.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTautoClose extends IASTexpression{
    IASTvariable getVariable();
    IASTexpression getValue(); 
    IASTexpression getBody();
}
