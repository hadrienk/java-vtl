package kohl.hadrien.vtl.script.visitors.join;

import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.join.AbstractJoinOperation;
import kohl.hadrien.vtl.script.operations.join.JoinClause;
import kohl.hadrien.vtl.script.operations.join.WorkingDataset;
import org.antlr.v4.runtime.RuleContext;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Sets up the join clauses in the given {@link AbstractJoinOperation}.
 * <p>
 * The last join clause is returned.
 */
public class JoinBodyVisitor extends VTLBaseVisitor<JoinClause> {

    private final AbstractJoinOperation joinOperation;

    public JoinBodyVisitor(AbstractJoinOperation joinOperation) {
        this.joinOperation = checkNotNull(joinOperation);
    }

    @Override
    public JoinClause visitJoinCalcClause(VTLParser.JoinCalcClauseContext ctx) {
        String variableName = ctx.varID().getText();

        // TODO: Spec does not specify what is the default role.
        String variableRole = Optional.ofNullable(ctx.role()).map(RuleContext::getText).orElse("MEASURE");

        List<JoinClause> clauses = this.joinOperation.getClauses();

        //DataStructure dataStructure = joinOperation.getDataStructure();

        JoinCalcClauseVisitor joinCalcClauseVisitor = new JoinCalcClauseVisitor();
        Function<Dataset.Tuple, Object> clauseFunction = joinCalcClauseVisitor.visit(ctx);
        JoinClause calcClause = new JoinClause() {

            @Override
            public WorkingDataset apply(WorkingDataset workingDataset) {
                return new WorkingDataset() {
                    @Override
                    public DataStructure getDataStructure() {
                        DataStructure structure = workingDataset.getDataStructure();
                        structure.addComponent(variableName, Component.Role.MEASURE, Number.class);
                        return structure;
                    }

                    @Override
                    public Stream<Tuple> get() {
                        return workingDataset.get()
                                .map(tuple -> {
                                    tuple.add(getDataStructure().wrap(variableName, clauseFunction.apply(tuple)));
                                    return tuple;
                                });
                    }
                };
            }
        };

        clauses.add(calcClause);
        return calcClause;
    }
}
