package no.ssb.vtl.script.visitors.join;

import me.yanaga.guava.stream.MoreCollectors;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.FoldClause;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class JoinFoldClauseVisitor extends VTLBaseVisitor<FoldClause> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    public JoinFoldClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }

    @Override
    public FoldClause visitJoinFoldExpression(VTLParser.JoinFoldExpressionContext ctx) {
        // TODO: Migrate to component type.
        String dimension = ctx.dimension.getText();
        String measure = ctx.measure.getText();

        Set<Component> elements = ctx.componentRef().stream()
                .map(referenceVisitor::visitComponentRef)
                .map(o -> (Component) o)
                .collect(MoreCollectors.toImmutableSet());

        return new FoldClause(dataset, dimension, measure, elements);
    }
}
