package kohl.hadrien.vtl.script.visitors.join;

import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.parser.VTLBaseVisitor;
import kohl.hadrien.vtl.parser.VTLParser;
import kohl.hadrien.vtl.script.operations.FilterOperator;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class JoinFilterClauseVisitor extends VTLBaseVisitor<FilterOperator> {
    
    private final Dataset dataset;
    
    JoinFilterClauseVisitor(Dataset dataset) {
        this.dataset = checkNotNull(dataset, "dataset was null");
    }
    
    @Override
    public FilterOperator visitJoinFilterClause(VTLParser.JoinFilterClauseContext ctx) {
        Set<String> components = Stream.of("id1").collect(Collectors.toSet());
        Predicate<Dataset.Tuple> predicate = tuple -> tuple.ids().stream()
                .filter(dataPoint -> components.contains(dataPoint.getComponent().getName()))
                .anyMatch(dataPoint -> dataPoint.get().equals("1"));  //TODO do not hardcode filter criteria
        FilterOperator filterOperator = new FilterOperator(dataset, predicate);
        System.out.println("Created new filterOperator: " + filterOperator);
        return filterOperator; //TODO: Do not hardcode component to filter on
    }
}
