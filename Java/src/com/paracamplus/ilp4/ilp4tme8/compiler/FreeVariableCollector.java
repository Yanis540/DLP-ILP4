package com.paracamplus.ilp4.ilp4tme8.compiler;

import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp4.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProprety;

public class FreeVariableCollector 
extends com.paracamplus.ilp4.compiler.FreeVariableCollector
implements IASTvisitor<Void, Set<IASTClocalVariable>, CompilationException>{
    public FreeVariableCollector(IASTCprogram program) {
        super(program);
    }

    @Override
    public Void visit(IASTreadProprety iast, Set<IASTClocalVariable> variables) throws CompilationException {
        iast.getTarget().accept(this, variables);
        iast.getProprety().accept(this, variables);
        return null;
    }

    @Override
    public Void visit(IASTwriteProprety iast, Set<IASTClocalVariable> variables) throws CompilationException {
         iast.getTarget().accept(this, variables);
        iast.getProprety().accept(this, variables);
        iast.getValue().accept(this, variables);
        return null;
    }

    @Override
    public Void visit(IASThasProprety iast, Set<IASTClocalVariable> variables) throws CompilationException {
        iast.getTarget().accept(this, variables);
        iast.getProprety().accept(this, variables);
        return null;
    }
}
