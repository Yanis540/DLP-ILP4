package com.paracamplus.ilp4.ilp4tme8.compiler.normalizer;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.compiler.interfaces.IASTCclassDefinition;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProprety;

public class Normalizer
extends com.paracamplus.ilp4.compiler.normalizer.Normalizer 
implements 
 IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {

    public Normalizer(INormalizationFactory factory, IASTCclassDefinition objectClass) {
        super(factory, objectClass);
    }

    @Override
    public IASTexpression visit(IASTreadProprety iast, INormalizationEnvironment lexenv) throws CompilationException {
        IASTexpression target = iast.getTarget().accept(this, lexenv);
        IASTexpression proprety = iast.getProprety().accept(this, lexenv);
        return ((INormalizationFactory)factory).newReadProprety(target,proprety);
    }

    @Override
    public IASTexpression visit(IASTwriteProprety iast, INormalizationEnvironment lexenv) throws CompilationException {
        IASTexpression target = iast.getTarget().accept(this, lexenv);
        IASTexpression proprety = iast.getProprety().accept(this, lexenv);
        IASTexpression value = iast.getValue().accept(this, lexenv);
        return ((INormalizationFactory)factory).newWriteProprety(target, proprety, value);
    }

    @Override
    public IASTexpression visit(IASThasProprety iast, INormalizationEnvironment lexenv) throws CompilationException {
        IASTexpression target = iast.getTarget().accept(this, lexenv);
        IASTexpression proprety = iast.getProprety().accept(this, lexenv);
        return ((INormalizationFactory)factory).newHasProprety(target,proprety);
    }
    
}
