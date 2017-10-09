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

/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import no.ssb.vtl.connectors.Connector;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

import javax.script.ScriptContext;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.*;

/**
 * Assignment visitor.
 */
public class AssignmentVisitor extends VTLBaseVisitor<Dataset> {

    private final ScriptContext context;
    private final ConnectorVisitor connectorVisitor;
    private final ClauseVisitor clausesVisitor;
    private final RelationalVisitor relationalVisitor;
    private final CheckVisitor checkVisitor;
    private final HierarchyVisitor hierarchyVisitor;
    private final AggregationVisitor aggregationVisitor;
    
    public AssignmentVisitor(ScriptContext context, List<Connector> connectors) {
        this.context = checkNotNull(context, "the context was null");
        connectorVisitor = new ConnectorVisitor(connectors);
        clausesVisitor = new ClauseVisitor();
        relationalVisitor = new RelationalVisitor(this, context);
        ReferenceVisitor referenceVisitor = new ReferenceVisitor(context.getBindings(ScriptContext.ENGINE_SCOPE));
        checkVisitor = new CheckVisitor(relationalVisitor, referenceVisitor);
        hierarchyVisitor = new HierarchyVisitor(referenceVisitor);
        aggregationVisitor = new AggregationVisitor(referenceVisitor);
    }

    @Override
    protected Dataset aggregateResult(Dataset aggregate, Dataset nextResult) {
        return nextResult != null ? nextResult : aggregate;
    }
    
    @Override
    public Dataset visitAssignment(VTLParser.AssignmentContext ctx) {
        String name = ctx.variable().getText();
        Dataset dataset = visit(ctx.expression());
        context.setAttribute(name, dataset, ScriptContext.ENGINE_SCOPE);
        return (Dataset) context.getAttribute(name, ScriptContext.ENGINE_SCOPE);
    }

    @Override
    public Dataset visitRelationalExpression(VTLParser.RelationalExpressionContext ctx) {
        return relationalVisitor.visit(ctx);
    }

    @Override
    public Dataset visitVariable(VTLParser.VariableContext ctx) {
        return (Dataset) context.getAttribute(ctx.getText());
    }

    @Override
    public Dataset visitGetFunction(VTLParser.GetFunctionContext ctx) {
        return connectorVisitor.visit(ctx);
    }

    @Override
    public Dataset visitPutFunction(VTLParser.PutFunctionContext ctx) {
        return connectorVisitor.visit(ctx);
    }

    @Override
    public Dataset visitWithClause(VTLParser.WithClauseContext ctx) {
        Dataset dataset = visit(ctx.datasetExpression());
        Function<Dataset, Dataset> clause = clausesVisitor.visit(ctx.clauseExpression());
        return clause.apply(dataset);
    }

    @Override
    public Dataset visitWithCheck(VTLParser.WithCheckContext ctx) {
        return checkVisitor.visit(ctx.checkFunction());
    }

    @Override
    public Dataset visitWithHierarchy(VTLParser.WithHierarchyContext ctx) {
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
    public Dataset visitWithAggregation(VTLParser.WithAggregationContext ctx) {
        return aggregationVisitor.visit(ctx.aggregationFunction());
    }
}
