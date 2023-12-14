package com.paracamplus.ilp4.ilp4tme10.compiler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.NoDestination;
import com.paracamplus.ilp1.compiler.ReturnDestination;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.compiler.interfaces.IASTClocalVariable;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.compiler.interfaces.IASTClambda;
import com.paracamplus.ilp4.compiler.interfaces.IASTCclassDefinition;
import com.paracamplus.ilp4.compiler.interfaces.IASTCmethodDefinition;
import com.paracamplus.ilp4.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp4.ilp4tme10.compiler.interfaces.IASTCvisitor;
import com.paracamplus.ilp4.ilp4tme10.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp4.ilp4tme10.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp4.ilp4tme10.compiler.normalizer.Normalizer;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;
import com.paracamplus.ilp4.interfaces.IASTprogram;

public class Compiler extends com.paracamplus.ilp4.compiler.Compiler
implements IASTCvisitor<Void, Compiler.Context, CompilationException>  {
    private Set<IASTCglobalVariable> globals;
    public Compiler(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve) {
        super(ioe, igve);
    }

    public IASTCprogram normalize(IASTprogram program, 
            IASTCclassDefinition objectClass) 
            		throws CompilationException {
    	INormalizationFactory nf = new NormalizationFactory();
    	Normalizer normalizer = new Normalizer(nf, objectClass);
    	IASTCprogram newprogram = normalizer.transform(program);
    	return newprogram;
    }
    
    @Override
    public Void visit(IASTCprogram iast, Context context)
            throws CompilationException {
        emit(cProgramPrefix);
        
        emit(cGlobalVariablesPrefix);
        for ( IASTCglobalVariable gv : iast.getGlobalVariables() ) {
            emit("ILP_Object ");
            emit(gv.getMangledName());
            emit(" = NULL;\n"); // Modification TME10
        }
        emit(cGlobalVariablesSuffix);
        
        emit(cPrototypesPrefix);
        Context c = context.redirect(NoDestination.NO_DESTINATION);
        for ( IASTfunctionDefinition ifd : iast.getFunctionDefinitions() ) {
            this.emitPrototype(ifd, c);
        }
        for ( IASTClambda closure : iast.getClosureDefinitions() ) {
            this.emitPrototype(closure, c);
        }
        emit(cFunctionsPrefix);
        for ( IASTfunctionDefinition ifd : iast.getFunctionDefinitions() ) {
            this.visit(ifd, c);
            emitClosure(ifd, c);
        }
        for ( IASTClambda closure : iast.getClosureDefinitions() ) {
            this.emitFunction(closure, c);
        }
        emit(cFunctionsSuffix);
        
        emit(cClassPrefix);
        for ( IASTCclassDefinition cd : iast.getClassDefinitions() ) {
            emitClassHeader(cd);
            visit(cd, c);
        }
        for ( IASTCclassDefinition cd : iast.getClassDefinitions() ) {
            for ( IASTCmethodDefinition md : cd.getProperMethodDefinitions() ) {
                visit((IASTCmethodDefinition)md, context);
            }
        }        
        emit(cClassSuffix);
        
        emit(cBodyPrefix);
        Context cr = context.redirect(ReturnDestination.RETURN_DESTINATION);
        iast.getBody().accept(this, cr);
        emit(cBodySuffix);
        
        emit(cProgramSuffix);
        return null;
    }


    // Branchement sur les collecteurs de variables étendus
	@Override
    public String compile(IASTprogram program, 
    		IASTCclassDefinition objectClass) 
    				throws CompilationException {

    	IASTCprogram newprogram = normalize(program, objectClass);
    	newprogram = (IASTCprogram) optimizer.transform(newprogram);

    	GlobalVariableCollector gvc = new GlobalVariableCollector();
    	Set<IASTCglobalVariable> gvs = gvc.analyze(newprogram);
    	newprogram.setGlobalVariables(gvs);
    	globals = gvs;
    	
    	FreeVariableCollector fvc = new FreeVariableCollector(newprogram);
    	newprogram = fvc.analyze();

    	Context context = new Context(NoDestination.NO_DESTINATION);
    	StringWriter sw = new StringWriter();
    	try {
    		out = new BufferedWriter(sw);
    		visit(newprogram, context);
    		out.flush();
    	} catch (IOException exc) {
    		throw new CompilationException(exc);
    	}
    	return sw.toString();
    }


    @Override
    public Void visit(IASTexists iast, Context context) throws CompilationException {
        IASTvariable variable = iast.getVariable();
        boolean exists = 
            (variable instanceof IASTClocalVariable) 
            || (globals.contains(variable))
            ||((IGlobalVariableEnvironment)globalVariableEnvironment).contains(variable)
        ;
        System.out.println(">>>>>>>"+exists+" "+iast.getVariable());
        emit(context.destination.compile());
        emit(exists ? "ILP_TRUE" : "ILP_FALSE");
        emit("; \n");
        return null;
    }

    
    
}
