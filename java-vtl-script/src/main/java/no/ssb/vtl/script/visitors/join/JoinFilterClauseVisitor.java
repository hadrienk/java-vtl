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

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLPredicate;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.FilterOperation;
import no.ssb.vtl.script.visitors.BooleanExpressionVisitor;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;

import static com.google.common.base.Preconditions.*;

public class JoinFilterClauseVisitor extends VTLDatasetExpressionVisitor<FilterOperation> {
    
    private final Dataset dataset;
    private final ReferenceVisitor referenceVisitor;
    
    public JoinFilterClauseVisitor(Dataset dataset, ReferenceVisitor referenceVisitor) {
        this.dataset = checkNotNull(dataset, "dataset was null");
        this.referenceVisitor = referenceVisitor;
    }

    @Override
    public FilterOperation visitJoinFilterClause(VTLParser.JoinFilterClauseContext ctx) {
        BooleanExpressionVisitor booleanExpressionVisitor = new BooleanExpressionVisitor(referenceVisitor,
                dataset.getDataStructure());
        VTLPredicate predicate = booleanExpressionVisitor.visit(ctx.joinFilterExpression().booleanExpression());
        return new FilterOperation(dataset, predicate);
    }
}
