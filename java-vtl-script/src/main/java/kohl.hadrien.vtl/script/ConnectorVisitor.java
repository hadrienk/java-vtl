package kohl.hadrien.vtl.script;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Throwables;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import kohl.hadrien.Dataset;
import kohl.hadrien.VTLBaseVisitor;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.connector.Connector;
import kohl.hadrien.vtl.script.connector.ConnectorException;

/**
 * A visitor that handles get and puts VTL operators.
 *
 * It uses the list of dataset connectors and returns the first result.
 */
public class ConnectorVisitor extends VTLBaseVisitor<Dataset> {

  final List<Connector> connectors;

  public ConnectorVisitor(List<Connector> connectors) {
    this.connectors = checkNotNull(connectors, "list of connectors was null");
  }

  @Override
  public Dataset visitGetExpression(@NotNull VTLParser.GetExpressionContext ctx) {
    // TODO: Get the identifier.
    String identifier = "identifier";
    try {
      for (Connector connector : connectors) {
        if (!connector.canHandle(identifier)) {
          continue;
        }
        return connector.getDataset(ctx.getText());
      }
    } catch (ConnectorException ce) {
      Throwables.propagate(ce);
    }
    return null;
  }

  @Override
  public Dataset visitPutExpression(@NotNull VTLParser.PutExpressionContext ctx) {
    // TODO: Get the identifier and the dataset.
    String identifier = "identifier";
    Dataset dataset = null;
    try {

      for (Connector connector : connectors) {
        if (!connector.canHandle(identifier)) {
          continue;
        }
        return connector.putDataset(identifier, dataset);
      }
      return super.visitPutExpression(ctx);
    } catch (ConnectorException ce) {
      Throwables.propagate(ce);
    }
    return null;
  }
}
