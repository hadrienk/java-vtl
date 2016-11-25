package kohl.hadrien.vtl.script.visitors;

import com.google.common.collect.Lists;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.UnionOperation;

import javax.script.ScriptContext;
import java.util.List;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A visitor that handles the relational operators.
 */
public class RelationalVisitor extends VTLBaseVisitor<Supplier<Dataset>> {

    final AssignmentVisitor assignmentVisitor;
    final JoinVisitor joinVisitor;

    public RelationalVisitor(AssignmentVisitor assignmentVisitor, ScriptContext context) {
        this.assignmentVisitor = checkNotNull(assignmentVisitor);
        this.joinVisitor = new JoinVisitor(context);
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

    @Override
    public Supplier<Dataset> visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        Dataset visit = joinVisitor.visit(ctx);
        return () -> visit;
    }

    @Override
    public Supplier<Dataset> visitRelationalExpression(VTLParser.RelationalExpressionContext ctx) {
        return super.visitRelationalExpression(ctx);
    }
}
