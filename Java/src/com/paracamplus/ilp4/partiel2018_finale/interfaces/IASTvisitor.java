package com.paracamplus.ilp4.partiel2018_finale.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTsuperWithArgs iast, Data data) throws Anomaly;
}
