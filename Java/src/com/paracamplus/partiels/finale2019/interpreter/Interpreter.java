package com.paracamplus.partiels.finale2019.interpreter;

import java.util.Map;

import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.IGlobalVariableEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.IOperatorEnvironment;
import com.paracamplus.ilp4.interpreter.ILPInstance;
import com.paracamplus.ilp4.interpreter.interfaces.IClassEnvironment;
import com.paracamplus.ilp4.interpreter.interfaces.IMethod;
import com.paracamplus.partiels.finale2019.interfaces.IASTautoClose;
import com.paracamplus.partiels.finale2019.interfaces.IASTvisitor;

public class Interpreter  extends com.paracamplus.ilp4.interpreter.Interpreter
implements IASTvisitor<Object, ILexicalEnvironment, EvaluationException> {

    public Interpreter(IGlobalVariableEnvironment globalVariableEnvironment, IOperatorEnvironment operatorEnvironment,
            IClassEnvironment classEnvironment) {
        super(globalVariableEnvironment, operatorEnvironment, classEnvironment);
    }

    @Override
    public Object visit(IASTautoClose iast, ILexicalEnvironment lexenv) throws EvaluationException {
        Object v =  iast.getValue().accept(this, lexenv);
        if(!(v instanceof ILPInstance))
            throw new EvaluationException("Expected Instance but got "+ v.getClass());
        ILPInstance i = (ILPInstance) v; 
        Map<String,IMethod> methods= i.classOf().getMethodDictionary();
        if(! (methods.containsKey("close")))
            throw new EvaluationException("close method doesn't exist ");
        IMethod method = methods.get("close");
        if(method.getArity()!=1)
            throw new EvaluationException("Close method should not have paramters");
        try{
            ILexicalEnvironment lexenv2 = lexenv; 
            lexenv2 = lexenv2.extend(iast.getVariable(), i);
            v = iast.getBody().accept(this,lexenv2);
            i.send(this,"close",new Object[0]);
            return v; 
        }
        catch(Exception e){
            i.send(this,"close",new Object[0]);
            throw new EvaluationException(e.getMessage());
        }
    }
    
}
