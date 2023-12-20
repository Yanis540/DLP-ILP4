package com.paracamplus.ilp4.partiel2018_finale.compiler.normalizer;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.compiler.interfaces.IASTCclassDefinition;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTsuperWithArgs;
import com.paracamplus.ilp4.partiel2018_finale.interfaces.IASTvisitor;

public class Normalizer 
extends com.paracamplus.ilp4.compiler.normalizer.Normalizer 
implements 
 IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException>{
    private INormalizationFactory factoryPartiel ; 
    public Normalizer(INormalizationFactory factory, IASTCclassDefinition objectClass) {
        super(factory, objectClass);
        this.factoryPartiel  = factory; 
    }

    @Override
    public IASTexpression visit(IASTsuperWithArgs iast, INormalizationEnvironment lexenv) throws CompilationException {
        IASTexpression[] args  = new IASTexpression[iast.getArguments().length];
        int i = 0 ; 
        for(IASTexpression a : iast.getArguments()){
            args[i] = a.accept(this, lexenv);
            i++; 
        }
        return factoryPartiel.newSuperWithArgs(args);
    }
    
}
