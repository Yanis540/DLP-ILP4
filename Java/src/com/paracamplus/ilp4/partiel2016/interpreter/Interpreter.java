package com.paracamplus.ilp4.partiel2016.interpreter;

import java.util.List;
import java.util.Vector;

import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.Invocable;
import com.paracamplus.ilp3.interpreter.primitive.Throw.ThrownException;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTinvocation;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTprogram;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTvisitor;
import com.paracamplus.ilp4.partiel2016.interfaces.IFunction;

public class Interpreter extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException>  {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }
    @Override
	public Object visit(com.paracamplus.ilp1.interfaces.IASTprogram iast, ILexicalEnvironment lexenv) throws EvaluationException  {
    	return visit((IASTprogram)iast, lexenv);
    }
     public Object visit(IASTprogram iast, ILexicalEnvironment lexenv) 
            throws EvaluationException {
        for ( IASTclassDefinition cd : iast.getClassDefinitions() ) {
            this.visit(cd, lexenv);
        }
        for ( IASTfunctionDefinition fd : iast.getFunctionDefinitions() ) {
            Object f = this.visit(fd, lexenv);
            String v = fd.getName();
            getGlobalVariableEnvironment().addGlobalVariableValue(v, f);
        }
        try {
            return iast.getBody().accept(this, lexenv);
        } catch (ThrownException exc) {
            return exc.getThrownValue();
        } catch (Exception exc) {
            return exc;
        }
    }
   
    public IFunction visit(IASTfunctionDefinition iast,ILexicalEnvironment lexenv){
        IFunction fun = new Function(iast.getVariables(),
            iast.getBody(),
            new EmptyLexicalEnvironment(), 
            iast.areOptionalVariables(), 
            iast.getOptionalVariables(), 
            iast.getOptionalVariablesDefaultValues()
        );
        return fun;
    }

    @Override
    public Object visit(IASTinvocation iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object function = iast.getFunction().accept(this, lexenv);
        if(!(function instanceof Invocable)){
            String msg = "Cannot apply " + function;
            throw new EvaluationException(msg);
        }
        if ( !(function instanceof IFunction) ) {
            return super.visit(iast,lexenv);
        } 

        // getting the arguments 

        IFunction f = (IFunction)function;
        IASTvariable[] optionalVariables = f.getOptionalVariables();
        IASTexpression[] defaultOptionalValues = f.getOptionalVariablesDefaultValues();
        IASTexpression[] optionalArgsValues= iast.getOptionalArgumentsValues();
        IASTvariable[] optionalVariablesArguments = iast.getOptionalVariablesArguments();
        List<Object> args = new Vector<Object>();
        // calculating the arguments 
        for ( IASTexpression arg : iast.getArguments() ) {
            Object value = arg.accept(this, lexenv);
            args.add(value);
        }
        // adding the optional arguments 
        int i = 0 ; 
        for(IASTvariable optVar : optionalVariables){
            Object value; 
            // get the value of the opt variable 
            // if the variable is in the optioanArgs then value == defaultOptional values 
            int indexOptionalValue = exists(optVar,optionalVariablesArguments);
            if(indexOptionalValue == -1)
                value = defaultOptionalValues[i].accept(this, lexenv);
            // else the variable is got from the 
            else 
                value = optionalArgsValues[indexOptionalValue].accept(this, lexenv);
                i++; 
            args.add(value);

            
        }
        return f.apply(this, args.toArray());

    }
    // exists : returns the index of the variable if it exists in the optional variables arguments 
    public int exists(IASTvariable var,IASTvariable[] optionalVariablesArguments){
        int i= 0 ; 
        for(IASTvariable optVar : optionalVariablesArguments){
            if(var.getName().equals(optVar.getName()))
                return i; 
            i++; 
        }
        return -1; 
    }
}
