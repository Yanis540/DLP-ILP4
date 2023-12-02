package com.paracamplus.ilp4.partiel2022.interpreter.primitive;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.Primitive;

public class GetListeElement extends Primitive{

    public GetListeElement() {
        super("get");
    }

    @Override
    public int getArity() {
        return 2; 
    }

    @Override
	public Object apply (Object l,Object i) throws EvaluationException {
        if(!(i instanceof BigInteger))
            throw new EvaluationException("Index for get should be an integer");
        int index= ((BigInteger) i).intValue();
        if (index<0 )
            throw new EvaluationException("index can't be negative");
        if(!(l instanceof List))
            throw new EvaluationException("Not a list");
        List<Object> liste= (List) l;
        int size = liste.size();
        if(size == 0 )
            throw new EvaluationException("Can't use get function on Empty list");
        if(index>size)
            throw new EvaluationException("Index out of bound");
        
        return liste.get(index);
    }
    
}
