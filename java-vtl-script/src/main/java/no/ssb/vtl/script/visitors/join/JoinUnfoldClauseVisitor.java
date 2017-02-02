package no.ssb.vtl.script.visitors.join;


import com.google.common.collect.Sets;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.UnfoldClause;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class JoinUnfoldClauseVisitor extends VTLBaseVisitor<UnfoldClause> {

    private final Dataset dataset;

    public JoinUnfoldClauseVisitor(Dataset dataset) {
        this.dataset = checkNotNull(dataset);
    }

    @Override
    public UnfoldClause visitJoinUnfoldExpression(VTLParser.JoinUnfoldExpressionContext ctx) {
        // TODO: Migrate to component references.
        String dimension = "";
        if (ctx.dimension.datasetRef() != null) {
            dimension += ctx.dimension.datasetRef().getText() + ".";
        }
        dimension += ctx.dimension.componentID().getText();

        // TODO: Migrate to component references.
        String measure = "";
        if (ctx.measure.datasetRef() != null) {
            measure += ctx.measure.datasetRef().getText() + ".";
        }
        measure += ctx.measure.componentID().getText();

        Set<String> elements = Sets.newLinkedHashSet();
        for (TerminalNode element : ctx.elements.STRING_CONSTANT()) {
            String constant = element.getText();
            elements.add(constant.substring(1, constant.length()-1));
        }
        return new UnfoldClause(dataset, dimension, measure, elements);
    }
}
