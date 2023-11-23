package com.paracamplus.ilp4.ilp4tme8.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly>{
    Result visit(IASTreadProprety iast, Data data) throws Anomaly;
    Result visit(IASTwriteProprety iast, Data data) throws Anomaly;
    Result visit(IASThasProprety iast, Data data) throws Anomaly;
}
