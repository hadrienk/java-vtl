package no.ssb.vtl.script.visitors.join;


import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.UnfoldClause;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class JoinUnfoldClauseVisitor extends VTLBaseVisitor<UnfoldClause> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    public JoinUnfoldClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = referenceVisitor;
    }

    @Override
    public UnfoldClause visitJoinUnfoldExpression(VTLParser.JoinUnfoldExpressionContext ctx) {
        Component dimension = (Component) referenceVisitor.visit(ctx.dimension);
        Component measure = (Component) referenceVisitor.visit(ctx.measure);

        Set<String> elements = Sets.newLinkedHashSet();
        for (TerminalNode element : ctx.STRING_CONSTANT()) {
            String constant = element.getText();
            elements.add(constant.substring(1, constant.length() - 1));
        }
        return new UnfoldClause(dataset, dimension, measure, elements);
    }
}
