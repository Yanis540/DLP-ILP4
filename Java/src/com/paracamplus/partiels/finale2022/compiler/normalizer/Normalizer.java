package com.paracamplus.partiels.finale2022.compiler.normalizer;

import org.hamcrest.core.Is;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.normalizer.INormalizationEnvironment;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.compiler.interfaces.IASTCclassDefinition;
import com.paracamplus.partiels.finale2022.interfaces.IASTlist;
import com.paracamplus.partiels.finale2022.interfaces.IASTlistRange;
import com.paracamplus.partiels.finale2022.interfaces.IASTvisitor;

public class Normalizer 
extends com.paracamplus.ilp4.compiler.normalizer.Normalizer 
implements 
 IASTvisitor<IASTexpression, INormalizationEnvironment, CompilationException> {
    private INormalizationFactory myFactory; 
    public Normalizer(INormalizationFactory factory, IASTCclassDefinition objectClass) {
        super(factory, objectClass);
    }
    @Override
    public IASTexpression visit(IASTlist iast, INormalizationEnvironment lexenv) throws CompilationException {
        IASTexpression[] exprs = new IASTexpression[iast.getExpressions().length]; 
        int i=0 ; 
        for(IASTexpression expr : iast.getExpressions()){
            exprs[i] = expr.accept(this,lexenv);
        }
        return myFactory.newList(exprs); 
    }
    @Override
    public IASTexpression visit(IASTlistRange iast, INormalizationEnvironment lexenv) throws CompilationException {
        return myFactory.newListRange(
            iast.getBody().accept(this, lexenv), 
            iast.getVariable(),
            iast.getMax().accept(this,lexenv),
            iast.getCondition().accept(this,lexenv), 
            iast.IsCondition()
        );
    }
    
    
}
