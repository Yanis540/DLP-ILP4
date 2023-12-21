package com.paracamplus.ilp4.partiel2017.interfaces;

import org.antlr.v4.runtime.misc.Nullable;

import com.paracamplus.ilp1.interfaces.IASTexpression;

public interface IASTfactory extends com.paracamplus.ilp4.interfaces.IASTfactory {
    IASTswitch newSwitch(IASTexpression arg, IASTexpression[] cases,IASTexpression[] casesBody,@Nullable IASTexpression otherwise);
}
