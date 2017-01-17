package kohl.hadrien.vtl.script.visitors.join;

import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.FilterOperator;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class JoinFilterClauseVisitor extends VTLBaseVisitor<FilterOperator> {
    
    private final Dataset dataset;
    
    public JoinFilterClauseVisitor(Dataset dataset) {
        this.dataset = checkNotNull(dataset, "dataset was null");
    }
    
    @Override
    public FilterOperator visitJoinFilterClause(VTLParser.JoinFilterClauseContext ctx) {
        return new FilterOperator(dataset, Stream.of("id1").collect(Collectors.toSet())); //TODO: Do not hardcode component to filter on
    }
}
