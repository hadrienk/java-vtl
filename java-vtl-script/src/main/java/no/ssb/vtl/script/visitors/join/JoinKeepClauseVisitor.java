package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.KeepOperator;
import no.ssb.vtl.script.operations.join.WorkingDataset;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

/**
 * Visit the keep clauses.
 */
public class JoinKeepClauseVisitor  extends VTLBaseVisitor<KeepOperator> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    public JoinKeepClauseVisitor(WorkingDataset dataset) {
        this.dataset = checkNotNull(dataset);
        referenceVisitor = new JoinReferenceVisitor(dataset);
    }

    @Override
    public KeepOperator visitJoinKeepExpression(VTLParser.JoinKeepExpressionContext ctx) {
        Set<Component> components = ctx.componentRef().stream()
                .map(referenceVisitor::visit)
                .map(o -> (Component) o) //TODO: Safe?
                .collect(Collectors.toSet());
        return new KeepOperator(dataset, components);
    }
}
