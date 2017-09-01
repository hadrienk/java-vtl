package no.ssb.vtl.dependencies;

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

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import no.ssb.vtl.parser.VTLBaseListener;
import no.ssb.vtl.parser.VTLParser;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class AssignmentListener extends VTLBaseListener {
    
    private Map<String, Assignment> variableDependency;
    
    private Set<ComponentRef> componentRefs = new HashSet<>();
    private Set<String> joinDatasets = new HashSet<>();
    
    public AssignmentListener() {
        variableDependency = new HashMap<>();
    }
    
    
    
    private void createAssignment(String identifier, String expression, Set<ComponentRef> componentRefs) {
        Assignment assignment =
                new Assignment(identifier, expression, componentRefs);
        variableDependency.put(identifier, assignment);
    }
    
    @Override
    public void exitJoinDefinition(VTLParser.JoinDefinitionContext ctx) {
        joinDatasets = ctx.datasetRef().stream()
                .map(RuleContext::getText)
                .collect(Collectors.toSet());
    }
    
    @Override
    public void enterJoinCalcClause(VTLParser.JoinCalcClauseContext ctx) {
        componentRefs.clear();
    }
    
    @Override
    public void exitJoinCalcClause(VTLParser.JoinCalcClauseContext ctx) {
        String identifier = ctx.identifier().getText();
        String expression = ctx.getText();
        createAssignment(identifier, expression, ImmutableSet.copyOf(componentRefs));
    }
    
    @Override
    public void exitJoinRenameExpression(VTLParser.JoinRenameExpressionContext ctx) {
        for (VTLParser.JoinRenameParameterContext renameParameterContext : ctx.joinRenameParameter()) {
            String identifier = renameParameterContext.to.getText();
            String expression = ctx.getText();
            createAssignment(identifier, expression, createComponentRefs(renameParameterContext.from));
        }
    }
    
    @Override
    public void exitJoinUnfoldExpression(VTLParser.JoinUnfoldExpressionContext ctx) {
        for (TerminalNode element : ctx.STRING_CONSTANT()) {
            String identifier = element.getText();
            String expression = ctx.getText();
            createAssignment(identifier, expression, createComponentRefs(ctx.measure));
        }
    }
    
    @Override
    public void enterJoinFoldExpression(VTLParser.JoinFoldExpressionContext ctx) {
        componentRefs.clear();
    }
    
    @Override
    public void exitJoinFoldExpression(VTLParser.JoinFoldExpressionContext ctx) {
        String identifier = ctx.measure.getText();
        String expression = ctx.getText();
        createAssignment(identifier, expression, ImmutableSet.copyOf(componentRefs));
    }
    
    @Override
    public void exitComponentRef(VTLParser.ComponentRefContext ctx) {
        Set<ComponentRef> componentRef = createComponentRefs(ctx);
        componentRefs.addAll(componentRef);
    }
    
    private Set<ComponentRef> createComponentRefs(VTLParser.ComponentRefContext ctx) {
        String variableRef = ctx.variableRef().getText();
        if (ctx.datasetRef() != null) {
            String datasetRef = ctx.datasetRef().getText();
            return Sets.newHashSet(new ComponentRef(datasetRef, variableRef));
        } else if (variableDependency.containsKey(variableRef)) {
            return variableDependency.get(variableRef).getComponentRefs();
        } else {
            return joinDatasets.stream()
                    .map(datasetRef -> new ComponentRef(datasetRef, variableRef))
                    .collect(Collectors.toSet());
        }
    }
    
    public Map<String, Assignment> getVariableDependency() {
        return variableDependency;
    }
    
}

