package no.ssb.vtl.model;

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

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class NewOrder implements Comparator<DataPoint> {

    public static final Comparator<Comparable> NULLS_FIRST = Comparator.<Comparable>nullsFirst(Comparator.naturalOrder());
    public static final Comparator<VTLObject> VTL_OBJECT_COMPARATOR = Comparator.comparing(vtlObject -> (Comparable) vtlObject.get(), NULLS_FIRST);

    private final DataStructure structure;
    private final ImmutableMap<String, Order.Direction> directionMap;
    private final Comparator<DataPoint> delegate;

    NewOrder(DataStructure structure, ImmutableMap<String, Order.Direction> directionMap) {
        this.structure = structure;
        this.directionMap = directionMap;

        ImmutableList<String> columnNames = ImmutableSet.copyOf(this.structure.keySet()).asList();
        List<Comparator<DataPoint>> comparators = new ArrayList<>();
        for (String name : directionMap.keySet()) {
            int index = columnNames.indexOf(name);
            Comparator<DataPoint> comparator = Comparator.comparing(dp -> dp.get(index), VTL_OBJECT_COMPARATOR);
            Order.Direction direction = directionMap.get(name);
            comparators.add(direction == Order.Direction.DESC ? comparator.reversed() : comparator);
        }

        Iterator<Comparator<DataPoint>> iterator = comparators.iterator();
        Comparator<DataPoint> comparator = iterator.next();
        while (iterator.hasNext()) {
            comparator = comparator.thenComparing(iterator.next());
        }
        this.delegate = comparator;
    }

    public static Builder builder(DataStructure structure) {
        return new Builder(structure);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NewOrder other = (NewOrder) o;

        // Ordering is the same if all columns/direction in this
        // are present and in the same order in other.

        Iterator<Map.Entry<String, Order.Direction>> it = directionMap.entrySet().iterator();
        Iterator<Map.Entry<String, Order.Direction>> otherIt = other.directionMap.entrySet().iterator();
        while (it.hasNext() && otherIt.hasNext()) {
            if (!Objects.equal(it.next(), otherIt.next())) {
                return false;
            }
        }
        return !it.hasNext();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(directionMap);
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        directionMap.entrySet().forEach(helper::addValue);
        return helper.toString();
    }

    @Override
    public int compare(DataPoint o1, DataPoint o2) {
        return delegate.compare(o1, o2);
    }

    static final class Builder {

        private final ImmutableMap.Builder<String, Order.Direction> mapBuilder = ImmutableMap.builder();
        private final DataStructure structure;

        private Builder(DataStructure structure) {
            this.structure = checkNotNull(structure);
        }

        public Builder put(String key, Order.Direction value) {
            checkArgument(structure.containsKey(key));
            mapBuilder.put(key, value);
            return this;
        }

        public Builder put(Map.Entry<? extends String, ? extends Order.Direction> entry) {
            checkArgument(structure.containsKey(entry.getKey()));
            mapBuilder.put(entry);
            return this;
        }

        public Builder putAll(Map<? extends String, ? extends Order.Direction> map) {
            return putAll(map.entrySet());
        }

        public Builder putAll(Iterable<? extends Map.Entry<? extends String, ? extends Order.Direction>> entries) {
            entries.forEach(e -> checkArgument(structure.containsKey(e.getKey())));
            mapBuilder.putAll(entries);
            return this;
        }

        public Builder orderEntriesByValue(Comparator<? super Order.Direction> valueComparator) {
            mapBuilder.orderEntriesByValue(valueComparator);
            return this;
        }

        public NewOrder build() {
            return new NewOrder(this.structure, this.mapBuilder.build());
        }
    }
}
