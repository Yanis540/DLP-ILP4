package com.paracamplus.ilp4.partiel2016.ast;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTfactory;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTinvocation;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTprogram;

public class ASTfactory extends com.paracamplus.ilp4.ast.ASTfactory implements IASTfactory{

    @Override
    public IASTinvocation newInvocation(IASTexpression function,IASTexpression[] arguments, IASTvariable[] optionalVariables,
            IASTexpression[] optionalArgumentsValues) {
        return new ASTinvocation(function, arguments, optionalVariables, optionalArgumentsValues);
    }
    @Override
    public IASTfunctionDefinition newFunctionDefinition(IASTvariable functionVariable, IASTvariable[] variables,
            IASTexpression body, Boolean areOptionalVariables, IASTvariable[] optionalVariables,
            IASTexpression[] defaultOptionaVariablesValues) {
        return new ASTfunctionDefinition(functionVariable, variables, body, areOptionalVariables, optionalVariables, defaultOptionaVariablesValues);
    }

    @Override
    public IASTprogram newProgram(IASTfunctionDefinition[] functions, IASTclassDefinition[] clazzes,
            IASTexpression expression) {
        return new ASTprogram(functions, clazzes, expression);
    }
    
}
