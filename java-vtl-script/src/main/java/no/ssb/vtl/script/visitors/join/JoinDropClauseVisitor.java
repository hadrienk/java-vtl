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
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.DropOperation;
import no.ssb.vtl.script.operations.join.WorkingDataset;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;

import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

/**
 * Visit the drop clauses.
 */
public class JoinDropClauseVisitor extends VTLDatasetExpressionVisitor<DropOperation> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    @Deprecated
    public JoinDropClauseVisitor(WorkingDataset dataset) {
        this.dataset = checkNotNull(dataset, "dataset was null");
        this.referenceVisitor = null;
    }

    public JoinDropClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }

    @Override
    public DropOperation visitJoinDropExpression(VTLParser.JoinDropExpressionContext ctx) {
        Set<Component> components = ctx.componentRef().stream()
                .map(referenceVisitor::visit)
                .map(o -> (Component) o) //TODO: Safe?
                .collect(Collectors.toSet());
        return new DropOperation(dataset, components);
    }
}
