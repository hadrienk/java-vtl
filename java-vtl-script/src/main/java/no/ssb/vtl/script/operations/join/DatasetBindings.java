package no.ssb.vtl.script.operations.join;

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
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLDate;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLString;
import no.ssb.vtl.model.VTLTyped;

import javax.script.SimpleBindings;
import java.time.Instant;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A bindings wrapper that exposes the components of a dataset as {@link no.ssb.vtl.model.VTLTyped}.
 */
final class DatasetBindings extends SimpleBindings {

    private static final Map<Class<?>, Class<? extends VTLObject>> typeMap = ImmutableMap.of(
            String.class, VTLString.class,
            Long.class, VTLNumber.class,
            Double.class, VTLNumber.class,
            Instant.class, VTLDate.class,
            Boolean.class, VTLBoolean.class
    );

    DatasetBindings(Dataset dataset) {
        checkNotNull(dataset);
        for (Entry<String, Component> entry : dataset.getDataStructure().entrySet()) {
            VTLTyped<?> type = createType(entry.getValue());
            put(entry.getKey(), type);
        }
    }

    private static final VTLTyped<?> createType(Component component) {
        if (typeMap.containsKey(component.getType())) {
            return new VTLTyped<VTLObject>() {
                @Override
                public Class<VTLObject> getType() {
                    return (Class<VTLObject>) typeMap.get(component.getType());
                }
            };
        } else {
            throw new UnsupportedOperationException("Unknown type " + component.getType().getSimpleName());
        }
    }
}
