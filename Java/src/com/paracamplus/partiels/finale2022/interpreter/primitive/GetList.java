package com.paracamplus.partiels.finale2022.interpreter.primitive;

import java.io.IOException;
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


         
    @Override
	public Object apply (Object l, Object i) throws EvaluationException {
        // cast l + i 
        return 1; 
    }

    
}
