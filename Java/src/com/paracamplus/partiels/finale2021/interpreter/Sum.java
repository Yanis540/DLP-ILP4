package com.paracamplus.partiels.finale2021.interpreter;
import java.util.List;

import com.paracamplus.partiels.finale2021.interfaces.ISum;
public class Sum implements ISum {
    private String tag; 
    private List<Object> values; 
    public Sum(String tag,List<Object>values){
        this.tag = tag;
        this.values = values;
    }
    @Override
    public String getTag() {
        return this.tag; 
    }
    @Override
    public List<Object> getValues() {
        return this.values; 
    }
}
