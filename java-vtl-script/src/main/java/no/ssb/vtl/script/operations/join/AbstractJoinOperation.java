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
import no.ssb.vtl.model.*;
import no.ssb.vtl.script.support.JoinSpliterator;

import javax.script.Bindings;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract join operation.
 */
public abstract class AbstractJoinOperation extends AbstractDatasetOperation implements WorkingDataset {

    // The datasets the join operates on.
    private final Bindings joinScope;
    private final ImmutableMap<String, Dataset> datasets;
    private final ImmutableSet<Component> identifiers;

    AbstractJoinOperation(Map<String, Dataset> namedDatasets, Set<Component> identifiers) {
        super(Lists.newArrayList(namedDatasets.values()));
        this.datasets = ImmutableMap.copyOf(checkNotNull(namedDatasets));

        checkNotNull(identifiers);
        checkArgument(
                !namedDatasets.isEmpty(),
                "join operation impossible on empty dataset list"
        );

        this.joinScope = new JoinScopeBindings(this.datasets);

        this.identifiers = createIdentifierSet(identifiers);

    }

    private ImmutableSet<Component> createIdentifierSet(Set<Component> identifiers) {
        ImmutableMultimap<String, Component> superSet = createCommonIdentifiers();

        HashSet<Component> keySet = Sets.newHashSet(superSet.values());

        // Checks that the datasets have at least one common identifier.
        checkArgument(
                !keySet.isEmpty(),
                "could not find common identifiers in the datasets %s",
                superSet.keySet()
        );

        // Use all common identifiers if identifiers is  empty
        ImmutableSet<Component> result;
        if (!identifiers.isEmpty()) {
            result = ImmutableSet.copyOf(
                    Sets.intersection(
                            identifiers,
                            keySet
                    )
            );
            checkArgument(!result.isEmpty(), "cannot use %s as key",
                    Sets.difference(identifiers, Sets.newHashSet(keySet)));
        } else {
            result = ImmutableSet.copyOf(keySet);
        }
        return result;
    }

    protected abstract JoinSpliterator.TriFunction<JoinDataPoint, JoinDataPoint, Integer, List<JoinDataPoint>> getMerger();

    protected ImmutableSet<Component> getIdentifiers() {
        return this.identifiers;
    }

    /**
     * Compute a multimap with components eligible as keys.
     */
    private ImmutableMultimap<String, Component> createCommonIdentifiers() {

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
    public Stream<? extends DataPoint> getData() {
        // Optimization.
        if (datasets.size() == 1) {
            return datasets.values().iterator().next().get();
        }

        Iterator<Dataset> iterator = datasets.values().iterator();
        Stream<JoinDataPoint> result = iterator.next().get()
                .map(JoinDataPoint::new);

        while (iterator.hasNext()) {
            result = StreamSupport.stream(
                    new JoinSpliterator<>(
                            getKeyComparator(),
                            result.spliterator(),
                            iterator.next().get()
                                    .map(JoinDataPoint::new)
                                    .spliterator(),
                            getKeyExtractor(),
                            getKeyExtractor(),
                            getMerger()
                    ), false
            );
        }
        return result.map(tuple -> tuple);
    }

    @Override
    @Deprecated
    public Stream<DataPoint> get() {
        return getData().map(o -> o);
    }

    private Function<JoinDataPoint, List<VTLObject>> getKeyExtractor() {
        return tuple -> {
            // Filter by common ids.
            return tuple.stream().filter(dataPoint ->
                    getIdentifiers().contains(dataPoint.getComponent())
            ).collect(Collectors.toList());
        };
    }

    @Override
    protected DataStructure computeDataStructure() {
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

    @Deprecated
    public abstract WorkingDataset workDataset();

    protected Comparator<List<VTLObject>> getKeyComparator() {
        ImmutableSet<Component> keys = getIdentifiers();
        return (l, r) -> {
            // TODO: DataPoint should expose method to handle this.
            // TODO: Evaluate migrating to VTLObject.
            // TODO: When using on, the left over identifiers should be transformed to measures.

            Map<String, Comparable> lIds = l.stream()
                    .filter(dataPoint -> keys.contains(dataPoint.getComponent()))
                    .collect(Collectors.toMap(
                            VTLObject::getName,
                            t -> (Comparable) t.get()
                    ));
            Map<String, Object> rIds = r.stream()
                    .filter(dataPoint -> keys.contains(dataPoint.getComponent()))
                    .collect(Collectors.toMap(
                            VTLObject::getName,
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
     * Holds the "working dataset" dataPoint.
     */
    static final class JoinDataPoint extends DataPoint.AbstractDataPoint implements RandomAccess {

        private final List<VTLObject> delegate = Lists.newArrayList();

        public JoinDataPoint(List<VTLObject> ids) {
            this.delegate.addAll(ids);
        }

        @Override
        protected List<VTLObject> delegate() {
            return delegate;
        }

    }

}
