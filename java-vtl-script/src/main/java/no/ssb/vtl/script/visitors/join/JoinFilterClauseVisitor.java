package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.FilterOperator;
import no.ssb.vtl.script.visitors.BooleanExpressionVisitor;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import java.util.function.Predicate;

import static com.google.common.base.Preconditions.*;

public class JoinFilterClauseVisitor extends VTLBaseVisitor<FilterOperator> {
    
    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;
    
    public JoinFilterClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset, "dataset was null");
        this.referenceVisitor = referenceVisitor;
    }

    @Override
    public FilterOperator visitJoinFilterClause(VTLParser.JoinFilterClauseContext ctx) {
        BooleanExpressionVisitor booleanExpressionVisitor = new BooleanExpressionVisitor(referenceVisitor);
        Predicate<Dataset.DataPoint> predicate = booleanExpressionVisitor.visit(ctx.joinFilterExpression().booleanExpression());
        return new FilterOperator(dataset, predicate);
    }
}
