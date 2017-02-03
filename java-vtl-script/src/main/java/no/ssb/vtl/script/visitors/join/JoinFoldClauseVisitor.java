package no.ssb.vtl.script.visitors.join;

import com.google.common.collect.Sets;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.FoldClause;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public class JoinFoldClauseVisitor extends VTLBaseVisitor<FoldClause> {

    private final Dataset dataset;

    public JoinFoldClauseVisitor(Dataset dataset) {
        this.dataset = checkNotNull(dataset);
    }

    @Override
    public FoldClause visitJoinFoldExpression(VTLParser.JoinFoldExpressionContext ctx) {
        // TODO: Migrate to component type.
        String dimension = ctx.dimension.getText();
        String measure = ctx.measure.getText();

        Set<String> elements = Sets.newLinkedHashSet();
        for (TerminalNode element : ctx.elements.STRING_CONSTANT()) {
            String constant = element.getText();
            elements.add(constant.substring(1, constant.length()-1));
        }
        return new FoldClause(dataset, dimension, measure, elements);
    }
}
