package com.paracamplus.ilp4.partiel2016.interpreter;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp4.partiel2016.interfaces.IFunction;

public class Function extends com.paracamplus.ilp1.interpreter.Function implements IFunction {
    private Boolean areOptionalVariablesB ;  
    private IASTvariable[] optionalVariables ;  
    private IASTexpression[] defaultOptionaVariablesValues ;  
    public Function(IASTvariable[] variables, IASTexpression body, ILexicalEnvironment lexenv, 
        Boolean areOptionalVariables, 
        IASTvariable[] optionalVariables,
        IASTexpression[] defaultOptionaVariablesValues
    ) {
        super(variables, body, lexenv);
        this.areOptionalVariablesB = areOptionalVariables; 
        this.optionalVariables = optionalVariables; 
        this.defaultOptionaVariablesValues = defaultOptionaVariablesValues; 
    }
    @Override 
    public int getArity(){
        return super.getArity()+optionalVariables.length; 
    }
    @Override
    public Boolean areOptionalVariables() {
        return this.areOptionalVariablesB; 
    }
    @Override
    public IASTvariable[] getOptionalVariables() {
        return this.optionalVariables;
    }
    @Override
    public IASTexpression[] getOptionalVariablesDefaultValues() {
        return this.defaultOptionaVariablesValues;
    }

	public Object apply(Interpreter interpreter, Object[] argument) 
            throws EvaluationException {
        if ( argument.length != getArity() ) {
            String msg = "Wrong arity";
            throw new EvaluationException(msg);
        }
        
        ILexicalEnvironment lexenv2 = getClosedEnvironment();

        IASTvariable[] variables = super.getVariables();
        // setting up the variables 
        for ( int i=0 ; i<variables.length ; i++ ) {
            lexenv2 = lexenv2.extend(variables[i], argument[i]);
        }

        // setting up the opational variables 
        for(int i =variables.length; i<argument.length;i++){
            lexenv2 = lexenv2.extend(optionalVariables[i-variables.length],argument[i]);
        }
        return getBody().accept(interpreter, lexenv2);
    }
    
}
