package kohl.hadrien.vtl.script;

import static com.google.common.base.Preconditions.checkNotNull;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import javax.script.ScriptContext;
import kohl.hadrien.Dataset;
import kohl.hadrien.VTLBaseVisitor;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.connector.Connector;

/**
 * Created by hadrien on 30/09/2016.
 */
public class AssignmentVisitor extends VTLBaseVisitor<Dataset> {

  private final ScriptContext context;
  private final ConnectorVisitor connectorVisitor;

  public AssignmentVisitor(ScriptContext context, List<Connector> connectors) {
    this.context = checkNotNull(context, "the context was null");
    connectorVisitor = new ConnectorVisitor(connectors);
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
}
