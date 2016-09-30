package kohl.hadrien.vtl.script.connector;

import static com.google.common.base.Preconditions.checkNotNull;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import kohl.hadrien.Dataset;
import kohl.hadrien.VTLBaseVisitor;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.AssignmentVisitor;

/**
 * Handles the clauses.
 */
public class ClausesVisitor extends VTLBaseVisitor<Function<Dataset, Dataset>> {

  final AssignmentVisitor assignmentVisitor;

  public ClausesVisitor(AssignmentVisitor assignmentVisitor) {
    this.assignmentVisitor = checkNotNull(assignmentVisitor);
  }

  @Override
  protected Function<Dataset, Dataset> defaultResult() {
    return Function.identity();
  }

  @Override
  protected Function<Dataset, Dataset> aggregateResult(Function<Dataset, Dataset> aggregate,
                                                       Function<Dataset, Dataset> nextResult) {
    return aggregate.andThen(nextResult);
  }

  @Override
  public Function<Dataset, Dataset> visitRename(@NotNull VTLParser.RenameContext ctx) {
    List<VTLParser.RenameParamContext> parameters = ctx.renameParam();
    for (VTLParser.RenameParamContext parameter : parameters) {
      String from = parameter.from.getText();
      String to = parameter.to.getText();
      Optional<String> role = Optional.ofNullable(parameter.role())
          .map(VTLParser.RoleContext::getText);
    }

    return dataset -> {
      System.err.println(dataset);
      return dataset;
    };
  }
}
