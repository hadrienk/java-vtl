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

import static com.google.common.base.Preconditions.*;

public class HierarchyVisitor extends VTLBaseVisitor<Dataset> {

    private final ReferenceVisitor referenceVisitor;

    public HierarchyVisitor(ReferenceVisitor referenceVisitor) {
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }

    @Override
    public Dataset visitHierarchyExpression(VTLParser.HierarchyExpressionContext ctx) {

        // Safe.
        Dataset dataset = (Dataset) referenceVisitor.visit(ctx.datasetRef());
        Dataset hierarchyDataset = (Dataset) referenceVisitor.visit(ctx.hierarchyReference().datasetRef());
        Component component = (Component) referenceVisitor.visitComponentRef(ctx.componentRef());

        return new HierarchyOperation(dataset, hierarchyDataset, component);

    }
}
