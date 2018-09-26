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

import com.google.common.annotations.Beta;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;
import static no.ssb.vtl.model.Ordering.Direction.ASC;

/**
 * Represent the ordering the {@link DataPoint}s in a Dataset.
 * <p>
 * Deprecated. Replace with interface Ordering or AbstractOrdering implementations.
 */
@Deprecated
public final class Order extends ForwardingMap<Component, no.ssb.vtl.model.Ordering.Direction> implements no.ssb.vtl.model.Ordering {

    public static final Comparator<Comparable> NULLS_FIRST = Comparator.<Comparable>nullsFirst(Comparator.naturalOrder());
    public static final Comparator<VTLObject> VTL_OBJECT_COMPARATOR = Comparator.comparing(vtlObject -> (Comparable) vtlObject.get(), NULLS_FIRST);

    public static final Comparator<Entry<String, Component>> BY_ROLE = Comparator.comparing(
            entry -> entry.getValue().getRole(),
            Ordering.explicit(
                    Component.Role.IDENTIFIER,
                    Component.Role.MEASURE,
                    Component.Role.ATTRIBUTE
            )
    );

    public static final Comparator<Entry<String, Component>> BY_NAME = Comparator.comparing(Entry::getKey);

    private final DataStructure structure;
    private final ImmutableMap<Component, no.ssb.vtl.model.Ordering.Direction> delegate;
    private final int[] indices;
    private final no.ssb.vtl.model.Ordering.Direction[] directions;

    Order(DataStructure structure, ImmutableMap<Component, no.ssb.vtl.model.Ordering.Direction> orders) {
        this.delegate = ImmutableMap.copyOf(orders);
        this.structure = checkNotNull(structure);

        ArrayList<Integer> indices = Lists.newArrayList();
        ArrayList<no.ssb.vtl.model.Ordering.Direction> directions = Lists.newArrayList();
        for (Component component : orders.keySet()) {
            indices.add(structure.indexOf(component));
            directions.add(orders.get(component));
        }

        this.indices = Ints.toArray(indices);
        this.directions = directions.toArray(new no.ssb.vtl.model.Ordering.Direction[]{});


    }

    /**
     * Create a new Order.Builder instance.
     *
     * @param structure the structure this Order will operate on.
     */
    public static Builder create(DataStructure structure) {
        return new Builder(structure);
    }

    /**
     * Create a copy of the order.
     * <p>
     * Useful to change data structure.
     */
    public static Order.Builder createCopyOf(Order order) {
        return create(order.structure);
    }

    /**
     * Return the default Order for the given DataStructure.
     */
    public static Order createDefault(DataStructure dataStructure) {
        Map<Component, no.ssb.vtl.model.Ordering.Direction> order = dataStructure.entrySet().stream()
                .filter(e -> e.getValue().isIdentifier())
                .sorted(BY_ROLE.thenComparing(BY_NAME))
                .collect(ImmutableMap.toImmutableMap(Entry::getValue, o -> ASC));
        return new Order(dataStructure, ImmutableMap.copyOf(order));
    }

    public Component get(String column) {
        return structure.get(column);
    }

    public boolean containsKey(String column) {
        return containsKey(get(column));
    }

    public no.ssb.vtl.model.Ordering.Direction getOrDefault(String key, no.ssb.vtl.model.Ordering.Direction defaultValue) {
        no.ssb.vtl.model.Ordering.Direction v;
        return (((v = get(get(key))) != null) || containsKey(key))
                ? v
                : defaultValue;
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        if (structure != null && delegate != null) {
            for (Entry<Component, no.ssb.vtl.model.Ordering.Direction> entry : delegate.entrySet()) {
                helper.add(structure.getName(entry.getKey()), entry.getValue());
            }
        }
        return helper.toString();
    }

    @Override
    public int compare(DataPoint o1, DataPoint o2) {
        int result;

        for (int i = 0; i < indices.length; i++) {
            result = VTL_OBJECT_COMPARATOR.compare(o1.get(indices[i]), o2.get(indices[i]));
            if (result != 0) {
                return directions[i] == ASC ? result : -result;
            }
        }
        return 0;
    }

    @Override
    protected Map<Component, no.ssb.vtl.model.Ordering.Direction> delegate() {
        return delegate;
    }

    @Override
    public Iterable<String> columns() {
        return Iterables.transform(delegate.keySet(), structure::getName);
    }

    @Override
    public Direction getDirection(String column) {
        return delegate.get(structure.get(column));
    }

    public static class Builder {

        private final ImmutableMap.Builder<Component, no.ssb.vtl.model.Ordering.Direction> delegate = ImmutableMap.builder();
        private final DataStructure structure;

        private Builder(DataStructure structure) {
            this.structure = checkNotNull(structure);
        }

        public Builder put(Component key, no.ssb.vtl.model.Ordering.Direction value) {
            delegate.put(key, value);
            return this;
        }

        public Builder put(String key, no.ssb.vtl.model.Ordering.Direction value) {
            delegate.put(structure.get(key), value);
            return this;
        }

        public Builder put(Entry<Component, no.ssb.vtl.model.Ordering.Direction> entry) {
            delegate.put(entry);
            return this;
        }

        public Builder putAll(Map<Component, no.ssb.vtl.model.Ordering.Direction> map) {
            delegate.putAll(map);
            return this;
        }

        @Beta
        public Builder putAll(Iterable<? extends Entry<Component, no.ssb.vtl.model.Ordering.Direction>> entries) {
            delegate.putAll(entries);
            return this;
        }

        public Order build() {
            return new Order(structure, delegate.build());
        }
    }
}
