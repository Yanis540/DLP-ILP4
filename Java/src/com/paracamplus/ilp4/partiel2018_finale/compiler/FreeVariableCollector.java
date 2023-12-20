package com.paracamplus.ilp4.partiel2018_finale.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTvisitor;

public class FreeVariableCollector 
extends com.paracamplus.ilp4.compiler.FreeVariableCollector
implements IASTvisitor<Void, Set<IASTClocalVariable>, CompilationException>{

    public FreeVariableCollector(IASTCprogram program) {
        super(program);
    }

    @Override
    public Void visit(IASTsuperWithArgs iast, Set<IASTClocalVariable> variables) throws CompilationException {
        for(IASTexpression arg : iast.getArguments()){
            arg.accept(this, variables);
        }
        return null; 
    }
    
}
