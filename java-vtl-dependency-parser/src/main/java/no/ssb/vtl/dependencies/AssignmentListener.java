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
import no.ssb.vtl.parser.VTLBaseListener;
import no.ssb.vtl.parser.VTLParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AssignmentListener extends VTLBaseListener {
    
    private Map<String, Assignment> variableDependency;
    
    private Set<ComponentRef> componentRefs = new HashSet<>();
    
    public AssignmentListener() {
        variableDependency = new HashMap<>();
    }
    
    @Override
    public void exitJoinCalcClause(VTLParser.JoinCalcClauseContext ctx) {
        String identifier = ctx.identifier().getText();
        String expression = ctx.getText();
        Assignment assignment =
                new Assignment(identifier, expression,ImmutableSet.copyOf(componentRefs));
        variableDependency.put(identifier, assignment);
        componentRefs.clear();
    }
    
    @Override
    public void exitComponentRef(VTLParser.ComponentRefContext ctx) {
    
        String datasetRef = ctx.datasetRef().getText();
        String variableRef = ctx.variableRef().getText();
        componentRefs.add(new ComponentRef(datasetRef, variableRef));
    }
    
    public Map<String, Assignment> getVariableDependency() {
        return variableDependency;
    }
    
}

