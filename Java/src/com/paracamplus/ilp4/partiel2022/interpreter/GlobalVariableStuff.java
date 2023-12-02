package com.paracamplus.ilp4.partiel2022.interpreter;

import java.io.Writer;

import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp4.partiel2022.interpreter.primitive.GetListeElement;


public class GlobalVariableStuff extends com.paracamplus.ilp4.interpreter.GlobalVariableStuff {
    public static void fillGlobalVariables (
            IGlobalVariableEnvironment env,
            Writer out ) {
        com.paracamplus.ilp4.interpreter.GlobalVariableStuff.fillGlobalVariables(env, out);
        env.addGlobalVariableValue(new GetListeElement());
    }
}
