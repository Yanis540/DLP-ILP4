package com.paracamplus.partiels.finale2021_2.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends  com.paracamplus.ilp4.interfaces.IASTfactory{
    IASTtag newTag(IASTvariable tag,IASTexpression[]exprs); 
    IASTmatch newMatch(IASTexpression disc,IASTvariable tag,IASTexpression[] arguments, IASTexpression consequence,IASTexpression alternant); 
}
