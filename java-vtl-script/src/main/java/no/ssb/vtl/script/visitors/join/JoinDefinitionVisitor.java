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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.ComponentBindings;
import no.ssb.vtl.script.operations.join.CrossJoinOperation;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import no.ssb.vtl.script.operations.join.OuterJoinOperation;
import no.ssb.vtl.script.visitors.ComponentVisitor;
import no.ssb.vtl.script.visitors.DatasetExpressionVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;
import org.antlr.v4.runtime.Token;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Visitor that handle the join definition.
 * <p>
 * TODO: Need to support alias here. The spec forgot it.
 * TODO: Support for escaped identifiers.
 */
public class JoinDefinitionVisitor extends VTLDatasetExpressionVisitor<AbstractJoinOperation> {

    private DatasetExpressionVisitor datasetExpressionVisitor;

    public JoinDefinitionVisitor(DatasetExpressionVisitor datasetExpressionVisitor) {
        this.datasetExpressionVisitor = checkNotNull(datasetExpressionVisitor);
    }

    private ImmutableMap<String, Dataset> extractDatasets(List<VTLParser.VariableContext> variables) {
        ImmutableMap.Builder<String, Dataset> builder = ImmutableMap.builder();
        for (VTLParser.VariableContext variable : variables) {
            // TODO: Escaped identifiers.
            String name = variable.getText();
            Dataset dataset = datasetExpressionVisitor.visit(variable);
            builder.put(name, dataset);
        }
        return builder.build();
    }

    private ImmutableSet<Component> extractIdentifierComponents(List<VTLParser.VariableExpressionContext> identifiers,
                                                                ImmutableMap<String, Dataset> datasets) {
        ImmutableSet.Builder<Component> builder = ImmutableSet.builder();
        ComponentVisitor componentVisitor = new ComponentVisitor(new ComponentBindings(datasets));
        for (VTLParser.VariableExpressionContext identifier : identifiers) {
            Component identifierComponent = componentVisitor.visit(identifier);
            builder.add(identifierComponent);
        }
        return builder.build();
    }

    @Override
    public AbstractJoinOperation visitJoinDefinition(VTLParser.JoinDefinitionContext ctx) {

        // Create a component bindings to be able to resolve components.
        ImmutableMap<String, Dataset> datasets = extractDatasets(ctx.variable());

        ImmutableSet<Component> identifiers = extractIdentifierComponents(ctx.variableExpression(), datasets);

        Integer joinType = Optional.ofNullable(ctx.type).map(Token::getType).orElse(VTLParser.INNER);
        switch (joinType) {
            case VTLParser.INNER:
                return new InnerJoinOperation(datasets, identifiers);
            case VTLParser.OUTER:
                return new OuterJoinOperation(datasets, identifiers);
            case VTLParser.CROSS:
                return new CrossJoinOperation(datasets, identifiers);

        }
        return super.visitJoinDefinition(ctx);
    }
}
