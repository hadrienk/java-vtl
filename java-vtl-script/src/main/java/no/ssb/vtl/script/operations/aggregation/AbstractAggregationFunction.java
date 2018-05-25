package no.ssb.vtl.script.operations.aggregation;

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

import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLTyped;

import java.util.List;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractAggregationFunction<T extends VTLNumber> implements VTLTyped<T>, Function<List<VTLNumber>, VTLNumber> {

    private final Class<T> clazz;

    protected AbstractAggregationFunction(Class<T> clazz) {
        this.clazz = checkNotNull(clazz);
    }

    @Override
    public Class<T> getVTLType() {
        return clazz;
    }

    /**
     * Compute the type of the resulting component
     */
    public abstract Class<?> getVTLReturnTypeFor(Class<?> clazz);
}
