package kohl.hadrien.vtl.script.visitors.join;

import com.google.common.collect.ImmutableMap;
import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.RenameOperation;
import kohl.hadrien.vtl.script.operations.join.WorkingDataset;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A visitor that instantiate rename operators.
 */
public class JoinRenameClauseVisitor extends VTLBaseVisitor<RenameOperation> {

    private final Dataset dataset;

    public JoinRenameClauseVisitor(WorkingDataset dataset) {
        this.dataset = checkNotNull(dataset);
    }

    @Override
    public RenameOperation visitJoinRenameExpression(VTLParser.JoinRenameExpressionContext ctx) {
        DataStructure dataStructure = dataset.getDataStructure();
        ImmutableMap.Builder<String, String> newNames = ImmutableMap.builder();
        ImmutableMap.Builder<String, Component.Role> newRoles = ImmutableMap.builder();
        for (VTLParser.JoinRenameParameterContext renameParam : ctx.joinRenameParameter()) {
            String from = renameParam.from.getText();
            String to = renameParam.to.getText();
            newNames.put(from, to);
            newRoles.put(from, dataStructure.get(from).getRole());
        }
        return new RenameOperation(dataset, newNames.build(), newRoles.build());
    }
}
