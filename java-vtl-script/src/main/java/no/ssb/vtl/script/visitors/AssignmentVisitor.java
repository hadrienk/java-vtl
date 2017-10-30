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

import no.ssb.vtl.connectors.Connector;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.VTLDataset;

import javax.script.Bindings;
import javax.script.ScriptContext;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Assignment visitor.
 */
public class AssignmentVisitor extends VTLBaseVisitor<Object> {

    private final Bindings bindings;
    private final ExpressionVisitor expressionVisitor;

    private final ConnectorVisitor connectorVisitor;
    private final ClauseVisitor clausesVisitor;
    private final DatasetExpressionVisitor datasetExpressionVisitor;
    private final CheckVisitor checkVisitor;
    private final HierarchyVisitor hierarchyVisitor;
    private final AggregationVisitor aggregationVisitor;
    
    public AssignmentVisitor(ScriptContext context, List<Connector> connectors) {
        checkNotNull(context, "the context was null");

        this.bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        this.expressionVisitor = new ExpressionVisitor(this.bindings);

        connectorVisitor = new ConnectorVisitor(connectors);
        clausesVisitor = new ClauseVisitor();
        datasetExpressionVisitor = new DatasetExpressionVisitor(expressionVisitor);
        checkVisitor = new CheckVisitor(datasetExpressionVisitor);
        hierarchyVisitor = new HierarchyVisitor(datasetExpressionVisitor);
        aggregationVisitor = new AggregationVisitor(datasetExpressionVisitor);
    }


    
    @Override
    public Object visitAssignment(VTLParser.AssignmentContext ctx) {
        String name = ctx.variable().getText();
        Object value;
        if (ctx.datasetExpression() != null) {
            value = visit(ctx.datasetExpression());
        } else {
            VTLExpression expression = expressionVisitor.visit(ctx.expression());
            value = expression.resolve(bindings);
        }
        bindings.put(name, value);
        return value;
    }

    @Override
    public Object visitRelationalExpression(VTLParser.RelationalExpressionContext ctx) {
        return datasetExpressionVisitor.visit(ctx);
    }

    @Override
    public Object visitGetFunction(VTLParser.GetFunctionContext ctx) {
        return connectorVisitor.visit(ctx);
    }

    @Override
    public Object visitPutFunction(VTLParser.PutFunctionContext ctx) {
        return connectorVisitor.visit(ctx);
    }

    @Override
    public Object visitWithClause(VTLParser.WithClauseContext ctx) {
        Dataset dataset = (Dataset) visit(ctx.datasetExpression());
        Function<Dataset, Dataset> clause = clausesVisitor.visit(ctx.clauseExpression());
        return clause.apply(dataset);
    }

    @Override
    public Object visitVariable(VTLParser.VariableContext ctx) {
        VTLObject resolved = expressionVisitor.visit(ctx).resolve(bindings);
        return ((VTLDataset) resolved).get();
    }

    @Override
    public Object visitWithCheck(VTLParser.WithCheckContext ctx) {
        return checkVisitor.visit(ctx.checkFunction());
    }

    @Override
    public Object visitWithHierarchy(VTLParser.WithHierarchyContext ctx) {
        return hierarchyVisitor.visit(ctx.hierarchyExpression());
    }
    
    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @param ctx
     */
    @Override
    public Object visitWithAggregation(VTLParser.WithAggregationContext ctx) {
        return aggregationVisitor.visit(ctx.aggregationFunction());
    }
}
