package com.paracamplus.ilp4.partiel2016.interfaces;

public interface IASTvisitor <Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp3.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTinvocation iast, Data data) throws Anomaly;
}
