// Generated from d:/Yanis/FAC/M1/S1/DLP/TP/ilp4/ANTLRGrammars/ILPMLgrammar4.g4 by ANTLR 4.13.1

    package antlr4;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ILPMLgrammar4Parser}.
 */
public interface ILPMLgrammar4Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ILPMLgrammar4Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(ILPMLgrammar4Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPMLgrammar4Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(ILPMLgrammar4Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalFunctionDefinition}
	 * labeled alternative in {@link ILPMLgrammar4Parser#globalDef}.
	 * @param ctx the parse tree
	 */
	void enterGlobalFunctionDefinition(ILPMLgrammar4Parser.GlobalFunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalFunctionDefinition}
	 * labeled alternative in {@link ILPMLgrammar4Parser#globalDef}.
	 * @param ctx the parse tree
	 */
	void exitGlobalFunctionDefinition(ILPMLgrammar4Parser.GlobalFunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ClassDefinition}
	 * labeled alternative in {@link ILPMLgrammar4Parser#globalDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDefinition(ILPMLgrammar4Parser.ClassDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ClassDefinition}
	 * labeled alternative in {@link ILPMLgrammar4Parser#globalDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDefinition(ILPMLgrammar4Parser.ClassDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPMLgrammar4Parser#classDef}.
	 * @param ctx the parse tree
	 */
	void enterClassDef(ILPMLgrammar4Parser.ClassDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPMLgrammar4Parser#classDef}.
	 * @param ctx the parse tree
	 */
	void exitClassDef(ILPMLgrammar4Parser.ClassDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPMLgrammar4Parser#methodDef}.
	 * @param ctx the parse tree
	 */
	void enterMethodDef(ILPMLgrammar4Parser.MethodDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPMLgrammar4Parser#methodDef}.
	 * @param ctx the parse tree
	 */
	void exitMethodDef(ILPMLgrammar4Parser.MethodDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPMLgrammar4Parser#globalFunDef}.
	 * @param ctx the parse tree
	 */
	void enterGlobalFunDef(ILPMLgrammar4Parser.GlobalFunDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPMLgrammar4Parser#globalFunDef}.
	 * @param ctx the parse tree
	 */
	void exitGlobalFunDef(ILPMLgrammar4Parser.GlobalFunDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPMLgrammar4Parser#localFunDef}.
	 * @param ctx the parse tree
	 */
	void enterLocalFunDef(ILPMLgrammar4Parser.LocalFunDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPMLgrammar4Parser#localFunDef}.
	 * @param ctx the parse tree
	 */
	void exitLocalFunDef(ILPMLgrammar4Parser.LocalFunDefContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Binding}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinding(ILPMLgrammar4Parser.BindingContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Binding}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinding(ILPMLgrammar4Parser.BindingContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Loop}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLoop(ILPMLgrammar4Parser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Loop}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLoop(ILPMLgrammar4Parser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by the {@code New}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNew(ILPMLgrammar4Parser.NewContext ctx);
	/**
	 * Exit a parse tree produced by the {@code New}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNew(ILPMLgrammar4Parser.NewContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariable(ILPMLgrammar4Parser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariable(ILPMLgrammar4Parser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Alternative}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAlternative(ILPMLgrammar4Parser.AlternativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Alternative}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAlternative(ILPMLgrammar4Parser.AlternativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Invocation}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInvocation(ILPMLgrammar4Parser.InvocationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Invocation}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInvocation(ILPMLgrammar4Parser.InvocationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstFloat}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstFloat(ILPMLgrammar4Parser.ConstFloatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstFloat}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstFloat(ILPMLgrammar4Parser.ConstFloatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Sequence}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSequence(ILPMLgrammar4Parser.SequenceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Sequence}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSequence(ILPMLgrammar4Parser.SequenceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Self}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSelf(ILPMLgrammar4Parser.SelfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Self}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSelf(ILPMLgrammar4Parser.SelfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VariableAssign}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariableAssign(ILPMLgrammar4Parser.VariableAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VariableAssign}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariableAssign(ILPMLgrammar4Parser.VariableAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ReadField}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterReadField(ILPMLgrammar4Parser.ReadFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ReadField}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitReadField(ILPMLgrammar4Parser.ReadFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstFalse}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstFalse(ILPMLgrammar4Parser.ConstFalseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstFalse}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstFalse(ILPMLgrammar4Parser.ConstFalseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterUnary(ILPMLgrammar4Parser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Unary}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitUnary(ILPMLgrammar4Parser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Send}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSend(ILPMLgrammar4Parser.SendContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Send}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSend(ILPMLgrammar4Parser.SendContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstTrue}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstTrue(ILPMLgrammar4Parser.ConstTrueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstTrue}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstTrue(ILPMLgrammar4Parser.ConstTrueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstInteger}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstInteger(ILPMLgrammar4Parser.ConstIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstInteger}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstInteger(ILPMLgrammar4Parser.ConstIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Super}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSuper(ILPMLgrammar4Parser.SuperContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Super}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSuper(ILPMLgrammar4Parser.SuperContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WriteField}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWriteField(ILPMLgrammar4Parser.WriteFieldContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WriteField}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWriteField(ILPMLgrammar4Parser.WriteFieldContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ConstString}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterConstString(ILPMLgrammar4Parser.ConstStringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ConstString}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitConstString(ILPMLgrammar4Parser.ConstStringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Try}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterTry(ILPMLgrammar4Parser.TryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Try}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitTry(ILPMLgrammar4Parser.TryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Codefinitions}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterCodefinitions(ILPMLgrammar4Parser.CodefinitionsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Codefinitions}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitCodefinitions(ILPMLgrammar4Parser.CodefinitionsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Binary}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBinary(ILPMLgrammar4Parser.BinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Binary}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBinary(ILPMLgrammar4Parser.BinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Lambda}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLambda(ILPMLgrammar4Parser.LambdaContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Lambda}
	 * labeled alternative in {@link ILPMLgrammar4Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLambda(ILPMLgrammar4Parser.LambdaContext ctx);
}