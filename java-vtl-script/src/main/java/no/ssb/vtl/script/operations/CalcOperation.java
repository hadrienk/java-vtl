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
import no.ssb.vtl.model.VTLExpression;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO: Rename to reflect scalar assignment
 */
public class CalcOperation extends AbstractUnaryDatasetOperation {
    
    private VTLExpression componentExpression;
    private final String variableName;
    private final Component.Role role;
    private final Boolean implicit;
    
    public CalcOperation(Dataset dataset, VTLExpression componentExpression, String identifier, Component.Role role, Boolean implicit) {
        super(checkNotNull(dataset));
        this.componentExpression = checkNotNull(componentExpression);
        this.role = checkNotNull(role);
        this.implicit = checkNotNull(implicit);
        // TODO: move to visitor and reuse Pawel's helper.
        this.variableName = removeQuoteIfNeeded(identifier);
    }
    
    @Override
    protected DataStructure computeDataStructure() {

        DataStructure.Builder builder = DataStructure.builder();
        DataStructure dataStructure = getChild().getDataStructure();
        for (Map.Entry<String, Component> entry : dataStructure.entrySet()) {
            if (entry.getKey().equals(variableName))
                continue;
            builder.put(entry.getKey(), entry.getValue());
        }

        Class<?> type = componentExpression.getType();

        if (dataStructure.containsKey(variableName)) {
            Component existingComponent = dataStructure.get(variableName);

            // Overriding identifier is not permitted.
            checkArgument(existingComponent.getRole() != Component.Role.IDENTIFIER,
                    "an identifier %s already exists in %s", variableName, getChild()
            );

            // Implicit fails if a component with the same name but a different role
            // already exists.
            if (implicit) {
                checkArgument(role.equals(existingComponent.getRole()),
                        "the role of the component %s must be %s",
                        variableName, existingComponent.getRole()
                );
            }
        }

        builder.put(variableName, role, type);

        return builder.build();
    }
    
    private static String removeQuoteIfNeeded(String key) {
        if (!key.isEmpty() && key.length() > 3) {
            if (key.charAt(0) == '\'' && key.charAt(key.length() - 1) == '\'') {
                return key.substring(1, key.length() - 1);
            }
        }
        return key;
    }

    @Override
    public Stream<DataPoint> getData() {
        return getChild().getData().map(dataPoint -> {
            dataPoint.add(getDataStructure().wrap(variableName, componentExpression.apply(dataPoint)));
            return dataPoint;
        });
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
