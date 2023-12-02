package com.paracamplus.ilp4.partiel2022.interfaces;


public interface IASTvisitor <Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
    Result visit(IASTliste iast, Data data) throws Anomaly;
    Result visit(IASTlisteRange iast, Data data) throws Anomaly;
}
