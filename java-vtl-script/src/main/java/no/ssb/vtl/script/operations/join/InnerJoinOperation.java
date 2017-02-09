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

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represent an inner join on datasets.
 */
public class InnerJoinOperation extends AbstractJoinOperation {

    private ImmutableSet<Component> identifiers;

    public InnerJoinOperation(Map<String, Dataset> namedDatasets) {
        this(namedDatasets, Collections.emptySet());
    }

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
    public ImmutableSet<Component> getIdentifiers() {
        return identifiers;
    }

    @Override
    public WorkingDataset workDataset() {
        // TODO: Remove
        return this;
    }

    @Override
    protected BiFunction<JoinTuple, Dataset.Tuple, JoinTuple> getMerger() {
        return (joinTuple, components) -> {
            joinTuple.addAll(components.values());
            return joinTuple;
        };
    }

    @Override
    protected Comparator<List<DataPoint>> getKeyComparator() {
        ImmutableMultimap<Dataset, Component> keys = getPossibleKeyComponents();
        return (l, r) -> {
            // TODO: Tuple should expose method to handle this.
            // TODO: Evaluate migrating to DataPoint.
            // TODO: When using on, the left over identifiers should be transformed to measures.

            Map<String, Comparable> lIds = l.stream()
                    .filter(dataPoint -> keys.containsValue(dataPoint.getComponent()))
                    .collect(Collectors.toMap(
                            DataPoint::getName,
                            t -> (Comparable) t.get()
                    ));
            Map<String, Object> rIds = r.stream()
                    .filter(dataPoint -> keys.containsValue(dataPoint.getComponent()))
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
