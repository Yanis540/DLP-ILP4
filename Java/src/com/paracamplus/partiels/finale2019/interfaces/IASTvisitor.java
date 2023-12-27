package com.paracamplus.partiels.finale2019.interfaces;


public interface IASTvisitor<Result, Data, Anomaly extends Throwable>
extends com.paracamplus.ilp4.interfaces.IASTvisitor<Result, Data, Anomaly>{
    Result visit(IASTautoClose iast, Data data) throws Anomaly;

}
