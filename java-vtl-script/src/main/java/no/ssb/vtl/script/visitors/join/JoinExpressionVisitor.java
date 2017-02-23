package no.ssb.vtl.script.visitors.join;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import javax.script.Bindings;
import javax.script.ScriptContext;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.google.common.base.MoreObjects.firstNonNull;

public class JoinExpressionVisitor extends VTLBaseVisitor<Dataset> {

    private final JoinDefinitionVisitor joinDefVisitor;
    private ReferenceVisitor referenceVisitor;
    private Dataset workingDataset;
    private Bindings joinScope;

    public JoinExpressionVisitor(ScriptContext context) {
        joinDefVisitor = new JoinDefinitionVisitor(context);
    }

    @Override
    public Dataset visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        AbstractJoinOperation joinOperation = joinDefVisitor.visit(ctx.joinDefinition());
        joinScope = joinOperation.getJoinScope();

        workingDataset = joinOperation.workDataset();
        referenceVisitor = new ReferenceVisitor(joinScope);

        Dataset finalDataset = visit(ctx.joinBody());

        // TODO: Put somewhere else.
        IdentityHashMap<Component, String> identityMap = Maps.newIdentityHashMap();
        Deque<Map.Entry<String, ?>> stack = Queues.newArrayDeque();
        stack.addAll(joinScope.entrySet());
        int lastSize = -1;
        while (!stack.isEmpty()) {
            Map.Entry<String, ?> entry = stack.pop();
            Object value = entry.getValue();
            if (value instanceof Dataset) {
                if (lastSize == identityMap.size()) {
                    Dataset dataset = (Dataset) value;
                    stack.addAll(dataset.getDataStructure().entrySet());
                } else {
                    lastSize = identityMap.size();
                    stack.addLast(entry);
                }
            } else if (value instanceof Component) {
                identityMap.put((Component) entry.getValue(), entry.getKey());
            }
        }



        return finalDataset;
//        JoinBodyVisitor joinBodyVisitor = new JoinBodyVisitor(joinScope);
//        Function<WorkingDataset, WorkingDataset> joinClause = joinBodyVisitor.visitJoinBody(ctx.joinBody());
//        WorkingDataset workingDataset = joinOperation.workDataset();
//
//        return joinClause.apply(workingDataset);
    }
    
    @Override
    protected Dataset aggregateResult(Dataset aggregate, Dataset nextResult) {
        // Compute the new scope.
        Dataset currentDataset = firstNonNull(nextResult, aggregate);
        
        Set<String> previous = Optional.ofNullable(aggregate)
                .map(Dataset::getDataStructure)
                .map(ForwardingMap::keySet)
                .orElse(Collections.emptySet());
        Set<String> current = currentDataset.getDataStructure().keySet();
        
        Set<String> referencesToRemove = Sets.difference(previous, current);
        Set<String> referencesToAdd = Sets.difference(current, previous);
        
        for (String key : referencesToRemove) {
            joinScope.remove(key);
        }
        for (String key : referencesToAdd) {
            joinScope.put(key, currentDataset.getDataStructure().get(key));
        }
        
        return workingDataset = currentDataset;
    }

    @Override
    public Dataset visitJoinCalcClause(VTLParser.JoinCalcClauseContext ctx) {
        /*
            TODO: Handle explicit and implicit component computation.
            Need to parse the role
            If implicit, error if already defined.
         */
        String variableName = removeQuoteIfNeeded(ctx.identifier().getText());
        Component.Role role = Component.Role.MEASURE;
        Class<?> type = Number.class;

        DataStructure.Builder structureCopy = DataStructure.copyOf(workingDataset.getDataStructure());
        structureCopy.put(variableName, role, type);
        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(referenceVisitor, workingDataset.getDataStructure());
        Function<Dataset.DataPoint, Object> componentExpression = visitor.visit(ctx);

        // TODO: Extract to its own visitor implementing dataset.
        Dataset dataset = workingDataset;
        DataStructure dataStructure = structureCopy.build();
        return new Dataset() {
            @Override
            public DataStructure getDataStructure() {
                return dataStructure;
            }

            @Override
            public Stream<DataPoint> get() {
                return dataset.get().map(tuple -> {
                    tuple.add(dataStructure.wrap(variableName, componentExpression.apply(tuple)));
                    return tuple;
                });
            }

            @Override
            public String toString() {
                MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Calc");
                return helper.omitNullValues().toString();
            }
        };
    }

    @Override
    public Dataset visitJoinFoldClause(VTLParser.JoinFoldClauseContext ctx) {
        JoinFoldClauseVisitor visitor = new JoinFoldClauseVisitor(workingDataset, referenceVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinUnfoldClause(VTLParser.JoinUnfoldClauseContext ctx) {
        JoinUnfoldClauseVisitor visitor = new JoinUnfoldClauseVisitor(workingDataset, referenceVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinKeepClause(VTLParser.JoinKeepClauseContext ctx) {
        JoinKeepClauseVisitor visitor = new JoinKeepClauseVisitor(workingDataset, referenceVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinDropClause(VTLParser.JoinDropClauseContext ctx) {
        JoinDropClauseVisitor visitor = new JoinDropClauseVisitor(workingDataset, referenceVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinFilterClause(VTLParser.JoinFilterClauseContext ctx) {
        JoinFilterClauseVisitor visitor = new JoinFilterClauseVisitor(workingDataset, referenceVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinRenameClause(VTLParser.JoinRenameClauseContext ctx) {
        JoinRenameClauseVisitor visitor = new JoinRenameClauseVisitor(workingDataset, referenceVisitor);
        return visitor.visit(ctx);
    }

    private static String removeQuoteIfNeeded(String key) {
        if (!key.isEmpty() && key.length() > 3) {
            if (key.charAt(0) == '\'' && key.charAt(key.length() - 1) == '\'') {
                return key.substring(1, key.length() - 1);
            }
        }
        return key;
    }
}
