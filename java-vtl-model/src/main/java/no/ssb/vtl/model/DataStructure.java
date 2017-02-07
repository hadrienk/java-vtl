package no.ssb.vtl.model;

/*-
 * #%L
 * java-vtl-model
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

import com.google.common.annotations.Beta;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.Comparator;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Data structure of a {@link Dataset}.
 * <p>
 * The data structure defines the role and type of the columns of a data set and
 * serves as a {@link DataPoint}s and {@link no.ssb.vtl.model.Dataset.Tuple}s factory.
 */
public class DataStructure extends ForwardingMap<String, Component> {

    private final BiFunction<Object, Class<?>, ?> converter;

    private final ImmutableMap<String, Component> delegate;

    private final IdentityHashMap<Component, String> inverseCache;
    private final ImmutableMap<String, Component.Role> roleCache;
    private final ImmutableMap<String, Class<?>> typeCache;

    protected DataStructure(BiFunction<Object, Class<?>, ?> converter, ImmutableMap<String, Component> map) {
        this.converter = checkNotNull(converter);
        this.delegate = checkNotNull(map);
        this.inverseCache = computeInverseCache(this.delegate);
        this.roleCache = computeRoleCache(delegate);
        this.typeCache = computeTypeCache(delegate);
    }

    private static ImmutableMap<String, Component.Role> computeRoleCache(ImmutableMap<String, Component> delegate) {
        return ImmutableMap.copyOf(Maps.transformValues(delegate, Component::getRole));
    }

    private static ImmutableMap<String, Class<?>> computeTypeCache(ImmutableMap<String, Component> delegate) {
        return ImmutableMap.copyOf(Maps.transformValues(delegate, Component::getType));
    }

    public static DataStructure.Builder builder() {
        return new DataStructure.Builder();
    }

    public static DataStructure.Builder copyOf(
            Map<String, Component> dataStructure
    ) {
        Builder builder = new Builder();
        builder.putAll(dataStructure);
        return builder;
    }

    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter, Map<String, Class<?>> types, Map<String, Component.Role> roles) {
        checkArgument(types.keySet().equals(roles.keySet()));
        Builder builder = builder();
        for (String name : types.keySet()) {
            builder.put(name, roles.get(name), types.get(name));
        }
        return builder.build();
    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Component.Role role1, Class<?> type1) {
        Builder builder = builder();
        builder.put(name1, role1, type1);
        return builder.build();
    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Component.Role role1, Class<?> type1,
                                   String name2, Component.Role role2, Class<?> type2) {
        return builder()
                .put(name1, role1, type1).put(name2, role2, type2)
                .build();

    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Component.Role role1, Class<?> type1,
                                   String name2, Component.Role role2, Class<?> type2,
                                   String name3, Component.Role role3, Class<?> type3) {
        return builder()
                .put(name1, role1, type1).put(name2, role2, type2).put(name3, role3, type3)
                .build();
    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Component.Role role1, Class<?> type1,
                                   String name2, Component.Role role2, Class<?> type2,
                                   String name3, Component.Role role3, Class<?> type3,
                                   String name4, Component.Role role4, Class<?> type4) {
        return builder()
                .put(name1, role1, type1).put(name2, role2, type2).put(name3, role3, type3).put(name4, role4, type4)
                .build();

    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Component.Role role1, Class<?> type1,
                                   String name2, Component.Role role2, Class<?> type2,
                                   String name3, Component.Role role3, Class<?> type3,
                                   String name4, Component.Role role4, Class<?> type4,
                                   String name5, Component.Role role5, Class<?> type5) {
        return builder()
                .put(name1, role1, type1).put(name2, role2, type2).put(name3, role3, type3).put(name4, role4, type4)
                .put(name5, role5, type5)
                .build();
    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Component.Role role1, Class<?> type1,
                                   String name2, Component.Role role2, Class<?> type2,
                                   String name3, Component.Role role3, Class<?> type3,
                                   String name4, Component.Role role4, Class<?> type4,
                                   String name5, Component.Role role5, Class<?> type5,
                                   String name6, Component.Role role6, Class<?> type6) {
        return builder()
                .put(name1, role1, type1).put(name2, role2, type2).put(name3, role3, type3).put(name4, role4, type4)
                .put(name5, role5, type5).put(name6, role6, type6)
                .build();
    }

    private static IdentityHashMap<Component, String> computeInverseCache(ImmutableMap<String, Component> delegate) {
        IdentityHashMap<Component, String> map = Maps.newIdentityHashMap();
        for (Entry<String, Component> entry : delegate.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        return map;
    }

    public String getName(Component component) {
        return this.inverseCache.get(component);
    }

    public Map<String, Component.Role> getRoles() {
        return this.roleCache;
    }

    public Map<String, Class<?>> getTypes() {
        return this.typeCache;
    }

    public BiFunction<Object, Class<?>, ?> converter() {
        return this.converter;
    }

    ;

    /**
     * Creates a new {@link Component} for the given column and value.
     *
     * @param name  the name of the column.
     * @param value the value of the resulting component.
     * @return a component
     */
    public DataPoint wrap(String name, Object value) {
        checkArgument(
                containsKey(name),
                "could not find %s in data structure %s",
                name, this
        );

        Component component = get(name);
        return new DataPoint(component) {
            @Override
            public Object get() {
                return converter().apply(value, component.getType());
            }
        };
    }

    /**
     * Creates a new {@link Dataset.Tuple} for the given names and values.
     * <p>
     * This method uses the {@link #wrap(String, Object)} method to convert each value and returns
     * a {@link Dataset.Tuple}.
     *
     * @param map a map of name and values
     * @return the corresponding tuple (row)
     */
    public Dataset.Tuple wrap(Map<String, Object> map) {

        List<DataPoint> components = Lists.newArrayList();
        for (Map.Entry<String, Object> entry : map.entrySet())
            components.add(wrap(entry.getKey(), entry.getValue()));

        return Dataset.Tuple.create(components);

    }

    @Override
    protected Map<String, Component> delegate() {
        return delegate;
    }

    public static class Builder {

        private final ImmutableMap.Builder<String, Component> builder = ImmutableMap.builder();
        private final BiFunction<Object, Class<?>, ?> converter;

        private Builder(BiFunction<Object, Class<?>, ?> converter) {
            this.converter = checkNotNull(converter);
        }

        private Builder() {
            this.converter = (o, aClass) -> o;
        }

        /**
         * Associates {@code key} with {@code value} in the built map. Duplicate
         * keys are not allowed, and will cause {@link #build} to fail.
         *
         * @param key
         * @param value
         */
        public Builder put(String key, Component value) {
            builder.put(key, value);
            return this;
        }

        public Builder put(String key, Component.Role role, Class<?> type) {
            return put(key, new Component(type, role, key));
        }

        /**
         * Adds the given {@code entry} to the map, making it immutable if
         * necessary. Duplicate keys are not allowed, and will cause {@link #build}
         * to fail.
         *
         * @param entry
         */
        public Builder put(Entry<? extends String, ? extends Component> entry) {
            builder.put(entry);
            return this;
        }

        /**
         * Associates all of the given map's keys and values in the built map.
         * Duplicate keys are not allowed, and will cause {@link #build} to fail.
         *
         * @param map
         * @throws NullPointerException if any key or value in {@code map} is null
         */
        public Builder putAll(Map<? extends String, ? extends Component> map) {
            builder.putAll(map);
            return this;
        }

        /**
         * Adds all of the given entries to the built map.  Duplicate keys are not
         * allowed, and will cause {@link #build} to fail.
         *
         * @param entries
         * @throws NullPointerException if any key, value, or entry is null
         */
        @Beta
        public Builder putAll(Iterable<? extends Entry<? extends String, ? extends Component>> entries) {
            builder.putAll(entries);
            return this;
        }

        /**
         * Configures this {@code Builder} to order entries by value according to the specified
         * comparator.
         * <p>
         * <p>The sort order is stable, that is, if two entries have values that compare
         * as equivalent, the entry that was inserted first will be first in the built map's
         * iteration order.
         *
         * @param valueComparator
         * @throws IllegalStateException if this method was already called
         */
        @Beta
        public Builder orderEntriesByValue(Comparator<? super Component> valueComparator) {
            builder.orderEntriesByValue(valueComparator);
            return this;
        }

        public DataStructure build() {
            return new DataStructure(this.converter, this.builder.build());
        }
    }
}
