package no.ssb.vtl.script.visitors.join;

import me.yanaga.guava.stream.MoreCollectors;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.FoldOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;

import java.util.Set;

import static com.google.common.base.Preconditions.*;

public class JoinFoldClauseVisitor extends VTLDatasetExpressionVisitor<FoldOperation> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    public JoinFoldClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }

    @Override
    public FoldOperation visitJoinFoldExpression(VTLParser.JoinFoldExpressionContext ctx) {
        // TODO: Migrate to component type.
        String dimension = ctx.dimension.getText();
        String measure = ctx.measure.getText();

        Set<Component> elements = ctx.componentRef().stream()
                .map(referenceVisitor::visitComponentRef)
                .map(o -> (Component) o)
                .collect(MoreCollectors.toImmutableSet());

        return new FoldOperation(dataset, dimension, measure, elements);
    }
}
