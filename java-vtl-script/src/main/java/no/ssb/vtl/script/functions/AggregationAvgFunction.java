package no.ssb.vtl.script.functions;

/*
 * -
 *  * ========================LICENSE_START=================================
 * * Java VTL
 *  *
 * %%
 * Copyright (C) 2017 Arild Johan Takvam-Borge
 *  *
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
 *
 */

import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.script.operations.aggregation.AbstractAggregationFunction;

import java.util.List;
import java.util.Objects;
import java.util.OptionalDouble;

public class AggregationAvgFunction extends AbstractAggregationFunction<VTLFloat> {

    public AggregationAvgFunction() {
        super(VTLFloat.class);
    }

    @Override
    public VTLNumber apply(List<VTLNumber> vtlNumbers) {
        // TODO: Support for all non finite values.
        OptionalDouble average = vtlNumbers
                .stream()
                .filter(Objects::nonNull)
                .filter(n -> n.get() != null)
                .mapToDouble(n -> n.get().doubleValue())
                .average();

        if (average.isPresent()) {
            return VTLFloat.of(average.getAsDouble());
        } else {
            return VTLFloat.of((Double) null);
        }
    }

    @Override
    public Class<?> getVTLReturnTypeFor(Class<?> clazz) {
        return Double.class;
    }
}

