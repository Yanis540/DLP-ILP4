package com.paracamplus.ilp4.partiel2017.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTswitch extends IASTexpression {
    IASTexpression getArgument(); 
    IASTexpression[] getCases(); 
    IASTexpression[] getCasesBody(); 
    IASTexpression getOtherwise(); 
}
