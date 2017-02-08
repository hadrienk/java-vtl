package no.ssb.vtl.script.operations.join;
/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.base.MoreObjects;
import com.google.common.collect.*;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.JoinSpliterator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represent an inner join on datasets.
 */
public class InnerJoinOperation extends AbstractJoinOperation {

    public InnerJoinOperation(Map<String, Dataset> namedDatasets) {
        this(namedDatasets, Collections.emptySet());
    }

    ImmutableSet<Component> identifiers;

    public InnerJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(namedDatasets);

        checkNotNull(identifiers);

        // Optimization
        if (namedDatasets.size() == 1) {
            return;
        }

        ImmutableMultimap<Dataset, Component> possibleKeyComponentsByDataset = getPossibleKeyComponents();
        Set<Component> allPossibleKeyComponents = Sets.newHashSet(possibleKeyComponentsByDataset.values());

        // Checks that the datasets have at least on common identifier.
        checkArgument(
                !allPossibleKeyComponents.isEmpty(),
                "could not find common identifiers in the datasets %s",
                namedDatasets.keySet()
        );

        // Use all common identifiers if identifiers is empty
        if (!identifiers.isEmpty()) {
            this.identifiers = ImmutableSet.copyOf(
                    Sets.intersection(
                            identifiers,
                            allPossibleKeyComponents
                    )
            );
            checkArgument(!this.identifiers.isEmpty(), "cannot use %s as key",
                    Sets.difference(identifiers, Sets.newHashSet(possibleKeyComponentsByDataset.values())));
        } else {
            this.identifiers = ImmutableSet.copyOf(allPossibleKeyComponents);
        }
    }

    @Override
    public WorkingDataset workDataset() {

        return new WorkingDataset() {

            @Override
            public String toString() {
                MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
                return helper.toString();
            }

            @Override
            public DataStructure getDataStructure() {
                return InnerJoinOperation.this.getDataStructure();
            }

            @Override
            public Stream<Tuple> get() {
                Collection<Dataset> datasets = getDatasets().values();
                checkArgument(!datasets.isEmpty(), "no dataset for the join %s", this);

                // Optimization.
                if (datasets.size() == 1) {
                    return datasets.iterator().next().get();
                }

                // Get the key comparator.
                Comparator<List<DataPoint>> keyComparator = getKeyComparator();

                // How to merge the tuples.
                BiFunction<JoinTuple, Dataset.Tuple, JoinTuple> merger = getMerger();

                DataStructure joinedDataStructure = getDataStructure();
                Iterator<Dataset> iterator = datasets.iterator();
                Stream<JoinTuple> result = iterator.next().get().map(components -> {
                    components.replaceAll(dataPoint -> {
                        // Get the new component.
                        Component newComponent = joinedDataStructure.get(joinedDataStructure.getName(dataPoint.getComponent()));
                        return new DataPoint(newComponent) {
                            @Override
                            public Object get() {
                                return dataPoint.get();
                            }
                        };
                    });
                    JoinTuple joinTuple = new JoinTuple(components.ids());
                    joinTuple.addAll(components.values());
                    return joinTuple;
                });

                while (iterator.hasNext()) {
                    Function<Tuple, List<DataPoint>> keyExtractor = tuple -> {
                        // Filter by common ids.
                        return tuple.stream().filter(dataPoint ->
                                identifiers.contains(dataPoint.getComponent())
                        ).collect(Collectors.toList());
                    };
                    Function<JoinTuple, List<DataPoint>> joinKeyExtractor = tuple -> {
                        // Filter by common ids.
                        return tuple.stream().filter(dataPoint ->
                                identifiers.contains(dataPoint.getComponent())
                        ).collect(Collectors.toList());
                    };
                    result = StreamSupport.stream(
                            new JoinSpliterator<>(
                                    keyComparator,
                                    result.spliterator(),
                                    iterator.next().get().spliterator(),
                                    joinKeyExtractor,
                                    keyExtractor,
                                    merger
                            ), false
                    );
                }
                return result.map(tuple -> (Tuple) tuple);
            }
        };
    }

    private BiFunction<JoinTuple, Dataset.Tuple, JoinTuple> getMerger() {
        return (joinTuple, components) -> {
            joinTuple.addAll(components.values());
            return joinTuple;
        };
    }

    private Comparator<List<DataPoint>> getKeyComparator() {
        return (l, r) -> {
            // TODO: Tuple should expose method to handle this.
            // TODO: Evaluate migrating to DataPoint.
            // TODO: When using on, the left over identifiers should be transformed to measures.

            Map<String, Comparable> lIds = l.stream()
                    .filter(dataPoint -> identifiers.contains(dataPoint.getComponent()))
                    .collect(Collectors.toMap(
                            DataPoint::getName,
                            t -> (Comparable) t.get()
                    ));
            Map<String, Object> rIds = r.stream()
                    .filter(dataPoint -> identifiers.contains(dataPoint.getComponent()))
                    .collect(Collectors.toMap(
                            DataPoint::getName,
                            Supplier::get
                    ));
            for (String key : lIds.keySet()) {
                int res = lIds.get(key).compareTo(rIds.get(key));
                if (res != 0)
                    return res;
            }
            return 0;
        };
    }
}
