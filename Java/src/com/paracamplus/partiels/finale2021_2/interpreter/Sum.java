package com.paracamplus.partiels.finale2021_2.interpreter;

import java.util.List;

import com.paracamplus.partiels.finale2021_2.interfaces.ISum;

public class Sum implements ISum {
    private String tag; 
    private List<Object> args;
    public Sum(String tag,List<Object> args){
        this.tag = tag; 
        this.args = args; 
    }
    public String getTag(){
        return this.tag; 
    } 
    public List<Object> getArguments(){
        return this.args; 
    } 
}
