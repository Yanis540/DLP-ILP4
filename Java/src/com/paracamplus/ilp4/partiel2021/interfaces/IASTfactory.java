package com.paracamplus.ilp4.partiel2021.interfaces;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTmatch newMatch(IASTexpression discriminant,IASTvariable tag,IASTvariable[]variables,IASTexpression consequence,IASTexpression alternant);
    IASTtag newTag(IASTvariable tag,IASTexpression[]expressions);
}
