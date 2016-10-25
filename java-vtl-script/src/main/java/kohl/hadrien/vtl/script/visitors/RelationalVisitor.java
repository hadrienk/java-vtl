package kohl.hadrien.vtl.script.visitors;

import com.google.common.collect.Lists;
import kohl.hadrien.Dataset;
import kohl.hadrien.VTLBaseVisitor;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.operations.UnionOperation;

import java.util.List;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A visitor that handles the relational operators.
 */
public class RelationalVisitor extends VTLBaseVisitor<Supplier<Dataset>> {

    final AssignmentVisitor assignmentVisitor;

    public RelationalVisitor(AssignmentVisitor assignmentVisitor) {
        this.assignmentVisitor = checkNotNull(assignmentVisitor);
    }

    @Override
    public Supplier<Dataset> visitUnionExpression(VTLParser.UnionExpressionContext ctx) {
        List<Dataset> datasets = Lists.newArrayList();
        for (VTLParser.DatasetExpressionContext datasetExpressionContext : ctx.datasetExpression()) {
            Dataset dataset = assignmentVisitor.visit(datasetExpressionContext);
            datasets.add(dataset);
        }
        return new UnionOperation(datasets);
    }
}
