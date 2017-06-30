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

import com.google.common.collect.Lists;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.UnionOperation;
import no.ssb.vtl.script.visitors.join.JoinExpressionVisitor;

import javax.script.ScriptContext;
import java.util.List;

import static com.google.common.base.Preconditions.*;

/**
 * A visitor that handles the relational operators.
 */
public class RelationalVisitor extends VTLBaseVisitor<Dataset> {

    private final AssignmentVisitor assignmentVisitor;
    private final JoinExpressionVisitor joinVisitor;

    public RelationalVisitor(AssignmentVisitor assignmentVisitor, ScriptContext context) {
        this.assignmentVisitor = checkNotNull(assignmentVisitor);
        this.joinVisitor = new JoinExpressionVisitor(context);
    }


    @Override
    public Dataset visitUnionExpression(VTLParser.UnionExpressionContext ctx) {
        List<Dataset> datasets = Lists.newArrayList();
        for (VTLParser.DatasetExpressionContext datasetExpressionContext : ctx.datasetExpression()) {
            Dataset dataset = assignmentVisitor.visit(datasetExpressionContext);
            datasets.add(dataset);
        }
        return new UnionOperation(datasets);
    }

    @Override
    public Dataset visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        return joinVisitor.visit(ctx);
    }

}
