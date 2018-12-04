package no.ssb.vtl.script.operations.join;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2018 Hadrien Kohl
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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Table;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.Order;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.OrderingSpecification;
import no.ssb.vtl.model.VtlOrdering;
import no.ssb.vtl.script.operations.AbstractDatasetOperation;
import no.ssb.vtl.script.operations.VtlStream;
import no.ssb.vtl.script.support.Closer;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class InnerJoinOperation extends AbstractJoinOperation {


    public InnerJoinOperation(Map<String, Dataset> namedDatasets) {
        this(namedDatasets, Collections.emptySet());
    }

    public InnerJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets, identifiers);
        // We need the identifiers in the case of inner join.
        ComponentBindings joinScope = this.getJoinScope();
        for (Component component : getCommonIdentifiers()) {
            joinScope.put(
                    getDataStructure().getName(component),
                    component
            );
        }
    }

    @Override
    public Stream<DataPoint> computeData(Ordering requestedOrder, Filtering filtering, Set<String> components) {

        // Try to create a compatible order.
        // If not, the caller will have to sort the result manually.
        Optional<Ordering> compatibleOrder = createCompatibleOrder(getDataStructure(), getCommonIdentifiers(), requestedOrder);
        if (!compatibleOrder.isPresent()) {
            throw new UnsupportedOperationException();
        }

        Ordering requiredOrder = compatibleOrder.get();

        // Compute the predicate
        Ordering predicate = computePredicate(requiredOrder);

        // TODO: Use abstract operation here.
        Iterator<Dataset> iterator = datasets.values().iterator();
        Dataset left = iterator.next();
        Dataset right = left;

        ImmutableList.Builder<Stream<DataPoint>> originals = ImmutableList.builder();

        Closer closer = Closer.create();
        try {

            Table<Component, Dataset, Component> componentMapping = getComponentMapping();
            Stream<DataPoint> original = getOrSortData(
                    left,
                    adjustOrderForStructure(requiredOrder, left.getDataStructure()),
                    filtering,
                    components
            );
            originals.add(original);
            Stream<DataPoint> result = original.peek(new DataPointCapacityExpander(getDataStructure().size()));
            closer.register(result);


            boolean first = true;
            while (iterator.hasNext()) {
                left = right;
                right = iterator.next();

                Stream<DataPoint> rightStream = getOrSortData(
                        right,
                        adjustOrderForStructure(requiredOrder, right.getDataStructure()),
                        filtering,
                        components
                );
                originals.add(rightStream);
                closer.register(rightStream);

                // The first left stream uses its own structure. After that, the left data structure
                // will always be the resulting structure. We use a flag (first) to handle the first case
                // since the hotfix needs to quickly released but this code should be refactored.

                result = StreamSupport.stream(
                        new InnerJoinSpliterator<>(
                                new JoinKeyExtractor(
                                        first ? left.getDataStructure() : getDataStructure(),
                                        predicate,
                                        first ? componentMapping.column(left)::get : c -> c
                                ),
                                new JoinKeyExtractor(right.getDataStructure(), predicate, componentMapping.column(right)),
                                predicate,
                                new InnerJoinMerger(getDataStructure(), right.getDataStructure()),
                                result.spliterator(),
                                rightStream.spliterator()
                        ), false
                );

                first = false;
            }

            // Close all the underlying streams.
            Stream<DataPoint> delegate = result.onClose(() -> {
                try {
                    closer.close();
                } catch (IOException e) {
                    // ignore (cannot happen).
                }
            });

            // TODO: Post order and post filter.
            // TODO: Closer could be moved to VtlStream.
            return new VtlStream(
                    this,
                    delegate,
                    originals.build(),
                    requestedOrder,
                    filtering,
                    requiredOrder,
                    filtering
            );

        } catch (Exception ex) {
            try {
                closer.close();
            } catch (IOException ioe) {
                ex.addSuppressed(ioe);
            }
            throw ex;
        }
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        if (getChildren().size() == 1) {
            return getChildren().get(0).getDistinctValuesCount();
        } else {
            // TODO
            return Optional.empty();
        }
    }

    @Override
    public Optional<Long> getSize() {
        if (getChildren().size() == 1) {
            return getChildren().get(0).getSize();
        } else {
            // TODO
            return Optional.empty();
        }
    }

    @Override
    public FilteringSpecification unsupportedFiltering(FilteringSpecification filtering) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public OrderingSpecification unsupportedOrdering(OrderingSpecification filtering) {
        throw new UnsupportedOperationException("TODO");
    }
}
