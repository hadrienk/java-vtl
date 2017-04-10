package no.ssb.vtl.script.visitors.join;


import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.UnfoldOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;
import no.ssb.vtl.script.visitors.VisitorUtil;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Set;

import static com.google.common.base.Preconditions.*;

public class JoinUnfoldClauseVisitor extends VTLDatasetExpressionVisitor<UnfoldOperation> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    public JoinUnfoldClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = referenceVisitor;
    }

    @Override
    public UnfoldOperation visitJoinUnfoldExpression(VTLParser.JoinUnfoldExpressionContext ctx) {
        Component dimension = (Component) referenceVisitor.visit(ctx.dimension);
        Component measure = (Component) referenceVisitor.visit(ctx.measure);

        Set<String> elements = Sets.newLinkedHashSet();
        for (TerminalNode element : ctx.STRING_CONSTANT()) {
            elements.add(VisitorUtil.stripQuotes(element));
        }
        return new UnfoldOperation(dataset, dimension, measure, elements);
    }
}
