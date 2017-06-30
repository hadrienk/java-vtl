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

import static com.google.common.base.Preconditions.*;

public class CalcOperation extends AbstractUnaryDatasetOperation {
    
    private VTLExpression componentExpression;
    private final String variableName;
    
    public CalcOperation(Dataset dataset, VTLExpression componentExpression, String identifier) {
        super(checkNotNull(dataset, "the dataset was null"));
        this.componentExpression = checkNotNull(componentExpression, "the function was null");
        
        /*
            TODO: Handle explicit and implicit component computation.
         */
        variableName = removeQuoteIfNeeded(identifier);
//        return new Dataset() {
//
//
//
//            @Override
//            public String toString() {
//                MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper("Calc");
//                return helper.omitNullValues().toString();
//            }
//        };
    }
    
    @Override
    protected DataStructure computeDataStructure() {

        Boolean implicit = true;
        Component.Role defaultRole = Component.Role.MEASURE;

        DataStructure.Builder builder = DataStructure.builder();
        DataStructure dataStructure = getChild().getDataStructure();
        for (Map.Entry<String, Component> entry : dataStructure.entrySet()) {
            if (dataStructure.containsKey(variableName))
                continue;
            builder.put(entry.getKey(), entry.getValue());
        }

        Component.Role role = Component.Role.MEASURE;
        Class<?> type = componentExpression.getType();

        // TODO: Support implicit
        if (dataStructure.containsKey(variableName) && implicit)
            role = dataStructure.get(variableName).getRole();

        // TODO: Support role change
        if (false)
            role = Component.Role.MEASURE;

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
