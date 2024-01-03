package com.paracamplus.partiels.finale2022_2.interpreter.primitive;
import java.math.BigInteger;
import java.util.List;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.primitive.Primitive;
public class GetList extends Primitive{

	public GetList() {
		super("get");
	}
    public int getArity(){
        return 2; 
    }
    public Object apply(Object l,Object i) throws EvaluationException{
        if(!(l instanceof List))
            throw new EvaluationException("Expected list but got " + l.getClass());
        List<Object> list= (List)l;
        if(!(i instanceof BigInteger))
            throw new EvaluationException("Expected integer as index but got "+i.getClass());
        int index = ((BigInteger)i).intValue();
        if(index<0 || index >= list.size()) 
            throw new EvaluationException("Index out of bound");
          
        return list.get(index); 
    }
}
