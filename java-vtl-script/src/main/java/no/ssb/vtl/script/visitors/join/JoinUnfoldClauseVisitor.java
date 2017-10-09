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


import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.UnfoldOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;
import no.ssb.vtl.script.visitors.VisitorUtil;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.Set;

import static com.google.common.base.Preconditions.*;

public class JoinUnfoldClauseVisitor extends VTLDatasetExpressionVisitor<UnfoldOperation> {

    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;

    public JoinUnfoldClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset);
        this.referenceVisitor = referenceVisitor;
    }

    @Override
    public UnfoldOperation visitJoinUnfoldExpression(VTLParser.JoinUnfoldExpressionContext ctx) {
        Component dimension = (Component) referenceVisitor.visit(ctx.dimension);
        Component measure = (Component) referenceVisitor.visit(ctx.measure);

        Set<String> elements = Sets.newLinkedHashSet();
        for (TerminalNode element : ctx.STRING_CONSTANT()) {
            elements.add(VisitorUtil.stripQuotes(element));
        }
        return new UnfoldOperation(dataset, dimension, measure, elements);
    }
}
