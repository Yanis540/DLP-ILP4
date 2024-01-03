package com.paracamplus.partiels.finale2022_2.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly> {
        Result visit(IASTlistRange iast, Data data) throws Anomaly;

}
