package com.paracamplus.ilp4.ilp4tme10.interpreter;

import java.util.HashSet;
import java.util.Set;

import com.paracamplus.ilp1.interfaces.IASTalternative;
import com.paracamplus.ilp2.interfaces.IASTassignment;
import com.paracamplus.ilp1.interfaces.IASTbinaryOperation;
import com.paracamplus.ilp1.interfaces.IASTblock;
import com.paracamplus.ilp1.interfaces.IASTblock.IASTbinding;
import com.paracamplus.ilp1.interfaces.IASTboolean;
import com.paracamplus.ilp4.interfaces.IASTclassDefinition;
import com.paracamplus.ilp3.interfaces.IASTcodefinitions;
import com.paracamplus.ilp1.interfaces.IASTexpression;
import com.paracamplus.ilp4.interfaces.IASTfieldRead;
import com.paracamplus.ilp4.interfaces.IASTfieldWrite;
import com.paracamplus.ilp1.interfaces.IASTfloat;
import com.paracamplus.ilp2.interfaces.IASTfunctionDefinition;
import com.paracamplus.ilp4.interfaces.IASTinstantiation;
import com.paracamplus.ilp1.interfaces.IASTinteger;
import com.paracamplus.ilp1.interfaces.IASTinvocation;
import com.paracamplus.ilp3.interfaces.IASTlambda;
import com.paracamplus.ilp2.interfaces.IASTloop;
import com.paracamplus.ilp4.interfaces.IASTmethodDefinition;
import com.paracamplus.ilp3.interfaces.IASTnamedLambda;
import com.paracamplus.ilp4.interfaces.IASTprogram;
import com.paracamplus.ilp4.interfaces.IASTself;
import com.paracamplus.ilp4.interfaces.IASTsend;
import com.paracamplus.ilp1.interfaces.IASTsequence;
import com.paracamplus.ilp1.interfaces.IASTstring;
import com.paracamplus.ilp4.interfaces.IASTsuper;
import com.paracamplus.ilp3.interfaces.IASTtry;
import com.paracamplus.ilp1.interfaces.IASTunaryOperation;
import com.paracamplus.ilp1.interfaces.IASTvariable;
import com.paracamplus.ilp1.interpreter.EmptyLexicalEnvironment;
import com.paracamplus.ilp1.interpreter.interfaces.EvaluationException;
import com.paracamplus.ilp1.interpreter.interfaces.ILexicalEnvironment;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTdefined;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTexists;
import com.paracamplus.ilp4.ilp4tme10.interfaces.IASTvisitor;

/*
 * Visiteur traversant l'AST pour collecter les variables globales.
 * 
 * Ce visiteur fonctionne sur l'AST et non l'ASTC normalisé.
 * Il peut donc être facilement être utilisé avant l'interpète.
 * Une alternative serait de réutiliser le GlboalVariableCollector
 * du compilateur, mais ce dernier foncitonne sur les ASTC.
 */

public class GlobalVariableCollector implements
		IASTvisitor<Void, ILexicalEnvironment, EvaluationException> {

	/*
	 *  Variable globales collectées
	 *  
	 *  Note : on représente les variables par leurs nom (String)
	 *  On évite de comparer des IASTvariable : la même variable peut
	 *  être représentée par plusieurs objets différents.
	*/
	protected Set<String> globals;
	
	public GlobalVariableCollector() {
		globals= new HashSet<>();		
	}
	
	public Set<String> getGlobals() {
		return globals;
	}
	
	
	/*
	 * Point d'entrée.
	 * Le résultat peut être récupéré par getGlobals.
	 */
	public GlobalVariableCollector visit(IASTprogram iast) 
			throws EvaluationException {
		ILexicalEnvironment env = new EmptyLexicalEnvironment();
		// Classes
		for (IASTclassDefinition c : iast.getClassDefinitions()) {
			ILexicalEnvironment methodenv = env;
			for (IASTmethodDefinition m : c.getProperMethodDefinitions()) {
				for (IASTvariable v : m.getVariables()) {
					methodenv.extend(v, null);
				}
				m.getBody().accept(this,  methodenv);
			}
		}
		// Fonctions globales
		for (IASTfunctionDefinition f : iast.getFunctionDefinitions()) {
			globals.add(f.getFunctionVariable().getName());
		}
		for (IASTfunctionDefinition f : iast.getFunctionDefinitions()) {
			ILexicalEnvironment funenv = env;
			for (IASTvariable v : f.getVariables()) {
				funenv.extend(v, null);
			}
			f.getBody().accept(this, funenv);
		}
		// Corps
		iast.getBody().accept(this,  env);
		return this;
	}
	    

	
	
	@Override
	public Void visit(IASTalternative iast, ILexicalEnvironment locals)
			throws EvaluationException {
		iast.getCondition().accept(this, locals);
		iast.getConsequence().accept(this, locals);
		if (iast.getAlternant()!=null) {
			iast.getAlternant().accept(this, locals);
		}
        return null;
	}

	@Override
	public Void visit(IASTassignment iast, ILexicalEnvironment locals)
			throws EvaluationException {
		IASTvariable var = iast.getVariable();
		if (!locals.isPresent(var)) {
			globals.add(var.getName());
		}
		iast.getExpression().accept(this,  locals);
		return null;
	}

	@Override
	public Void visit(IASTbinaryOperation iast,
			ILexicalEnvironment locals) throws EvaluationException {
		iast.getLeftOperand().accept(this, locals);
		iast.getRightOperand().accept(this, locals);
		return null;
	}

	@Override
	public Void visit(IASTblock iast, ILexicalEnvironment locals)
			throws EvaluationException {
		// construit l'environnement lexical enrichi
		ILexicalEnvironment newenv = locals;
		for (IASTbinding b : iast.getBindings()) {
			// la valeur de la variable n'est pas utile, on met donc null
			newenv = newenv.extend(b.getVariable(), null);
		}
		// visite le corps dans l'environnement enrichi
		iast.getBody().accept(this, newenv);
		return null;
	}

	@Override
	public Void visit(IASTboolean iast, ILexicalEnvironment locals)
			throws EvaluationException {
		return null;
	}

	@Override
	public Void visit(IASTcodefinitions iast,
			ILexicalEnvironment locals) throws EvaluationException {
		// construit l'environnement lexical enrichi
		ILexicalEnvironment newenv = locals;
		for (IASTnamedLambda l : iast.getFunctions()) {
			newenv = newenv.extend(l.getFunctionVariable(), null);
		}
		for (IASTnamedLambda l : iast.getFunctions()) {
			ILexicalEnvironment funenv = newenv;
			// construit l'environnement lexical de la fonction
			for (IASTvariable v : l.getVariables()) {
				funenv = funenv.extend(v, null);
			}
			// visite le coprs de la fonction dans le nouvel environnement 
			l.getBody().accept(this,  funenv);
		}
		// visite le corps dans l'environnement enrich
		iast.getBody().accept(this, newenv);
		return null;
	}

	@Override
	public Void visit(IASTfloat iast, ILexicalEnvironment locals)
			throws EvaluationException {
		return null;
	}

	@Override
	public Void visit(IASTinstantiation iast,
			ILexicalEnvironment locals) throws EvaluationException {
		for (IASTexpression e : iast.getArguments()) {
			e.accept(this,  locals);
		}
		return null;
	}

	@Override
	public Void visit(IASTinteger iast, ILexicalEnvironment locals)
			throws EvaluationException {
		return null;
	}

	@Override
	public Void visit(IASTinvocation iast, ILexicalEnvironment locals)
			throws EvaluationException {
		iast.getFunction().accept(this, locals);
		for (IASTexpression i : iast.getArguments()) {
			i.accept(this,  locals);
		}
		return null;
	}

	@Override
	public Void visit(IASTlambda iast, ILexicalEnvironment locals)
			throws EvaluationException {
		ILexicalEnvironment newenv = locals;
		// Construit l'environement lexical enrichi
		for (IASTvariable v : iast.getVariables()) {
			newenv = newenv.extend(v, null);
		}
		// Visite le corps dans l'environement enrich
		iast.getBody().accept(this, newenv);
		return null;
	}

	@Override
	public Void visit(IASTloop iast, ILexicalEnvironment locals)
			throws EvaluationException {
		iast.getCondition().accept(this, locals);
		iast.getBody().accept(this, locals);
		return null;
	}

	@Override
	public Void visit(IASTfieldRead iast, ILexicalEnvironment locals)
			throws EvaluationException {
		iast.getTarget().accept(this, locals);
		return null;
	}

	@Override
	public Void visit(IASTself iast, ILexicalEnvironment locals)
			throws EvaluationException {
		return null;
	}

	@Override
	public Void visit(IASTsend iast, ILexicalEnvironment locals)
			throws EvaluationException {
		iast.getReceiver().accept(this,  locals);
		for (IASTexpression e : iast.getArguments()) {
			e.accept(this, locals);
		}
		return null;
	}

	@Override
	public Void visit(IASTsequence iast, ILexicalEnvironment locals)
			throws EvaluationException {
		for (IASTexpression e : iast.getExpressions()) {
			e.accept(this, locals);
		}
		return null;
	}

	@Override
	public Void visit(IASTstring iast, ILexicalEnvironment locals)
			throws EvaluationException {
		return null;
	}

	@Override
	public Void visit(IASTsuper iast, ILexicalEnvironment locals)
			throws EvaluationException {
		return null;
	}

	@Override
	public Void visit(IASTtry iast, ILexicalEnvironment locals)
			throws EvaluationException {
		iast.getBody().accept(this, locals);
		if (iast.getCatcher()!=null)  {
			iast.getCatcher().accept(this, locals);
		}
		if (iast.getFinallyer()!=null) {
			iast.getFinallyer().accept(this,locals);
		}
		return null;
	}

	@Override
	public Void visit(IASTunaryOperation iast,
			ILexicalEnvironment locals) throws EvaluationException {
		iast.getOperand().accept(this,  locals);
		return null;
	}

	@Override
	public Void visit(IASTvariable iast, ILexicalEnvironment locals)
			throws EvaluationException {
		// si la variable est absente de l'environnement lexical, elle est globale
		if (!locals.isPresent(iast)) {
			globals.add(iast.getName());
		}
		return null;
	}

	@Override
	public Void visit(IASTfieldWrite iast, ILexicalEnvironment locals)
			throws EvaluationException {
		iast.getTarget().accept(this,  locals);
		iast.getValue().accept(this, locals);
		return null;
	}

	@Override
	public Void visit(IASTexists iast, ILexicalEnvironment locals)
			throws EvaluationException {
		return null;
	}

	@Override
	public Void visit(IASTdefined iast, ILexicalEnvironment data) throws EvaluationException {
		return null; 	
	}

	

}