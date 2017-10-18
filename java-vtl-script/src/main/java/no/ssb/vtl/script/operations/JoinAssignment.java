package no.ssb.vtl.script.operations;

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

import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.operations.join.ComponentBindings;
import no.ssb.vtl.script.operations.join.DataPointBindings;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class JoinAssignment extends AbstractUnaryDatasetOperation {

    private final VTLExpression2 expression;
    private final Component.Role role;
    private final Boolean implicit;
    private final String identifier;
    private final ComponentBindings componentBindings;

    public JoinAssignment(Dataset dataset, VTLExpression2 expression, String identifier, Component.Role role,
                          Boolean implicit) {
        super(checkNotNull(dataset));
        this.expression = checkNotNull(expression);
        this.role = checkNotNull(role);
        this.implicit = checkNotNull(implicit);
        this.identifier = checkNotNull(identifier);
        this.componentBindings = null;
    }

    public JoinAssignment(Dataset dataset, VTLExpression2 expression, String identifier, Component.Role role,
                          Boolean implicit, ComponentBindings componentBindings) {
        super(checkNotNull(dataset));
        this.expression = checkNotNull(expression);
        this.role = checkNotNull(role);
        this.implicit = checkNotNull(implicit);
        this.identifier = checkNotNull(identifier);
        this.componentBindings = checkNotNull(componentBindings);
    }

    @Override
    protected DataStructure computeDataStructure() {
        DataStructure.Builder builder = DataStructure.builder();
        DataStructure dataStructure = getChild().getDataStructure();
        for (Map.Entry<String, Component> entry : dataStructure.entrySet()) {
            if (entry.getKey().equals(identifier))
                continue;
            builder.put(entry.getKey(), entry.getValue());
        }

        Class<?> type = expression.getVTLType();

        if (dataStructure.containsKey(identifier)) {
            Component existingComponent = dataStructure.get(identifier);

            // Overriding identifier is not permitted.
            checkArgument(existingComponent.getRole() != Component.Role.IDENTIFIER,
                    "an identifier %s already exists in %s", identifier, getChild()
            );

            // Implicit fails if a component with the same name but a different role
            // already exists.
            if (implicit) {
                checkArgument(role.equals(existingComponent.getRole()),
                        "the role of the component %s must be %s",
                        identifier, existingComponent.getRole()
                );
            }
        }

        DataStructure newDataStructure = builder.put(identifier, role, type).build();
        componentBindings.put(identifier, newDataStructure.get(identifier));
        return newDataStructure;
    }

    @Override
    public Stream<DataPoint> getData() {
        DataPointBindings dataPointBindings = new DataPointBindings(componentBindings, getDataStructure());
        Stream<DataPoint> data = getChild().getData();

        // TODO: Allow putting new values in the DataPointBindings.
        if (!getChild().getDataStructure().containsKey(identifier))
            data = data.peek(dataPoint -> dataPoint.add(VTLObject.NULL));

        return data.map(dataPointBindings::setDataPoint)
                .peek(bindings -> {
                    VTLObject resolved = expression.resolve(dataPointBindings);
                    bindings.put(identifier, resolved);
                })
                .map(DataPointBindings::getDataPoint);
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return getChild().getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return getChild().getSize();
    }
}
