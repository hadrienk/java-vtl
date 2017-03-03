package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.FilterOperation;
import no.ssb.vtl.script.visitors.BooleanExpressionVisitor;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;

import java.util.function.Predicate;

import static com.google.common.base.Preconditions.*;

public class JoinFilterClauseVisitor extends VTLDatasetExpressionVisitor<FilterOperation> {
    
    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;
    
    public JoinFilterClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset, "dataset was null");
        this.referenceVisitor = referenceVisitor;
    }

    @Override
    public FilterOperation visitJoinFilterClause(VTLParser.JoinFilterClauseContext ctx) {
        BooleanExpressionVisitor booleanExpressionVisitor = new BooleanExpressionVisitor(referenceVisitor,
                dataset.getDataStructure());
        Predicate<DataPoint> predicate = booleanExpressionVisitor.visit(ctx.joinFilterExpression().booleanExpression());
        return new FilterOperation(dataset, predicate);
    }
}
