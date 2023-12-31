package com.paracamplus.partiels.finale2022.interpreter;
import java.io.Writer;
import java.math.BigDecimal;

import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.primitive.Newline;
import com.paracamplus.ilp1.interpreter.primitive.Print;
import com.paracamplus.ilp1.interpreter.primitive.ToString;
import com.paracamplus.ilp4.interpreter.primitive.TypeOf;
import com.paracamplus.partiels.finale2022.interpreter.primitive.GetList;
import com.paracamplus.ilp3.interpreter.primitive.Throw;


public class GlobalVariableStuff extends com.paracamplus.ilp1.interpreter.GlobalVariableStuff{
    public static void fillGlobalVariables (
            IGlobalVariableEnvironment env,
            Writer out ) {
        env.addGlobalVariableValue("pi", new BigDecimal("3.1415926535"));
        env.addGlobalVariableValue(new Print(out));
        env.addGlobalVariableValue(new Newline(out));
        env.addGlobalVariableValue(new ToString());
        env.addGlobalVariableValue(new TypeOf());
        env.addGlobalVariableValue(new Throw());
        env.addGlobalVariableValue(new GetList());
  }
}
