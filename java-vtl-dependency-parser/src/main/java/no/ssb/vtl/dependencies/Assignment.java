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

import com.google.common.base.MoreObjects;

import java.util.Set;

public class Assignment {
    
    private final String identifier;
    private final String expression;
    private final Set<ComponentRef> componentRefs;
    
    public Assignment(String identifier, String expression, Set<ComponentRef> componentRefs) {
        this.identifier = identifier;
        this.expression = expression;
        this.componentRefs = componentRefs;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public String getExpression() {
        return expression;
    }
    
    public Set<ComponentRef> getComponentRefs() {
        return componentRefs;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier)
                .add("expression", expression)
                .add("componentRefs", componentRefs)
                .toString();
    }
}
