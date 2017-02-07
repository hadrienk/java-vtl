package no.ssb.vtl.script.visitors.join;

import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.RenameOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A visitor that instantiate rename operators.
 */
public class JoinRenameClauseVisitor extends VTLBaseVisitor<RenameOperation> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    @Deprecated
    public JoinRenameClauseVisitor(Dataset dataset) {
        this.dataset = checkNotNull(dataset); this.referenceVisitor = null;
    }

    public JoinRenameClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }

    @Override
    public RenameOperation visitJoinRenameExpression(VTLParser.JoinRenameExpressionContext ctx) {
        ImmutableMap.Builder<Component, String> newNames = ImmutableMap.builder();
        for (VTLParser.JoinRenameParameterContext renameParam : ctx.joinRenameParameter()) {
            Component component = (Component) referenceVisitor.visit(renameParam.componentRef());
            String to = renameParam.to.getText();
            newNames.put(component, to);
        }
        return new RenameOperation(dataset, newNames.build());
    }
}
