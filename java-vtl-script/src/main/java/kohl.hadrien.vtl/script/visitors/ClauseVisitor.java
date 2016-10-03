package kohl.hadrien.vtl.script.visitors;

import static java.util.Optional.ofNullable;

import com.google.common.collect.ImmutableMap;

import org.antlr.v4.runtime.misc.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import kohl.hadrien.Attribute;
import kohl.hadrien.Component;
import kohl.hadrien.Dataset;
import kohl.hadrien.Identifier;
import kohl.hadrien.Measure;
import kohl.hadrien.VTLBaseVisitor;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.RenameOperation;

/**
 * A visitor that handles the clauses.
 */
public class ClauseVisitor extends VTLBaseVisitor<Function<Dataset, Dataset>> {

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

    ImmutableMap.Builder<String, String> names = ImmutableMap.builder();
    ImmutableMap.Builder<String, Class<? extends Component>> roles = ImmutableMap.builder();

    for (VTLParser.RenameParamContext parameter : parameters) {
      String from = parameter.from.getText();
      String to = parameter.to.getText();
      names.put(from, to);

      Optional<String> role = ofNullable(parameter.role()).map(VTLParser.RoleContext::getText);
      if (role.isPresent()) {
        Class<? extends Component> roleClass;
        switch (role.get()) {
          case "IDENTIFIER":
            roleClass = Identifier.class;
            break;
          case "MEASURE":
            roleClass = Measure.class;
            break;
          case "ATTRIBUTE":
            roleClass = Attribute.class;
            break;
          default:
            throw new RuntimeException("unknown component type " + role.get());
        }
        roles.put(from, roleClass);
      }
    }

    return new RenameOperation(names.build(), roles.build());
  }
}
