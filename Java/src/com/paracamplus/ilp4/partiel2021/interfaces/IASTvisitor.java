package com.paracamplus.ilp4.partiel2021.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTmatch iast, Data data) throws Anomaly;
    Result visit(IASTtag iast, Data data) throws Anomaly;
}
