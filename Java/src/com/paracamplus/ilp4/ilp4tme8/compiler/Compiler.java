package com.paracamplus.ilp4.ilp4tme8.compiler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Set;

import com.paracamplus.ilp1.compiler.AssignDestination;
import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.NoDestination;
import com.paracamplus.ilp1.compiler.ReturnDestination;
import com.paracamplus.ilp1.compiler.interfaces.IASTCglobalVariable;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.compiler.interfaces.IASTClambda;
import com.paracamplus.ilp4.compiler.interfaces.IASTCclassDefinition;
import com.paracamplus.ilp4.compiler.interfaces.IASTCmethodDefinition;
import com.paracamplus.ilp4.compiler.interfaces.IASTCprogram;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASThasProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTreadProprety;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTvisitor;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTwriteProprety;
import com.paracamplus.ilp4.interfaces.IASTprogram;
import com.paracamplus.ilp4.ilp4tme8.compiler.normalizer.INormalizationFactory;
import com.paracamplus.ilp4.ilp4tme8.compiler.normalizer.NormalizationFactory;
import com.paracamplus.ilp4.ilp4tme8.compiler.normalizer.Normalizer;

public class Compiler extends com.paracamplus.ilp4.compiler.Compiler
implements IASTvisitor<Void, Compiler.Context, CompilationException> {

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
    public String compile(IASTprogram program, IASTCclassDefinition objectClass) throws CompilationException {

        IASTCprogram newprogram = normalize(program, objectClass);
        newprogram = (IASTCprogram) optimizer.transform(newprogram);

        GlobalVariableCollector gvc = new GlobalVariableCollector();
        Set<IASTCglobalVariable> gvs = gvc.analyze(newprogram);
        newprogram.setGlobalVariables(gvs);

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
    public Void visit(IASTCprogram iast, Context context) throws CompilationException {
        emit(cProgramPrefix);
        
        emit(cGlobalVariablesPrefix);
        for ( IASTCglobalVariable gv : iast.getGlobalVariables() ) {
            emit("ILP_Object ");
            emit(gv.getMangledName());
            emit(";\n");
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
                visit(md, context);
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
     
    protected String cProgramPrefix = ""
        + "#include <stdio.h> \n"
        + "#include <stdlib.h> \n"
        + "#include \"ilp.h\" \n"
        + "#include \"proprety.h\" \n\n"
    ;
    
    @Override
    public Void visit(IASTreadProprety iast, Context context) throws CompilationException {
        emit("{ \n");

        IASTvariable tmpProperty = context.newTemporaryVariable();
        emit("  ILP_Object " + tmpProperty.getMangledName() + "; \n");
        Context cProperty = context.redirect(new AssignDestination(tmpProperty));
        iast.getProprety().accept(this, cProperty);

        IASTvariable tmpField = context.newTemporaryVariable();
        emit("  ILP_Object " + tmpField.getMangledName() + "; \n");
        Context cInstance = context.redirect(new AssignDestination(tmpField));
        iast.getTarget().accept(this, cInstance);

        emit(context.destination.compile());
        emit("ILP_read_property(");
        emit(tmpField.getMangledName());
        emit(", ");
        emit(tmpProperty.getMangledName());
        emit("); \n");

        emit("} \n");

        return null;
    }

    @Override
    public Void visit(IASTwriteProprety iast, Context context) throws CompilationException {
        emit("{ \n");

        IASTvariable tmpProperty = context.newTemporaryVariable();
        emit("  ILP_Object " + tmpProperty.getMangledName() + "; \n");
        Context cProperty = context.redirect(new AssignDestination(tmpProperty));
        iast.getProprety().accept(this, cProperty);

        IASTvariable tmpField = context.newTemporaryVariable();
        emit("  ILP_Object " + tmpField.getMangledName() + "; \n");
        Context cInstance = context.redirect(new AssignDestination(tmpField));
        iast.getTarget().accept(this, cInstance);

        IASTvariable tmpValue = context.newTemporaryVariable();
        emit("  ILP_Object " + tmpValue.getMangledName() + "; \n");
        Context cValue = context.redirect(new AssignDestination(tmpValue));
        iast.getValue().accept(this, cValue);

        emit(context.destination.compile());
        emit("ILP_write_property(");
        emit(tmpField.getMangledName());
        emit(", ");
        emit(tmpProperty.getMangledName());
        emit(", ");
        emit(tmpValue.getMangledName());
        emit("); \n");

        emit("} \n");

        return null;

    }

    @Override
    public Void visit(IASThasProprety iast, Context context) throws CompilationException {
        emit("{ \n");

        IASTvariable tmpProperty = context.newTemporaryVariable();
        emit("  ILP_Object " + tmpProperty.getMangledName() + "; \n");
        Context cProperty = context.redirect(new AssignDestination(tmpProperty));
        iast.getProprety().accept(this, cProperty);

        IASTvariable tmpField = context.newTemporaryVariable();
        emit("  ILP_Object " + tmpField.getMangledName() + "; \n");
        Context cInstance = context.redirect(new AssignDestination(tmpField));
        iast.getTarget().accept(this, cInstance);

        emit(context.destination.compile());
        emit("ILP_has_property(");
        emit(tmpField.getMangledName());
        emit(", ");
        emit(tmpProperty.getMangledName());
        emit("); \n");

        emit("} \n");

        return null;
    }
    
}
