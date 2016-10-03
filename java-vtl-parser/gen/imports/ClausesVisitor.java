// Generated from /Users/hadrien/Projects/java-vtl/java-vtl-parser/src/main/antlr4/imports/Clauses.g4 by ANTLR 4.5.3
package imports;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ClausesParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ClausesVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ClausesParser#clause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClause(ClausesParser.ClauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRename(ClausesParser.RenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#renameParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRenameParam(ClausesParser.RenameParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#role}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRole(ClausesParser.RoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#filter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(ClausesParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#keep}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeep(ClausesParser.KeepContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#calc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCalc(ClausesParser.CalcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#attrcalc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttrcalc(ClausesParser.AttrcalcContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#aggregate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAggregate(ClausesParser.AggregateContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(ClausesParser.BooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link ClausesParser#varID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarID(ClausesParser.VarIDContext ctx);
}