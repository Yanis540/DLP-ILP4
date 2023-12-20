package com.paracamplus.ilp4.partiel2018.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTfactory;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.partiel2018.interfaces.IASTprogram;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory {

    @Override
    public IASTfunctionDefinition newFunctionDefinition(IASTvariable functionVariable, IASTvariable[] variables,
            IASTexpression body, Boolean countable) {
        return new ASTfunctionDefinition(functionVariable, variables, body, countable);
    }

    @Override
    public IASTprogram newProgram(IASTfunctionDefinition[] functions,IASTclassDefinition[] clazzes, IASTexpression expression) {
        return new ASTprogram(functions, clazzes, expression);
    }
    
}
