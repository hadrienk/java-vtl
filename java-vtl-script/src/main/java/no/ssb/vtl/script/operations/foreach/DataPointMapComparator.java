package no.ssb.vtl.script.operations.foreach;

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
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.Ordering;

import java.util.Comparator;
import java.util.Map;

import static no.ssb.vtl.model.Ordering.Direction.ASC;
import static no.ssb.vtl.model.Order.VTL_OBJECT_COMPARATOR;

public class DataPointMapComparator implements Comparator<DataPointMap.View> {

    private final ImmutableMap<String, Ordering.Direction> identifiers;

    public DataPointMapComparator(Map<String, Ordering.Direction> order) {
        this.identifiers = ImmutableMap.copyOf(order);
    }

    public DataPointMapComparator(Map<String, Component> identifiers, Order order) {
        ImmutableMap.Builder<String, Ordering.Direction> builder = ImmutableMap.builder();
        for (String key : identifiers.keySet()) {
            builder.put(key, order.get(identifiers.get(key)));
        }
        this.identifiers = builder.build();
    }

    @Override
    public int compare(DataPointMap.View o1, DataPointMap.View o2) {
        int result;
        for (String key : identifiers.keySet()) {
            result = VTL_OBJECT_COMPARATOR.compare(o1.get(key), o2.get(key));
            if (result != 0) {
                return identifiers.get(key) == ASC ? result : -result;
            }
        }
        return 0;
    }
}
