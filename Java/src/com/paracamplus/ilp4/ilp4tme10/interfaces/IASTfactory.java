package com.paracamplus.ilp4.ilp4tme10.interfaces;

import com.paracamplus.ilp1.interfaces.IASTvariable;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTexists newExists(IASTvariable variable);
}
