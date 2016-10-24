package kohl.hadrien.vtl.script.visitors;

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

import kohl.hadrien.Dataset;
import kohl.hadrien.VTLBaseVisitor;
import kohl.hadrien.VTLParser;
import kohl.hadrien.vtl.script.connector.Connector;
import org.antlr.v4.runtime.misc.NotNull;

import javax.script.ScriptContext;
import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Assignement visitor.
 */
public class AssignmentVisitor extends VTLBaseVisitor<Dataset> {

    private final ScriptContext context;
    private final ConnectorVisitor connectorVisitor;
    private final ClauseVisitor clausesVisitor;

    public AssignmentVisitor(ScriptContext context, List<Connector> connectors) {
        this.context = checkNotNull(context, "the context was null");
        connectorVisitor = new ConnectorVisitor(connectors);
        clausesVisitor = new ClauseVisitor();
    }

    @Override
    protected Dataset aggregateResult(Dataset aggregate, Dataset nextResult) {
        return nextResult != null ? nextResult : aggregate;
    }

    @Override
    public Dataset visitStart(VTLParser.StartContext ctx) {
        for (VTLParser.StatementContext statement : ctx.statement()) {

        }
        return super.visitStart(ctx);
    }

    @Override
    public Dataset visitStatement(@NotNull VTLParser.StatementContext ctx) {
        String name = ctx.variableRef().getText();
        Dataset dataset = visit(ctx.datasetExpression());
        context.setAttribute(name, dataset, ScriptContext.ENGINE_SCOPE);
        return (Dataset) context.getAttribute(name, ScriptContext.ENGINE_SCOPE);
    }

    @Override
    public Dataset visitVariableRef(@NotNull VTLParser.VariableRefContext ctx) {
        return (Dataset) context.getAttribute(ctx.getText());
    }

    @Override
    public Dataset visitGetExpression(@NotNull VTLParser.GetExpressionContext ctx) {
        return connectorVisitor.visit(ctx);
    }

    @Override
    public Dataset visitPutExpression(@NotNull VTLParser.PutExpressionContext ctx) {
        return connectorVisitor.visit(ctx);
    }

    @Override
    public Dataset visitWithClause(@NotNull VTLParser.WithClauseContext ctx) {
        Dataset dataset = visit(ctx.datasetExpression());
        Function<Dataset, Dataset> clause = clausesVisitor.visit(ctx.clauseExpression());
        return clause.apply(dataset);
    }

}
