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

import com.google.common.base.Objects;
import com.google.common.collect.*;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.support.JoinSpliterator;

import javax.script.Bindings;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Abstract join operation.
 */
public abstract class AbstractJoinOperation implements WorkingDataset {

    // The datasets the join operates on.
    private final Map<String, Dataset> datasets;
    private final Bindings joinScope;

    public AbstractJoinOperation(Map<String, Dataset> namedDatasets) {

        checkArgument(
                !namedDatasets.isEmpty(),
                "join operation impossible on empty dataset list"
        );

        this.datasets = namedDatasets;
        this.joinScope = new JoinScopeBindings(this.datasets);

    }

    // TODO: Filtering in each ids() access is very expensive. Need to optimise.
    private static JoinTuple createJoinTuple(Tuple tuple) {
        JoinTuple joinTuple = new JoinTuple(tuple.ids());
        joinTuple.addAll(tuple.values());
        return joinTuple;
    }

    protected abstract JoinSpliterator.TriFunction<JoinTuple, JoinTuple, Integer, List<JoinTuple>> getMerger();

    protected abstract Comparator<List<DataPoint>> getKeyComparator();

    protected abstract ImmutableSet<Component> getIdentifiers();

    /**
     * Compute a multimap with components eligible as keys.
     */
    final ImmutableMultimap<String, Component> getIdentifierSuperSet() {

        Multimap<SuperSetWrapper, Component> superSet = LinkedListMultimap.create();
        for (Dataset dataset : datasets.values()) {
            for (Map.Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
                Component component = componentEntry.getValue();
                if (component.isIdentifier()) {
                    String name = componentEntry.getKey();
                    Class<?> type = component.getType();
                    superSet.put(
                            new SuperSetWrapper(type, name),
                            component
                    );
                }
            }
        }

        ImmutableMultimap.Builder<String, Component> builder = ImmutableMultimap.builder();
        for (Map.Entry<SuperSetWrapper, Collection<Component>> entry : superSet.asMap().entrySet()) {
            if (entry.getValue().size() >= datasets.size()) {
                builder.putAll(
                        entry.getKey().name,
                        entry.getValue()
                );
            }
        }
        return builder.build();
    }

    @Override
    public Stream<Tuple> get() {

        // Optimization.
        if (datasets.size() == 1) {
            return datasets.values().iterator().next().get();
        }

        Iterator<Dataset> iterator = datasets.values().iterator();
        Stream<JoinTuple> result = iterator.next().get()
                .map(AbstractJoinOperation::createJoinTuple);

        while (iterator.hasNext()) {
            result = StreamSupport.stream(
                    new JoinSpliterator<>(
                            getKeyComparator(),
                            result.spliterator(),
                            iterator.next().get()
                                    .map(AbstractJoinOperation::createJoinTuple)
                                    .spliterator(),
                            getKeyExtractor(),
                            getKeyExtractor(),
                            getMerger()
                    ), false
            );
        }
        return result.map(tuple -> tuple);
    }

    protected Function<JoinTuple, List<DataPoint>> getKeyExtractor() {
        return tuple -> {
            // Filter by common ids.
            return tuple.stream().filter(dataPoint ->
                    getIdentifiers().contains(dataPoint.getComponent())
            ).collect(Collectors.toList());
        };
    }

    /**
     * Compute the DataStructure of the join dataset.
     */
    public DataStructure getDataStructure() {

        // Optimization.
        if (datasets.size() == 1) {
            return datasets.values().iterator().next().getDataStructure();
        }

        Set<String> ids = Sets.newHashSet();
        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (String datasetName : datasets.keySet()) {
            DataStructure structure = datasets.get(datasetName).getDataStructure();
            for (Map.Entry<String, Component> componentEntry : structure.entrySet()) {
                if (!componentEntry.getValue().isIdentifier()) {
                    newDataStructure.put(datasetName.concat("_".concat(componentEntry.getKey())), componentEntry.getValue());
                } else {
                    if (ids.add(componentEntry.getKey())) {
                        newDataStructure.put(componentEntry);
                    }
                }
            }
        }
        return newDataStructure.build();
    }

    public Bindings getJoinScope() {
        return joinScope;
    }

    public abstract WorkingDataset workDataset();

    /**
     * Wrapper that helps with superset calculation.
     */
    private final static class SuperSetWrapper {

        private final Class<?> clazz;
        private final String name;

        private SuperSetWrapper(Class<?> clazz, String name) {
            this.clazz = clazz;
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SuperSetWrapper that = (SuperSetWrapper) o;
            return Objects.equal(clazz, that.clazz) &&
                    Objects.equal(name, that.name);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(clazz, name);
        }
    }

    /**
     * Holds the "working dataset" tuples.
     */
    static final class JoinTuple extends Dataset.AbstractTuple implements RandomAccess {

        private final List<DataPoint> delegate = Lists.newArrayList();

        public JoinTuple(List<DataPoint> ids) {
            this.delegate.addAll(ids);
        }

        @Override
        protected List<DataPoint> delegate() {
            return delegate;
        }

    }

}
