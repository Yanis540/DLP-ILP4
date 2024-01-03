package com.paracamplus.partiels.finale2022_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory{
    IASTlistRange newListRange(IASTexpression body, IASTvariable var,IASTexpression max,IASTexpression condition); 
}
