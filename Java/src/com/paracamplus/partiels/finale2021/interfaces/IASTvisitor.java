package com.paracamplus.partiels.finale2021.interfaces;

public interface IASTvisitor <Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTtag iast, Data data) throws Anomaly;
    Result visit(IASTmatch iast, Data data) throws Anomaly;
}
