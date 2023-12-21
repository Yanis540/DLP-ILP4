package com.paracamplus.ilp4.partiel2017.interfaces;


public interface IASTvisitor <Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTswitch iast, Data data) throws Anomaly;

}
