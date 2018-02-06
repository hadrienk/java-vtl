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

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.hierarchy.HierarchyOperation;
import no.ssb.vtl.script.operations.join.ComponentBindings;

import static com.google.common.base.Preconditions.*;

public class HierarchyVisitor extends VTLBaseVisitor<Dataset> {

    private final DatasetExpressionVisitor datasetExpressionVisitor;

    public HierarchyVisitor(DatasetExpressionVisitor datasetExpressionVisitor) {
        this.datasetExpressionVisitor = checkNotNull(datasetExpressionVisitor);
    }

    @Override
    public Dataset visitHierarchyExpression(VTLParser.HierarchyExpressionContext ctx) {

        // Safe.
        Dataset dataset = datasetExpressionVisitor.visit(ctx.variable());

        // Create local binding
        ComponentBindings componentBindings = new ComponentBindings(dataset);
        componentBindings.put(ctx.variable().getText(), new ComponentBindings(dataset));

        ComponentVisitor componentVisitor = new ComponentVisitor(componentBindings);
        Component component = componentVisitor.visit(ctx.variableExpression());

        Dataset hierarchyDataset = datasetExpressionVisitor.visit(ctx.hierarchyReference());

        return new HierarchyOperation(dataset, hierarchyDataset, component);

    }
}
