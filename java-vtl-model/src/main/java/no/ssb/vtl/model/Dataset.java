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
import com.google.common.collect.ForwardingList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

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
 * The observations (data points) can be requested by calling the {@link #getData()} functions.
 * The returned value is a new, independent and immutable stream of information.
 * <p>
 * Sorting and filtering:
 * <p>
 * Independent sorting and filtering of the data points for a stream can be requested with the
 * {@link #getData(String...)} methods. This allows optimized implementations of operations like
 * join or union.
 */
public interface Dataset extends Streamable<Dataset.Tuple> {

    /**
     * Deprecated, we are moving toward a Map view of the tuples.
     */
    @Deprecated
    static Comparator<Tuple> comparatorFor(Component.Role... roles) {
        ImmutableSet<Component.Role> roleSet = Sets.immutableEnumSet(Arrays.asList(roles));
        return new Comparator<Tuple>() {
            @Override
            public int compare(Tuple li, Tuple ri) {
                Comparator comparator = Comparator.naturalOrder();

                Map<String, Object> lm = li.stream().filter(dataPoint -> roleSet.contains(dataPoint.getRole()))
                        .collect(Collectors.toMap(
                                DataPoint::getName,
                                DataPoint::get
                        ));

                Map<String, Object> rm = ri.stream().filter(dataPoint -> roleSet.contains(dataPoint.getRole()))
                        .collect(Collectors.toMap(
                                DataPoint::getName,
                                DataPoint::get
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
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * Implementations can decide not to allow sorting by returning {@link Optional#empty()}.
     * <p>
     * If supported, the {@link Spliterator} of the returned {@link Stream} <b>must</b> be {@link Spliterator#SORTED}
     * using the given {@link Ordering}.
     *
     * @param orders    the order in which the {@link DataPoint}s should be returned.
     * @param filtering the filtering on the {@link Component}s of the {@link DataPoint}s
     * @return a <b>sorted</b> stream of {@link DataPoint}s if sorting is supported.
     */
    Optional<Stream<? extends DataPoint>> getData(Ordering orders, Filtering filtering);

    /**
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * Calling this method is the same as calling getData(ordering, Filtering.ALL)
     *
     * @see Filtering#getData(Ordering, Filtering)
     */
    default Optional<Stream<? extends DataPoint>> getData(Ordering ordering) {
        return getData(ordering, Filtering.ALL);
    }

    /**
     * Creates a new independent, immutable stream of DataPoints.
     * <p>
     * Calling this method is the same as calling getData(Ordering.DEFAULT, filtering)
     *
     * @see Filtering#getData(Ordering, Filtering)
     */
    default Optional<Stream<? extends DataPoint>> getData(Filtering filtering) {
        return getData(Ordering.DEFAULT, filtering);
    }

    /**
     * Returns the data structure of the DataSet.
     */
    DataStructure getDataStructure();

    enum Order {
        ASC, DESC
    }

    /**
     * Represent the ordering the {@link DataPoint}s in a Dataset.
     */
    interface Ordering extends Comparator<DataPoint>, Map<String, Order> {
        Ordering DEFAULT = null;
    }

    /**
     * Represent the filtering of the {@link Component}s in a Dataset.
     */
    interface Filtering extends List<String> {
        Filtering ALL = null;
    }

    interface Tuple extends List<DataPoint>, Comparable<Tuple> {

        static Tuple create(List<DataPoint> components) {
            return new AbstractTuple() {
                @Override
                protected List<DataPoint> delegate() {
                    return components;
                }
            };
        }

        List<DataPoint> ids();

        List<DataPoint> values();

    }

    abstract class AbstractTuple extends ForwardingList<DataPoint> implements Tuple {

        @Override
        public List<DataPoint> ids() {
            return stream()
                    .filter(dataPoint -> dataPoint.getComponent().getRole().equals(Component.Role.IDENTIFIER))
                    .collect(Collectors.toList());
        }

        @Override
        public List<DataPoint> values() {
            return stream()
                    .filter(dataPoint -> !dataPoint.getComponent().getRole().equals(Component.Role.IDENTIFIER))
                    .collect(Collectors.toList());
        }

        @Override
        public int compareTo(Tuple o) {
            checkNotNull(o);

            Comparator comparator = Comparator.naturalOrder();
            Iterator<DataPoint> li = this.ids().iterator();
            Iterator<DataPoint> ri = o.ids().iterator();
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
            return MoreObjects.toStringHelper(Tuple.class)
                    .add("id", ids())
                    .add("values", values())
                    .toString();
        }

    }

}
