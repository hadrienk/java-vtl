package no.ssb.vtl.script.visitors.join;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.CalcOperation;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.visitors.ComponentRoleVisitor;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import javax.script.Bindings;
import javax.script.ScriptContext;
import java.util.Collections;
import java.util.Deque;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.MoreObjects.firstNonNull;
import static java.util.Optional.ofNullable;

public class JoinExpressionVisitor extends VTLBaseVisitor<Dataset> {

    private final JoinDefinitionVisitor joinDefVisitor;
    private ReferenceVisitor referenceVisitor;
    private Dataset workingDataset;
    private Bindings joinScope;
    private static final ComponentRoleVisitor ROLE_VISITOR = ComponentRoleVisitor.getInstance();

    public JoinExpressionVisitor(ScriptContext context) {
        joinDefVisitor = new JoinDefinitionVisitor(context);
    }

    @Override
    public Dataset visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        AbstractJoinOperation joinOperation = joinDefVisitor.visit(ctx.joinDefinition());
        joinScope = joinOperation.getJoinScope();

        workingDataset = joinOperation;
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
    }
    
    @Override
    protected Dataset aggregateResult(Dataset aggregate, Dataset nextResult) {
        // Compute the new scope.
        Dataset currentDataset = firstNonNull(nextResult, aggregate);
        
        Set<String> previous = ofNullable(aggregate)
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

        // Create the expression.
        JoinCalcClauseVisitor visitor = new JoinCalcClauseVisitor(referenceVisitor, workingDataset.getDataStructure());
        VTLExpression componentExpression = visitor.visit(ctx);

        Optional<Component.Role> componentRole = ofNullable(ROLE_VISITOR.visitComponentRole(ctx.role));
        Boolean implicit = ctx.implicit != null;

        // Calculate name
        String componentName = ctx.variable().getText();

        return new CalcOperation(
                workingDataset,
                componentExpression,
                componentName,
                componentRole.orElse(Component.Role.MEASURE),
                implicit
        );
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

}
