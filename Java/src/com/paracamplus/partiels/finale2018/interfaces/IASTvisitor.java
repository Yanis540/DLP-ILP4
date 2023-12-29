package com.paracamplus.partiels.finale2018.interfaces;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTsuperWithArgs iast, Data data) throws Anomaly;
}
