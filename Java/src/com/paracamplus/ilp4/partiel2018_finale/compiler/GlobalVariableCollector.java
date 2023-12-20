package com.paracamplus.ilp4.partiel2018_finale.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTvisitor;

public class GlobalVariableCollector 
extends com.paracamplus.ilp4.compiler.GlobalVariableCollector
implements IASTvisitor<Set<IASTCglobalVariable>, 
                        Set<IASTCglobalVariable>, 
                        CompilationException>  {

	@Override
	public Set<IASTCglobalVariable> visit(IASTsuperWithArgs iast, Set<IASTCglobalVariable> result)
			throws CompilationException {
        for(IASTexpression arg : iast.getArguments()){
            result = arg.accept(this, result);
        }
        return result; 
	}
    
}
