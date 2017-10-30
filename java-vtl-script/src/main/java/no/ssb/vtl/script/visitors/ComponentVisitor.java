package no.ssb.vtl.script.visitors;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import no.ssb.vtl.model.Component;
import no.ssb.vtl.script.operations.join.ComponentBindings;
import no.ssb.vtl.script.visitors.AbstractVariableVisitor;

import static no.ssb.vtl.script.operations.join.ComponentBindings.*;

/**
 * A variable visitor that expects component or component reference.
 */
public class ComponentVisitor extends AbstractVariableVisitor<Component> {

    public ComponentVisitor(ComponentBindings bindings) {
        super(bindings);
    }

    @Override
    protected Component convert(Object o) {

        if (o instanceof Component)
            return (Component) o;

        if (o instanceof ComponentReference)
            return ((ComponentReference)o).getComponent();

        throw new ClassCastException("could not cast " + o + "to Component");
    }

}
