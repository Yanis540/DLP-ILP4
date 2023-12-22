package com.paracamplus.ilp4.partiel2016.parser.ilpml;

import com.paracamplus.ilp1.parser.ParseException;
import com.paracamplus.ilp4.interfaces.IASTprogram;
import com.paracamplus.ilp4.partiel2016.interfaces.IASTfactory;

import antlr4.ILPMLgrammarPartiel2016Lexer;
import antlr4.ILPMLgrammarPartiel2016Parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class ILPMLParser 
extends com.paracamplus.ilp4.parser.ilpml.ILPMLParser {
	
	public ILPMLParser(IASTfactory factory) {
		super(factory);
	}
		
	@Override
    public IASTprogram getProgram() throws ParseException {
		try {
			ANTLRInputStream in = new ANTLRInputStream(input.getText());
			// flux de caractères -> analyseur lexical
			ILPMLgrammarPartiel2016Lexer lexer = new ILPMLgrammarPartiel2016Lexer(in);
			// analyseur lexical -> flux de tokens
			CommonTokenStream tokens =	new CommonTokenStream(lexer);
			// flux tokens -> analyseur syntaxique
			ILPMLgrammarPartiel2016Parser parser = new ILPMLgrammarPartiel2016Parser(tokens);
			// démarage de l'analyse syntaxique
			ILPMLgrammarPartiel2016Parser.ProgContext tree = parser.prog();		
			// parcours de l'arbre syntaxique et appels du Listener
			ParseTreeWalker walker = new ParseTreeWalker();
			ILPMLListener extractor = new ILPMLListener((IASTfactory)factory);
			walker.walk(extractor, tree);	
			return tree.node;
		} catch (Exception e) {
			throw new ParseException(e);
		}
    }

}
