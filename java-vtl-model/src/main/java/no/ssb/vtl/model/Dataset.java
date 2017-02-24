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

import com.codepoetics.protonpack.Streamable;
import com.google.common.base.MoreObjects;
import com.google.common.base.Predicate;
import com.google.common.collect.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static no.ssb.vtl.model.Dataset.Order.Direction.ASC;

/**
 * A dataset represents a list of observations.
 * <p>
 * It can be thought as a table where the columns have roles and types:
 * <pre>
 *        +---------------------------------------
 *        | Components                           |
 * +------+------------+------------+------------+
 * |Name  | Country    | Population | Updated    |
 * |Type  | String     | Integer    | Date       |
 * |Role  | Identifier | Measure    | Attribute  |
 * +------+------------+------------+------------+---+
 *        | France     | 123654     | 2015-01-01 | P |
 *        +------------+------------+------------+ o |
 *        | Germany    | 456321     | 2016-01-15 | i |
 *        +------------+------------+------------+ n |
 *        | Spain      | 456123     | 2011-09-15 | t |
 *        +------------+------------+------------+ s |
 *        | Italy      | 654123     | 2017-01-01 |   |
 *        +------------+------------+------------+---+
 * </pre>
 * <p>
 * Data Strucure:
 * <p>
 * The data structure of a dataset is
 * <p>
 * Data stream:
 * <p>
 * The observations (data points) can be requested by calling the {@link #getData()} functions. The returned value is a
 * new, independent and immutable stream of information.
 * <p>
 * Sorting and filtering:
 * <p>
 * Independent sorting and filtering of the data points for a stream can be requested with the
 * {@link Dataset#getData(Order, Filtering, Set)} methods. This allows optimized implementations of operations like
 * join or union.
 */
public interface Dataset extends Streamable<Dataset.DataPoint> {

    /**
     * Deprecated, we are moving toward a Map view of the tuples.
     */
    @Deprecated
    static Comparator<DataPoint> comparatorFor(Component.Role... roles) {
        ImmutableSet<Component.Role> roleSet = Sets.immutableEnumSet(Arrays.asList(roles));
        return new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint li, DataPoint ri) {
                Comparator comparator = Comparator.naturalOrder();

                Map<String, Object> lm = li.stream().filter(dataPoint -> roleSet.contains(dataPoint.getRole()))
                        .collect(Collectors.toMap(
                                VTLObject::getName,
                                VTLObject::get
                        ));

                Map<String, Object> rm = ri.stream().filter(dataPoint -> roleSet.contains(dataPoint.getRole()))
                        .collect(Collectors.toMap(
                                VTLObject::getName,
                                VTLObject::get
                        ));

                checkArgument(lm.keySet().equals(rm.keySet()));
                int i = 0;
                for (String key : lm.keySet()) {
                    i = comparator.compare(lm.get(key), rm.get(key));
                    if (i != 0)
                        return i;
                }
                return i;

            }
        };
    }

    /**
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * The {@link Spliterator} of the returned {@link Stream} <b>must</b> report the following characteristics:
     * <ul>
     * <li>{@link Spliterator#ORDERED}</li>
     * <li>{@link Spliterator#IMMUTABLE}</li>
     * <li>{@link Spliterator#DISTINCT}</li>
     * <li>{@link Spliterator#NONNULL}</li>
     * </ul>
     * <p>
     * Other characteristics are left at the discretion of the implementers.
     */
    Stream<? extends DataPoint> getData();

    /**
     * Returns the count of unique values by column.
     */
    Optional<Map<String, Integer>> getDistinctValuesCount();

    /**
     * Return the amount of {@link DataPoint} the stream obtained by the
     * method {@link Dataset#getData()} will return.
     */
    Optional<Long> getSize();

    /**
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * Implementations can decide not to allow sorting by returning {@link Optional#empty()}.
     * <p>
     * If supported, the {@link Spliterator} of the returned {@link Stream} <b>must</b> be {@link Spliterator#SORTED}
     * using the given {@link Order}.
     *
     * @param orders    the order in which the {@link DataPoint}s should be returned.
     * @param filtering the filtering on the {@link Component}s of the {@link DataPoint}s
     * @return a <b>sorted</b> stream of {@link DataPoint}s if sorting is supported.
     */
    default Optional<Stream<? extends DataPoint>> getData(Order orders, Filtering filtering, Set<String> components) {
        return Optional.of(getData().sorted(orders).filter(filtering).map(o -> {
            // TODO
            return o;
        }));
    }

    /**
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * Calling this method is equivalent to
     * <code>getData(ordering, Filtering.ALL, getDataStructure.keySet())</code>
     *
     * @see Filtering#getData(Order, Filtering, Set)
     */
    default Optional<Stream<? extends DataPoint>> getData(Order order) {
        DataStructure dataStructure = getDataStructure();
        return getData(order, Filtering.ALL, dataStructure.keySet());
    }

    /**
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * Calling this method is equivalent to
     * <code>getData(Ordering.DEFAULT, filtering, getDataStructure.keySet())</code>
     *
     * @see Filtering#getData(Order, Filtering, Set)
     */
    default Optional<Stream<? extends DataPoint>> getData(Filtering filtering) {
        DataStructure dataStructure = getDataStructure();
        return getData(Order.getDefault(dataStructure), filtering, dataStructure.keySet());
    }

    /**
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * Calling this method is equivalent to
     * <code>getData(Ordering.DEFAULT, filtering, getDataStructure.keySet())</code>
     *
     * @see Filtering#getData(Order, Filtering, Set)
     */
    default Optional<Stream<? extends DataPoint>> getData(Set<String> components) {
        DataStructure dataStructure = getDataStructure();
        return getData(Order.getDefault(dataStructure), Filtering.ALL, dataStructure.keySet());
    }

    /**
     * Returns the data structure of the DataSet.
     */
    DataStructure getDataStructure();

    /**
     * Represent the filtering of the {@link DataPoint}s in a Dataset.
     */
    interface Filtering extends Predicate<DataPoint> {
        Filtering ALL = dataPoint -> true;
    }

    interface DataPoint extends List<VTLObject>, Comparable<DataPoint> {

        static DataPoint create(List<VTLObject> components) {
            return new AbstractDataPoint() {
                @Override
                protected List<VTLObject> delegate() {
                    return components;
                }
            };
        }

        List<VTLObject> ids();

        List<VTLObject> values();

    }

    /**
     * Represent the ordering the {@link DataPoint}s in a Dataset.
     */
    final class Order extends ForwardingMap<String, Order.Direction> implements Comparator<DataPoint>, Map<String, Order.Direction> {

        public static final Comparator<Comparable> NULLS_FIRST = Comparator.<Comparable>nullsFirst(Comparator.naturalOrder());
        static final Comparator<Map.Entry<String, Component>> BY_ROLE = Comparator.comparing(
                entry -> entry.getValue().getRole(),
                Ordering.explicit(
                        Component.Role.IDENTIFIER,
                        Component.Role.MEASURE,
                        Component.Role.ATTRIBUTE
                )
        );
        static final Comparator<Map.Entry<String, Component>> BY_NAME = Comparator.comparing(Map.Entry::getKey);
        private final ImmutableMap<String, Direction> delegate;

        private Order(Map<String, Direction> orders) {
            this.delegate = ImmutableMap.copyOf(orders);
        }

        /**
         * Return a default Order for the given datastructure.
         *
         * @param structure
         * @return
         */
        static Order getDefault(DataStructure structure) {
            Set<Entry<String, Component>> sortedEntrySet = Sets.newTreeSet(BY_ROLE.thenComparing(BY_NAME));
            sortedEntrySet.addAll(structure.entrySet());

            ImmutableMap.Builder<String, Order.Direction> order = ImmutableMap.builder();
            for (Entry<String, Component> entry : sortedEntrySet) {
                order.put(entry.getKey(), ASC);
            }
            return new Order(order.build());
        }

        @Override
        protected Map<String, Direction> delegate() {
            return delegate;
        }

        @Override
        public int compare(DataPoint o1, DataPoint o2) {
            int result;

            // TODO dataStructure.asMap(o1) ?
            Map<String, Comparable> m1 = Maps.newHashMap(), m2 = Maps.newHashMap();
            for (Entry<String, Direction> order : delegate.entrySet()) {
                String key = order.getKey();
                result = NULLS_FIRST.compare(m1.get(key), m2.get(key));
                if (result != 0) {
                    return order.getValue() == ASC ? result : -result;
                }
            }

            // TODO build an index?
            Comparable[] c1 = new Comparable[1], c2 = new Comparable[1];
            ImmutableMap<Integer, Order.Direction> index = ImmutableMap.copyOf(Collections.emptyMap());
            for (Entry<Integer, Direction> order : index.entrySet()) {
                Integer i = order.getKey();
                result = NULLS_FIRST.compare(c1[i], c2[i]);
                if (result != 0) {
                    return order.getValue() == ASC ? result : -result;
                }
            }
            return 0;
        }

        enum Direction {
            ASC, DESC
        }
    }

    abstract class AbstractDataPoint extends ForwardingList<VTLObject> implements DataPoint {

        @Override
        public List<VTLObject> ids() {
            return stream()
                    .filter(dataPoint -> dataPoint.getComponent().getRole().equals(Component.Role.IDENTIFIER))
                    .collect(Collectors.toList());
        }

        @Override
        public List<VTLObject> values() {
            return stream()
                    .filter(dataPoint -> !dataPoint.getComponent().getRole().equals(Component.Role.IDENTIFIER))
                    .collect(Collectors.toList());
        }

        @Override
        public int compareTo(DataPoint o) {
            checkNotNull(o);

            Comparator comparator = Comparator.naturalOrder();
            Iterator<VTLObject> li = this.ids().iterator();
            Iterator<VTLObject> ri = o.ids().iterator();
            int i = 0;
            while (li.hasNext() && ri.hasNext()) {
                i = comparator.compare(li.next().get(), ri.next().get());
                if (i != 0)
                    return i;
            }
            return i;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(DataPoint.class)
                    .add("id", ids())
                    .add("values", values())
                    .toString();
        }

    }

}
