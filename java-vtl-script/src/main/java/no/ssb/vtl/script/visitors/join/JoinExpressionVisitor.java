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

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.JoinAssignment;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.ComponentBindings;
import no.ssb.vtl.script.visitors.ComponentRoleVisitor;
import no.ssb.vtl.script.visitors.ExpressionVisitor;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.RelationalVisitor;

import javax.script.ScriptContext;
import java.util.Optional;

import static com.google.common.base.MoreObjects.firstNonNull;
import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Optional.ofNullable;

// TODO: Rename to JoinBodyVisitor.
public class JoinExpressionVisitor extends VTLBaseVisitor<Dataset> {

    private RelationalVisitor relationalVisitor;

    private final JoinDefinitionVisitor joinDefVisitor;

    @Deprecated
    private ReferenceVisitor referenceVisitor;

    private ExpressionVisitor expressionVisitor;
    private Dataset workingDataset;
    private ComponentBindings joinScope;
    private static final ComponentRoleVisitor ROLE_VISITOR = ComponentRoleVisitor.getInstance();
    private ComponentVisitor componentVisitor;

    @Deprecated
    public JoinExpressionVisitor(ScriptContext context) {
        joinDefVisitor = new JoinDefinitionVisitor(context);
    }

    public JoinExpressionVisitor(RelationalVisitor relationalVisitor) {
        this.relationalVisitor = checkNotNull(relationalVisitor);
        joinDefVisitor = new JoinDefinitionVisitor(relationalVisitor); // TODO
    }

    @Override
    public Dataset visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        // Get the abstract join operation (inner, outer, cross).

        AbstractJoinOperation joinOperation = joinDefVisitor.visit(ctx.joinDefinition());

        // Holds the component references.
        joinScope = joinOperation.getJoinScope();
        workingDataset = joinOperation;
        expressionVisitor = new ExpressionVisitor(joinScope);
        componentVisitor = new ComponentVisitor(joinScope);

        Dataset finalDataset = visit(ctx.joinBody());

//        // TODO: Put somewhere else.
//        IdentityHashMap<Component, String> identityMap = Maps.newIdentityHashMap();
//        Deque<Map.Entry<String, ?>> stack = Queues.newArrayDeque();
//        stack.addAll(joinScope.entrySet());
//        int lastSize = -1;
//        while (!stack.isEmpty()) {
//            Map.Entry<String, ?> entry = stack.pop();
//            Object value = entry.getValue();
//            if (value instanceof Dataset) {
//                if (lastSize == identityMap.size()) {
//                    Dataset dataset = (Dataset) value;
//                    stack.addAll(dataset.getDataStructure().entrySet());
//                } else {
//                    lastSize = identityMap.size();
//                    stack.addLast(entry);
//                }
//            } else if (value instanceof Component) {
//                identityMap.put((Component) entry.getValue(), entry.getKey());
//            }
//        }

        return finalDataset;
    }
    
    @Override
    protected Dataset aggregateResult(Dataset aggregate, Dataset nextResult) {
        Dataset currentDataset = firstNonNull(nextResult, aggregate);
        // Update the component scope.
        joinScope.putAll(new ComponentBindings(currentDataset));
        return workingDataset = currentDataset;
    }

    @Override
    public Dataset visitJoinCalcClause(VTLParser.JoinCalcClauseContext ctx) {
        // The expression visitor operates on a scope that contains the component reference.
        VTLParser.JoinAssignmentContext joinAssignment = ctx.joinAssignment();

        Optional<Component.Role> componentRole = ofNullable(ROLE_VISITOR.visitComponentRole(joinAssignment.role));
        Boolean implicit = joinAssignment.implicit != null;

        VTLExpression2 expression = expressionVisitor.visit(joinAssignment.expression());

        // Calculate name
        String componentName = joinAssignment.variable().getText();


        JoinAssignment result = new JoinAssignment(
                workingDataset,
                expression,
                componentName,
                componentRole.orElse(Component.Role.MEASURE),
                implicit,
                joinScope // TODO: Rename to component bindings.
        );
        joinScope.putAll(new ComponentBindings(result));
        return result;
    }

    @Override
    public Dataset visitJoinFoldClause(VTLParser.JoinFoldClauseContext ctx) {
        JoinFoldClauseVisitor visitor = new JoinFoldClauseVisitor(workingDataset, componentVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinUnfoldClause(VTLParser.JoinUnfoldClauseContext ctx) {
        JoinUnfoldClauseVisitor visitor = new JoinUnfoldClauseVisitor(workingDataset, componentVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinKeepClause(VTLParser.JoinKeepClauseContext ctx) {
        JoinKeepClauseVisitor visitor = new JoinKeepClauseVisitor(workingDataset, componentVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinDropClause(VTLParser.JoinDropClauseContext ctx) {
        JoinDropClauseVisitor visitor = new JoinDropClauseVisitor(workingDataset, componentVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinFilterClause(VTLParser.JoinFilterClauseContext ctx) {
        JoinFilterClauseVisitor visitor = new JoinFilterClauseVisitor(workingDataset, joinScope, expressionVisitor);
        return visitor.visit(ctx);
    }

    @Override
    public Dataset visitJoinRenameClause(VTLParser.JoinRenameClauseContext ctx) {
        JoinRenameClauseVisitor visitor = new JoinRenameClauseVisitor(workingDataset, componentVisitor);
        return visitor.visit(ctx);
    }

}
