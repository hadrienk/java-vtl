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

import me.yanaga.guava.stream.MoreCollectors;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.FoldOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;

import java.util.Set;

import static com.google.common.base.Preconditions.*;

public class JoinFoldClauseVisitor extends VTLDatasetExpressionVisitor<FoldOperation> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    public JoinFoldClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }

    @Override
    public FoldOperation visitJoinFoldExpression(VTLParser.JoinFoldExpressionContext ctx) {
        // TODO: Migrate to component type.
        String dimension = ctx.dimension.getText();
        String measure = ctx.measure.getText();

        Set<Component> elements = ctx.componentRef().stream()
                .map(referenceVisitor::visitComponentRef)
                .map(o -> (Component) o)
                .collect(MoreCollectors.toImmutableSet());

        return new FoldOperation(dataset, dimension, measure, elements);
    }
}
