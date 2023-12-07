package com.paracamplus.ilp4.ilp4tme9.compiler;

import com.paracamplus.ilp1.compiler.CompilationException;
import com.paracamplus.ilp1.compiler.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.compiler.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.compiler.interfaces.IASTCprogram;

public class CompilerCachless extends com.paracamplus.ilp4.compiler.Compiler {
    
    public CompilerCachless(IOperatorEnvironment ioe, IGlobalVariableEnvironment igve) {
        super(ioe, igve);
    }

    @Override
    public Void visit(IASTCprogram iast, Context context) throws CompilationException {
        cProgramPrefix = ""
            + "#include <stdio.h> \n"
            + "#include <stdlib.h> \n"
            + "#include <time.h> \n"
            + "#include \"ilp.h\" \n"
            + "#include \"cache.h\" \n\n"
        ;
        cProgramSuffix = "\n"
            + "static ILP_Object ilp_caught_program () {\n"
            + "  struct ILP_catcher* current_catcher = ILP_current_catcher;\n"
            + "  struct ILP_catcher new_catcher;\n\n"
            + "  if ( 0 == setjmp(new_catcher._jmp_buf) ) {\n"
            + "    ILP_establish_catcher(&new_catcher);\n"
            + "    return ilp_program();\n"
            + "  };\n"
            + "  return ILP_current_exception;\n"
            + "}\n\n"
            + "int main (int argc, char *argv[]) \n"
            + "{ \n"
            + "  clock_t start, end; \n" // temps d'exécution
            + "  start = clock(); \n" //début
            + "  ILP_START_GC; \n"
            + "  ILP_print(ilp_caught_program()); \n"
            + "  ILP_newline(); \n"
            + "  end = clock(); \n" //fin
            + "  fprintf(stderr, \"Temps d'exécution: %f millisecondes\\n \", (float)(end-start)*1000/CLOCKS_PER_SEC ); \n"
            + "  return EXIT_SUCCESS; \n"
            + "} \n"
        ;
        return super.visit(iast, context);
    }
}
