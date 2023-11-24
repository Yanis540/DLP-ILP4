package com.paracamplus.ilp4.ilp4tme8.interpreter;

import java.util.HashMap;
import java.util.Map;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp4.interpreter.interfaces.IClass;


public class ILPInstance extends com.paracamplus.ilp4.interpreter.ILPInstance{
    private Map<String,Object> dynamicFields; 
    public ILPInstance(IClass clazz, Object[] staticFields) throws EvaluationException {
        super(clazz, staticFields);
        this.dynamicFields = new HashMap<>();
    }
    public Object writeDynamiqueField(String fieldName,Object value){
        Object old=null;
        try{
            old= readDynamique(fieldName);
        }
        catch(EvaluationException e){

        }
        dynamicFields.put(fieldName,value);
        return old; 
    }
    public Object readDynamique(String fieldName) throws EvaluationException{
        dynamicFields.get(fieldName);
        for(Map.Entry<String,Object> field  : dynamicFields.entrySet()){
            if(field.getKey().equals(fieldName))
                return field.getValue();
        }
        throw new EvaluationException("Field "+ fieldName+" doesn't exist  neither dynamic field on Class : "+ classOf().getName());
    }
    @Override
	public Object read(String fieldName) 
            throws EvaluationException {
        try{
            return super.read(fieldName);
        }
        catch(EvaluationException E){
            return readDynamique(fieldName);
        }
    }

    @Override
	public Object write(String fieldName, Object value) 
            throws EvaluationException {
        try{
            return super.write(fieldName, value);
        }
        catch(EvaluationException e){
            return writeDynamiqueField(fieldName, value);
        }
    }
    
}
