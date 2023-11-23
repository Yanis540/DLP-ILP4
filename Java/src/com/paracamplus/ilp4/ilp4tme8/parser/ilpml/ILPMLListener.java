package com.paracamplus.ilp4.ilp4tme8.parser.ilpml;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp2.interfaces.IASTdeclaration;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp3.interfaces.IASTlambda;
import com.paracamplus.ilp3.interfaces.IASTnamedLambda;
import com.paracamplus.ilp4.ilp4tme8.interfaces.IASTfactory;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import antlr4.ILPMLgrammar4Tme8Listener;
import antlr4.ILPMLgrammar4Tme8Parser.HasPropertyContext;
import antlr4.ILPMLgrammar4Tme8Parser.ReadPropertyContext;
import antlr4.ILPMLgrammar4Tme8Parser.WritePropertyContext;

import static antlr4.ILPMLgrammar4Tme8Parser.*;


public class ILPMLListener implements ILPMLgrammar4Tme8Listener {
    
	protected IASTfactory factory;
	
	public ILPMLListener(IASTfactory factory) {
		super();
		this.factory = factory;		
	}
    

    @Override
    public void enterHasProperty(HasPropertyContext ctx) {
    }


    @Override
    public void exitHasProperty(HasPropertyContext ctx) {
        ctx.node = factory.newHasProprety(ctx.obj.node,ctx.property.node);
    }
    

    @Override
    public void enterWriteProperty(WritePropertyContext ctx) {
    }


    @Override
    public void exitWriteProperty(WritePropertyContext ctx) {
        ctx.node = factory.newWriteProprety(ctx.obj.node, ctx.property.node, ctx.val.node);
    }


    @Override
    public void enterReadProperty(ReadPropertyContext ctx) {
    }


    @Override
    public void exitReadProperty(ReadPropertyContext ctx) {
        ctx.node = factory.newReadProprety(ctx.obj.node,ctx.property.node);

    }

	
	/*
	 * Il faut se souvenir du nom de la classe en cours de définition pour
	 * créer correctement les sous-nœuds IASTmethodDefinition.
	 */
	protected String className = "<unknown>";

	
	// ILP1

	public void exitVariable(VariableContext ctx) { 
		ctx.node = factory.newVariable(ctx.getText());
	}

	
	public void exitInvocation(
			InvocationContext ctx) { 
		ctx.node = factory.newInvocation(
				ctx.fun.node, 
				toExpressions(ctx.args));
	}

	
	public void exitConstFloat(
			ConstFloatContext ctx) { 
		ctx.node = factory.newFloatConstant(ctx.floatConst.getText());
	}

	
	public void	exitConstInteger(
			ConstIntegerContext ctx) { 
		ctx.node = factory.newIntegerConstant(ctx.intConst.getText());
	}

	
	public void exitBinding(BindingContext ctx) { 
		ctx.node = factory.newBlock(
				toBindings(ctx.vars, ctx.vals),
				ctx.body.node);
	}

	
	public void exitAlternative(
			AlternativeContext ctx) { 
		ctx.node = factory.newAlternative(
				ctx.condition.node, 
				ctx.consequence.node, 
				(ctx.alternant == null ? null : ctx.alternant.node));
	}

	
	public void exitSequence(
			SequenceContext ctx) {
		ctx.node = factory.newSequence(toExpressions(ctx.exprs));
	}

	
	public void exitConstFalse(
			ConstFalseContext ctx) { 
		ctx.node = factory.newBooleanConstant("false");
	}

	
	public void exitUnary(UnaryContext ctx) { 
		ctx.node = factory.newUnaryOperation(
				factory.newOperator(ctx.op.getText()),
				ctx.arg.node);
	}

	
	public void exitConstTrue(
			ConstTrueContext ctx) {
		ctx.node = factory.newBooleanConstant("true");
	}

	
	public void exitConstString(
			ConstStringContext ctx) { 
		/*
		 * On enlève le " initial et final, et on interprète les codes
		 * d'échapement \n, \r, \t, \"
		 */
		String s = ctx.getText();
		StringBuilder buf = new StringBuilder();
		for (int i = 1; i < s.length() - 1; i++) {
			if (s.charAt(i) == '\\' && i < s.length() - 2) {
				switch (s.charAt(i+1)) {
				case 'n': buf.append('\n'); i++; break;
				case 'r': buf.append('\r'); i++; break;
				case 't': buf.append('\t'); i++; break;
				case '"': buf.append('\"'); i++; break;
				default: buf.append(s.charAt(i));
				}
			}
			else buf.append(s.charAt(i));
		}
		ctx.node = factory.newStringConstant(buf.toString());
	}

	
	public void exitBinary(BinaryContext ctx) { 
		ctx.node = factory.newBinaryOperation(
				factory.newOperator(ctx.op.getText()),
				ctx.arg1.node,
				ctx.arg2.node);				
	}

		
	
	/* Utilitaires de conversion ANTLR vers AST */
	
	protected IASTexpression[] toExpressions(
			List<ExprContext> ctxs) {
		if (ctxs == null) return new IASTexpression[0];
		IASTexpression[] r = new IASTexpression[ctxs.size()];
		int pos = 0;
		for (ExprContext e : ctxs) {
			r[pos++] = e.node;
		}
		return r;
	}
	
	protected IASTvariable[] toVariables(
			List<Token> vars, boolean addSelf) {
		if (vars == null) return new IASTvariable[0];
		IASTvariable[] r = new IASTvariable[vars.size() + (addSelf ? 1 : 0)];
		int pos = 0;
		if (addSelf) {
			// Les déclarations de méthodes ont une variable "self" implicite
			r[pos++] = factory.newVariable("self");
		}
		for (Token v : vars) {
			r[pos++] = factory.newVariable(v.getText());
		}
		return r;
	}

	protected String[] toStrings(List<Token> vars) {
		if (vars == null) return new String[0];
		String[] r = new String[vars.size()];
		int pos = 0;
		for (Token v : vars) {
			r[pos++] = v.getText();
		}
		return r;
	}

	protected IASTblock.IASTbinding[] toBindings(
			List<Token> vars, 
			List<ExprContext> exprs) {
		if (vars == null) return new IASTblock.IASTbinding[0];
		/* par construction, vars et ctxs ont la même taille */
		assert(vars.size() == exprs.size());
		IASTblock.IASTbinding[] r = new IASTblock.IASTbinding[exprs.size()];
		int pos = 0;
		for (Token v : vars) {
			r[pos] = factory.newBinding(
					factory.newVariable(v.getText()),
					exprs.get(pos).node
					);
			pos++;
		}
		return r;			
	}

	public void enterEveryRule(ParserRuleContext arg0) {}
	public void exitEveryRule(ParserRuleContext arg0) {}
	public void visitErrorNode(ErrorNode arg0) {}
	public void visitTerminal(TerminalNode arg0) {}
	public void enterConstInteger(ConstIntegerContext ctx) {}
	public void enterProg(ProgContext ctx) {}
	public void enterConstFloat(ConstFloatContext ctx) {}
	public void enterVariable(VariableContext ctx) {}
	public void enterBinary(BinaryContext ctx) {}
	public void enterAlternative(AlternativeContext ctx) {}	
	public void enterConstFalse(ConstFalseContext ctx) {}
	public void enterSequence(SequenceContext ctx) {}
	public void enterConstTrue(ConstTrueContext ctx) {}
	public void enterBinding(BindingContext ctx) {}
	public void enterConstString(ConstStringContext ctx) {}
	public void enterUnary(UnaryContext ctx) {}
	public void enterInvocation(InvocationContext ctx) {}


	// ILP2
	
	
	public void exitGlobalFunDef(GlobalFunDefContext ctx) {
		ctx.node = factory.newFunctionDefinition(
				factory.newVariable(ctx.name.getText()),
				toVariables(ctx.vars, false), 
				ctx.body.node);
	}

	
	public void exitVariableAssign(VariableAssignContext ctx) {
		ctx.node = factory.newAssignment(
				factory.newVariable(ctx.var.getText()),
				ctx.val.node);		
	}
   
	
	public void exitLoop(LoopContext ctx) {
		ctx.node = factory.newLoop(ctx.condition.node, ctx.body.node);		
	}

    public void enterGlobalFunDef(GlobalFunDefContext ctx) {}
    public void enterVariableAssign(VariableAssignContext ctx) {}
    public void enterLoop(LoopContext ctx) {}

	
	// ILP3


	public void exitTry(TryContext ctx) {
		IASTlambda catcher = null;
		IASTexpression finallyer = null;
		if (ctx.catcher != null) {
			IASTvariable[] vars = { factory.newVariable(ctx.var.getText()) };
			catcher = factory.newLambda(vars, ctx.catcher.node);
		}
		if (ctx.finallyer != null) finallyer = ctx.finallyer.node;
		ctx.node = factory.newTry(ctx.body.node, catcher, finallyer);		
	}

	
	public void exitLocalFunDef(LocalFunDefContext ctx) {
		ctx.node = factory.newNamedLambda(
				factory.newVariable(ctx.name.getText()),
				toVariables(ctx.vars, false), 
				ctx.body.node);
	}

	
	public void exitCodefinitions(CodefinitionsContext ctx) {
		ctx.node = factory.newCodefinitions(
				toNamedLambdas(ctx.defs), 
				ctx.body.node);
	}

	
	public void exitLambda(LambdaContext ctx) {
		ctx.node =	factory.newLambda(toVariables(ctx.vars, false), ctx.body.node);
	}

	protected IASTnamedLambda[] toNamedLambdas(
			List<LocalFunDefContext> ctxs) {
		if (ctxs == null) return new IASTnamedLambda[0];
		IASTnamedLambda[] r = new IASTnamedLambda[ctxs.size()];
		int pos = 0;
		for (LocalFunDefContext e : ctxs) {
			r[pos++] = e.node;
		}
		return r;		
	}
	
    public void enterTry(TryContext ctx) {}
    public void enterLocalFunDef(LocalFunDefContext ctx) {}
    public void enterCodefinitions(CodefinitionsContext ctx) {}
    public void enterLambda(LambdaContext ctx) { }

	
	// ILP4

	
	public void exitProg(ProgContext ctx) { 
		// Sépare les définitions de fonctions et de classes
		List<IASTfunctionDefinition> f = new ArrayList<>();
		List<IASTclassDefinition> c = new ArrayList<>();
		for (GlobalDefContext d : ctx.defs) {
			IASTdeclaration x = d.node;
			if (x instanceof IASTfunctionDefinition) 
				f.add((IASTfunctionDefinition)x);
			else if (x instanceof IASTclassDefinition)
				c.add((IASTclassDefinition)x);
		}
		IASTexpression e = factory.newSequence(toExpressions(ctx.exprs));
		ctx.node = factory.newProgram(
				f.toArray(new IASTfunctionDefinition[0]),
				c.toArray(new IASTclassDefinition[0]),
				e);
	}

	
	public void exitSend(SendContext ctx) {
		ctx.node = factory.newSend(
				ctx.field.getText(), 
				ctx.obj.node, 
				toExpressions(ctx.args));
	}

	
	public void exitGlobalFunctionDefinition(GlobalFunctionDefinitionContext ctx) {
		ctx.node = ctx.def.node;
	}

	
	public void exitWriteField(WriteFieldContext ctx) {
		ctx.node = factory.newWriteField(
				ctx.field.getText(),  
				ctx.obj.node,  
				ctx.val.node);
	}

	 
	public void enterClassDef(ClassDefContext ctx) { 
		// On se souvient du nom de la classe, pour exitMethodDef
		className = ctx.name.getText();
	}

	
	public void exitClassDef(ClassDefContext ctx) {
		ctx.node = factory.newClassDefinition(
				ctx.name.getText(),
				(ctx.parent == null) ? "Object" : ctx.parent.getText(),
				toStrings(ctx.fields),
				toMethods(ctx.methods));
		className = "<unknown>";		
	}

	
	public void exitMethodDef(MethodDefContext ctx) {
		ctx.node = factory.newMethodDefinition(
				factory.newVariable(ctx.name.getText()),
				toVariables(ctx.vars, true),
				ctx.body.node,
				ctx.name.getText(),
				className);
	}
	
	
	public void exitReadField(ReadFieldContext ctx) {
		ctx.node = factory.newReadField(ctx.field.getText(), ctx.obj.node);		
	}

	
	public void exitSelf(SelfContext ctx) {
		ctx.node = factory.newSelf();		
	}

	
	public void exitSuper(SuperContext ctx) {
		ctx.node = factory.newSuper();		
	}

	
	public void exitClassDefinition(ClassDefinitionContext ctx) {
		ctx.node = ctx.def.node;
	}

	
	public void exitNew(NewContext ctx) {
		ctx.node = factory.newInstantiation(
				ctx.className.getText(),
				toExpressions(ctx.args));
	}

	protected IASTmethodDefinition[] toMethods(
			List<MethodDefContext> ctxs) {
		if (ctxs == null) return new IASTmethodDefinition[0];
		IASTmethodDefinition[] r = new IASTmethodDefinition[ctxs.size()];
		int pos = 0;
		for (MethodDefContext e : ctxs) {
			r[pos++] = e.node;
		}
		return r;		
	}

	public void enterSend(SendContext ctx) {}
	public void enterGlobalFunctionDefinition(GlobalFunctionDefinitionContext ctx) {}
	public void enterWriteField(WriteFieldContext ctx) {}
	public void enterMethodDef(MethodDefContext ctx) {}
	public void enterReadField(ReadFieldContext ctx) {}
	public void enterSelf(SelfContext ctx) {}
	public void enterSuper(SuperContext ctx) {}
	public void enterClassDefinition(ClassDefinitionContext ctx) {}
	public void enterNew(NewContext ctx) {}

}
