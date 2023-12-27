package com.paracamplus.partiels.finale2021.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTmatch newMatch(IASTexpression disc, IASTvariable tag, IASTvariable[] variables,IASTexpression body,IASTexpression alternant);
    IASTtag newTag(IASTvariable tag,IASTexpression[] expressions);
}
