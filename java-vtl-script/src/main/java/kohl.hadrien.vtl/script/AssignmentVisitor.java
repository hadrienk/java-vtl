package kohl.hadrien.vtl.script;

import static com.google.common.base.Preconditions.checkNotNull;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.function.Function;
import javax.script.ScriptContext;
import kohl.hadrien.Dataset;
import kohl.hadrien.VTLBaseVisitor;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.connector.Connector;

/**
 * Assignement visitor.
 */
public class AssignmentVisitor extends VTLBaseVisitor<Dataset> {

  private final ScriptContext context;
  private final ConnectorVisitor connectorVisitor;
  private final ClausesVisitor clausesVisitor;

  public AssignmentVisitor(ScriptContext context, List<Connector> connectors) {
    this.context = checkNotNull(context, "the context was null");
    connectorVisitor = new ConnectorVisitor(connectors);
    clausesVisitor = new ClausesVisitor(this);
  }

  @Override
  public Dataset visitStatement(@NotNull VTLParser.StatementContext ctx) {
    String name = ctx.variableRef().getText();
    Dataset dataset = visit(ctx.datasetExpression());
    context.setAttribute(name, dataset, ScriptContext.ENGINE_SCOPE);
    return (Dataset) context.getAttribute(name);
  }

  @Override
  public Dataset visitVariableRef(@NotNull VTLParser.VariableRefContext ctx) {
    return (Dataset) context.getAttribute(ctx.getText());
  }

  @Override
  public Dataset visitGetExpression(@NotNull VTLParser.GetExpressionContext ctx) {
    return connectorVisitor.visit(ctx);
  }

  @Override
  public Dataset visitPutExpression(@NotNull VTLParser.PutExpressionContext ctx) {
    return connectorVisitor.visit(ctx);
  }

  @Override
  public Dataset visitWithClause(@NotNull VTLParser.WithClauseContext ctx) {
    Dataset dataset = visit(ctx.datasetExpression());
    Function<Dataset, Dataset> clause = clausesVisitor.visit(ctx.clauseExpression());
    return clause.apply(dataset);
  }

}
