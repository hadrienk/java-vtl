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

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Order;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A dataset that avoid sorting on data that is already sorted.
 */
public class ForeachDataset extends IteratorDataset {

    private final Order order;

    public ForeachDataset(
            DataStructure structure,
            Iterator<DataPoint> source,
            Order order
    ) {
        super(structure, source);
        this.order = checkNotNull(order);
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order orders, Filtering filtering, Set<String> components) {
        if (orders.equals(order)) {

        }
        return Optional.empty();
    }
}
