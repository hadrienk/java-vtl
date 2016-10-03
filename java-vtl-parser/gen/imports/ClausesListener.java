// Generated from /Users/hadrien/Projects/java-vtl/java-vtl-parser/src/main/antlr4/imports/Clauses.g4 by ANTLR 4.5.3
package imports;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ClausesParser}.
 */
public interface ClausesListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ClausesParser#clause}.
	 * @param ctx the parse tree
	 */
	void enterClause(ClausesParser.ClauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#clause}.
	 * @param ctx the parse tree
	 */
	void exitClause(ClausesParser.ClauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#rename}.
	 * @param ctx the parse tree
	 */
	void enterRename(ClausesParser.RenameContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#rename}.
	 * @param ctx the parse tree
	 */
	void exitRename(ClausesParser.RenameContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#renameParam}.
	 * @param ctx the parse tree
	 */
	void enterRenameParam(ClausesParser.RenameParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#renameParam}.
	 * @param ctx the parse tree
	 */
	void exitRenameParam(ClausesParser.RenameParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#role}.
	 * @param ctx the parse tree
	 */
	void enterRole(ClausesParser.RoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#role}.
	 * @param ctx the parse tree
	 */
	void exitRole(ClausesParser.RoleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#filter}.
	 * @param ctx the parse tree
	 */
	void enterFilter(ClausesParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#filter}.
	 * @param ctx the parse tree
	 */
	void exitFilter(ClausesParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#keep}.
	 * @param ctx the parse tree
	 */
	void enterKeep(ClausesParser.KeepContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#keep}.
	 * @param ctx the parse tree
	 */
	void exitKeep(ClausesParser.KeepContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#calc}.
	 * @param ctx the parse tree
	 */
	void enterCalc(ClausesParser.CalcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#calc}.
	 * @param ctx the parse tree
	 */
	void exitCalc(ClausesParser.CalcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#attrcalc}.
	 * @param ctx the parse tree
	 */
	void enterAttrcalc(ClausesParser.AttrcalcContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#attrcalc}.
	 * @param ctx the parse tree
	 */
	void exitAttrcalc(ClausesParser.AttrcalcContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void enterAggregate(ClausesParser.AggregateContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#aggregate}.
	 * @param ctx the parse tree
	 */
	void exitAggregate(ClausesParser.AggregateContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void enterBooleanExpression(ClausesParser.BooleanExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#booleanExpression}.
	 * @param ctx the parse tree
	 */
	void exitBooleanExpression(ClausesParser.BooleanExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link ClausesParser#varID}.
	 * @param ctx the parse tree
	 */
	void enterVarID(ClausesParser.VarIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link ClausesParser#varID}.
	 * @param ctx the parse tree
	 */
	void exitVarID(ClausesParser.VarIDContext ctx);
}