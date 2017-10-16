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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.CrossJoinOperation;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import no.ssb.vtl.script.operations.join.OuterJoinOperation;
import no.ssb.vtl.script.visitors.ReferenceVisitor;
import no.ssb.vtl.script.visitors.VTLDatasetExpressionVisitor;
import org.antlr.v4.runtime.Token;

import javax.script.Bindings;
import javax.script.ScriptContext;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.*;

/**
 * Visitor that handle the join definition
 */
public class JoinDefinitionVisitor extends VTLDatasetExpressionVisitor<AbstractJoinOperation> {

    @Deprecated
    private final ReferenceVisitor referenceVisitor;

    public JoinDefinitionVisitor(ScriptContext context) {
        checkNotNull(context);
        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        referenceVisitor = new ReferenceVisitor(bindings);
    }

    private Dataset getDataset(VTLParser.DatasetRefContext ref) {
        Object referencedObject = referenceVisitor.visit(ref);
        return (Dataset) referencedObject; //TODO: Is this always safe? Hadrien: Yes, DatasetRefContext and ComponentRefContext will return the correct type
    }

    private ImmutableMap<String, Dataset> extractDatasets(List<VTLParser.DatasetRefContext> ctx) {
        return ctx.stream()
                    .collect(ImmutableMap.toImmutableMap(
                            // TODO: Need to support alias here. The spec forgot it.
                            datasetRefContext -> datasetRefContext.variable().getText(),
                            datasetRefContext -> (Dataset) referenceVisitor.visit(datasetRefContext)
                    ));
    }

    private ImmutableSet<Component> extractIdentifierComponents(List<VTLParser.ComponentRefContext> ctx) {
        return ctx.stream()
                    .map(componentRefContext -> (Component) referenceVisitor.visit(componentRefContext))
                    .collect(ImmutableSet.toImmutableSet());
    }

    @Override
    public AbstractJoinOperation visitJoinDefinition(VTLParser.JoinDefinitionContext ctx) {

        ImmutableMap<String, Dataset> datasets = extractDatasets(ctx.datasetRef());
        ImmutableSet<Component> identifiers = extractIdentifierComponents(ctx.componentRef());

        Integer joinType = Optional.ofNullable(ctx.type).map(Token::getType).orElse(VTLParser.INNER);
        switch (joinType) {
            case VTLParser.INNER:
                return new InnerJoinOperation(datasets, identifiers);
            case VTLParser.OUTER:
                return new OuterJoinOperation(datasets, identifiers);
            case VTLParser.CROSS:
                return new CrossJoinOperation(datasets, identifiers);

        }
        return super.visitJoinDefinition(ctx);
    }
}
