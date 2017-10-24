package no.ssb.vtl.script.visitors;

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

import com.google.common.annotations.VisibleForTesting;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.AggregationOperation;
import no.ssb.vtl.script.operations.join.ComponentBindings;
import org.antlr.v4.runtime.Token;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class AggregationVisitor extends VTLDatasetExpressionVisitor<AggregationOperation> {

    private final DatasetExpressionVisitor datasetExpressionVisitor;
    
    public AggregationVisitor(DatasetExpressionVisitor datasetExpressionVisitor) {
        this.datasetExpressionVisitor = checkNotNull(datasetExpressionVisitor);
    }

    @VisibleForTesting
    AggregationVisitor() {
        this.datasetExpressionVisitor = null;
    }
    
    @Override
    public AggregationOperation visitAggregateSum(VTLParser.AggregateSumContext ctx) {
        Dataset dataset;
        VTLParser.VariableExpressionContext variable = ctx.variableExpression();

        // Bypass membership.
        if (variable.membershipExpression() != null) {
            VTLParser.MembershipExpressionContext membership = variable.membershipExpression();
            dataset = datasetExpressionVisitor.visit(membership.left);
            ComponentVisitor componentVisitor = new ComponentVisitor(new ComponentBindings(dataset));
            Component aggregationComponent = componentVisitor.visit(membership.right);
            return getSumOperation(dataset, getGroupByComponents(ctx, dataset), Collections.singletonList(aggregationComponent));
        } else {
            dataset = datasetExpressionVisitor.visit(variable);
            return getSumOperation(dataset, getGroupByComponents(ctx, dataset));
        }
    }
    
    private Component getComponentFromDataset(Dataset dataset, VTLParser.VariableContext componentRef) {
        String text = componentRef.getText();
        DataStructure dataStructure = dataset.getDataStructure();
        return dataStructure.get(text);
    }
    
    private List<Component> getGroupByComponents(VTLParser.AggregateSumContext ctx, Dataset dataset) {
        List<Component> idComponents = ctx.aggregationParams().variableExpression().stream()
                .map(componentRef -> getComponentFromDataset(dataset, componentRef.variable()))
                .collect(Collectors.toList());
        
        Token clause = ctx.aggregationParams().aggregationClause;
        switch (clause.getType()){
            case VTLParser.GROUP_BY:
                return idComponents;
            case VTLParser.ALONG:
                return dataset.getDataStructure().values().stream()
                        .filter(Component::isIdentifier)
                        .filter(component -> !idComponents.contains(component))
                        .collect(Collectors.toList());
            default:
                throw new IllegalArgumentException("Unrecognized token: " + clause);
        }
    }

    @VisibleForTesting
    AggregationOperation getSumOperation(Dataset dataset, List<Component> groupBy) {
        List<Component> component = dataset.getDataStructure().values().stream()
                .filter(Component::isMeasure)
                .collect(Collectors.toList());
        return getSumOperation(dataset, groupBy, component);
    }

    @VisibleForTesting
    AggregationOperation getSumOperation(Dataset dataset, List<Component> groupBy, List<Component> aggregationComponents) {
        return new AggregationOperation(dataset, groupBy, aggregationComponents,
                vtlNumbers -> vtlNumbers.stream().reduce(VTLNumber::add).orElse(VTLObject.of((Double) null)));
    }
    
}
