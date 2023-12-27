package com.paracamplus.partiels.finale2022.interfaces;

public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTlist iast, Data data) throws Anomaly;
    Result visit(IASTlistRange iast, Data data) throws Anomaly;
}
