package no.ssb.vtl.script.operations.aggregation;

/*
 * -
 *  * ========================LICENSE_START=================================
 * * Java VTL
 *  *
 * %%
 * Copyright (C) 2017 Hadrien Kohl
 *  *
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
 *
 */

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import com.google.common.math.IntMath;
import com.sun.istack.internal.Nullable;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.model.VtlFiltering;
import no.ssb.vtl.model.VtlOrdering;
import no.ssb.vtl.script.functions.AggregationSumFunction;
import no.ssb.vtl.script.support.DatasetCloseWatcher;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractSet;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.stream.Collectors.toList;
import static no.ssb.vtl.model.Component.Role;
import static no.ssb.vtl.model.Ordering.Direction.ASC;
import static no.ssb.vtl.model.Ordering.Direction.DESC;
import static org.assertj.core.api.Assertions.assertThat;

public class AggregationOperationTest {

    private DatasetCloseWatcher dataset;

    public static <E> Set<Set<E>> combinations(Set<E> set, final int size) {
        final ImmutableMap<E, Integer> index = indexMap(set);
        checkArgument(size > 0, "size");
        checkArgument(size <= index.size(), "size (%s) must be <= set.size() (%s)", size, index.size());
        if (size == 0) {
            return ImmutableSet.<Set<E>>of(ImmutableSet.<E>of());
        } else if (size == index.size()) {
            return ImmutableSet.<Set<E>>of(index.keySet());
        }
        return new AbstractSet<Set<E>>() {
            @Override
            public boolean contains(@Nullable Object o) {
                if (o instanceof Set) {
                    Set<?> s = (Set<?>) o;
                    return s.size() == size && index.keySet().containsAll(s);
                }
                return false;
            }

            @Override
            public Iterator<Set<E>> iterator() {
                return new AbstractIterator<Set<E>>() {
                    final BitSet bits = new BitSet(index.size());

                    @Override
                    protected Set<E> computeNext() {
                        if (bits.isEmpty()) {
                            bits.set(0, size);
                        } else {
                            int firstSetBit = bits.nextSetBit(0);
                            int bitToFlip = bits.nextClearBit(firstSetBit);

                            if (bitToFlip == index.size()) {
                                return endOfData();
                            }

                            /*
                             * The current set in sorted order looks like
                             * {firstSetBit, firstSetBit + 1, ..., bitToFlip - 1, ...}
                             * where it does *not* contain bitToFlip.
                             *
                             * The next combination is
                             *
                             * {0, 1, ..., bitToFlip - firstSetBit - 2, bitToFlip, ...}
                             *
                             * This is lexicographically next if you look at the combinations in descending order
                             * e.g. {2, 1, 0}, {3, 1, 0}, {3, 2, 0}, {3, 2, 1}, {4, 1, 0}...
                             */

                            bits.set(0, bitToFlip - firstSetBit - 1);
                            bits.clear(bitToFlip - firstSetBit - 1, bitToFlip);
                            bits.set(bitToFlip);
                        }
                        final BitSet copy = (BitSet) bits.clone();
                        return new AbstractSet<E>() {
                            @Override
                            public boolean contains(@Nullable Object o) {
                                Integer i = index.get(o);
                                return i != null && copy.get(i);
                            }

                            @Override
                            public Iterator<E> iterator() {
                                return new AbstractIterator<E>() {
                                    int i = -1;

                                    @Override
                                    protected E computeNext() {
                                        i = copy.nextSetBit(i + 1);
                                        if (i == -1) {
                                            return endOfData();
                                        }
                                        return index.keySet().asList().get(i);
                                    }
                                };
                            }

                            @Override
                            public int size() {
                                return size;
                            }
                        };
                    }
                };
            }

            @Override
            public int size() {
                return IntMath.binomial(index.size(), size);
            }

            @Override
            public String toString() {
                return "Sets.combinations(" + index.keySet() + ", " + size + ")";
            }
        };
    }

    /**
     * Returns a map from the ith element of list to i.
     */
    static <E> ImmutableMap<E, Integer> indexMap(Collection<E> list) {

        ImmutableMap.Builder<E, Integer> builder = ImmutableMap.builder();
        int i = 0;
        for (E e : list) {
            builder.put(e, i++);
        }
        return builder.build();
    }

    @Before
    public void setUp() {

        StaticDataset staticDataset = StaticDataset.create()
                .addComponent("id1", Role.IDENTIFIER, String.class)
                .addComponent("id2", Role.IDENTIFIER, String.class)
                .addComponent("m1", Role.IDENTIFIER, Long.class)

                .addPoints("a", "1", 1L)
                .addPoints("a", "2", 2L)
                .addPoints("a", "3", 4L)
                .addPoints("a", "4", 8L)

                .addPoints("b", "1", 1L)
                .addPoints("b", "2", 2L)
                .addPoints("b", "3", 4L)
                .addPoints("b", "4", 8L)

                .addPoints("c", "1", 1L)
                .addPoints("c", "2", 2L)
                .addPoints("c", "3", 4L)
                .addPoints("c", "4", 8L)

                .build();

        this.dataset = DatasetCloseWatcher.wrap(staticDataset);
    }

    @Test
    public void testClosesStreams() {

        DataStructure structure = this.dataset.getDataStructure();
        AggregationOperation aggregationOperation = new AggregationOperation(
                this.dataset,
                ImmutableList.of(structure.get("id1")),
                ImmutableList.of(structure.get("m1")),
                new AggregationSumFunction()
        );

        try (Stream<DataPoint> data = aggregationOperation.getData()) {
            assertThat(data).containsExactly(
                    DataPoint.create("a", 15),
                    DataPoint.create("b", 15),
                    DataPoint.create("c", 15)
            );
        } finally {
            assertThat(dataset.allStreamWereClosed()).isTrue();
        }

    }

    @Test
    public void testFilters() {

        DataStructure structure = this.dataset.getDataStructure();
        AggregationOperation op = new AggregationOperation(
                this.dataset,
                ImmutableList.of(structure.get("id1")),
                ImmutableList.of(structure.get("m1")),
                new AggregationSumFunction()
        );

        VtlFiltering filterOnId1 = VtlFiltering.using(op).with(
                VtlFiltering.eq("id1", "a")
        );
        assertThat(op.computeRequiredFiltering(filterOnId1).toString())
                .isEqualTo("id1=a");

        VtlFiltering filterOnId2 = VtlFiltering.using(op).with(
                VtlFiltering.eq("id2", "1")
        );
        assertThat(op.computeRequiredFiltering(filterOnId2).toString())
                .isEqualTo("TRUE");

        VtlFiltering filterOnId1AndId2 = VtlFiltering.and(
                filterOnId1, filterOnId2
        );
        assertThat(op.computeRequiredFiltering(filterOnId1AndId2).toString())
                .isEqualTo("(TRUE&id1=a)");

        VtlFiltering filterOnM1 = VtlFiltering.using(op).with(
                VtlFiltering.le("m1", 1L)
        );
        assertThat(op.computeRequiredFiltering(filterOnM1).toString())
                .isEqualTo("TRUE");

        VtlFiltering filterOnId1AndId2AndM1 = VtlFiltering.and(
                filterOnId1, filterOnId2, filterOnM1
        );
        assertThat(op.computeRequiredFiltering(filterOnId1AndId2AndM1).toString())
                .isEqualTo("(id1=a&TRUE&TRUE)");

        VtlFiltering childFilter = (VtlFiltering) op.computeRequiredFiltering(filterOnId1);
        assertThat(op.getData().filter(childFilter)).containsExactly(
                DataPoint.create("a", 15)
        );

    }

    @Test
    public void testOrders() {

        // Need a couple more columns to test ordering.
        StaticDataset dataset = StaticDataset.create()
                .addComponent("id1", Role.IDENTIFIER, String.class)
                .addComponent("id3", Role.IDENTIFIER, String.class)
                .addComponent("id2", Role.IDENTIFIER, String.class)
                .addComponent("id4", Role.IDENTIFIER, String.class)
                .addComponent("m1", Role.IDENTIFIER, Long.class)
                .build();

        DataStructure structure = dataset.getDataStructure();
        AggregationOperation op = new AggregationOperation(
                dataset,
                ImmutableList.of(
                        structure.get("id1"),
                        structure.get("id2"),
                        structure.get("id3")
                ),
                ImmutableList.of(structure.get("m1")),
                new AggregationSumFunction()
        );

        assertThat(op.computeRequiredOrdering(VtlOrdering.using(op).desc("id1").build()).toString())
                .isEqualTo("VtlOrdering{id1=DESC, id3=ASC, id2=ASC}");

        assertThat(op.computeRequiredOrdering(VtlOrdering.using(op).desc("id3").desc("id1").build()).toString())
                .isEqualTo("VtlOrdering{id3=DESC, id1=DESC, id2=ASC}");

    }

    @Test
    public void testAggregation() {
        // Test all the combinations of ordering.
        StaticDataset staticDataset = StaticDataset.create()
                .addComponent("id1", Role.IDENTIFIER, String.class)
                .addComponent("id2", Role.IDENTIFIER, String.class)
                .addComponent("id3", Role.IDENTIFIER, String.class)
                .addComponent("id4", Role.IDENTIFIER, String.class)
                .addComponent("m1", Role.MEASURE, Long.class)
                .addComponent("m2", Role.MEASURE, Long.class)

                .addPoints("a", "1", "a", "l", 1L, 8L)
                .addPoints("a", "2", "b", "k", 2L, 4L)
                .addPoints("a", "3", "c", "j", 4L, 2L)
                .addPoints("a", "4", "d", "i", 8L, 1L)

                .addPoints("b", "1", "e", "h", 1L, 8L)
                .addPoints("b", "2", "f", "g", 2L, 4L)
                .addPoints("b", "3", "g", "f", 4L, 2L)
                .addPoints("b", "4", "h", "e", 8L, 1L)

                .addPoints("c", "1", "i", "d", 1L, 8L)
                .addPoints("c", "2", "j", "c", 2L, 4L)
                .addPoints("c", "3", "k", "b", 4L, 2L)
                .addPoints("c", "4", "l", "a", 8L, 1L)

                .build();

        DataStructure structure = staticDataset.getDataStructure();
        AggregationOperation groupById1 = new AggregationOperation(
                staticDataset,
                Arrays.asList(structure.get("id1")),
                Arrays.asList(structure.get("m1"), structure.get("m2")),
                new AggregationSumFunction()
        );

        AggregationOperation groupById2 = new AggregationOperation(
                staticDataset,
                Arrays.asList(structure.get("id2")),
                Arrays.asList(structure.get("m1"), structure.get("m2")),
                new AggregationSumFunction()
        );

        // TODO: Extract this and use it in all operations.
        // Iterator<VtlOrdering> VtlOrdering.combinations(DataStructure ds)
        Set<String> columns = groupById1.getDataStructure().keySet();
        List<ImmutableSet<Ordering.Direction>> directionCombinations = Collections.nCopies(columns.size(), ImmutableSet.of(ASC, DESC));
        for (List<String> permutation : Collections2.permutations(columns)) {
            for (List<Ordering.Direction> directions : Sets.cartesianProduct(directionCombinations)) {
                VtlOrdering.Builder builder = VtlOrdering.using(groupById1);
                Iterator<String> columnIterator = permutation.iterator();
                Iterator<Ordering.Direction> directionIterator = directions.iterator();
                while (columnIterator.hasNext() && directionIterator.hasNext()) {
                    builder.then(directionIterator.next(), columnIterator.next());
                }
                VtlOrdering ordering = builder.build();
                List<DataPoint> data = groupById1.computeData(ordering, Filtering.ALL, columns).collect(toList());
                assertThat(data).as("data grouped by id2 with ordering %s", ordering)
                        .containsExactlyInAnyOrder(
                                DataPoint.create("a", 15L, 15L),
                                DataPoint.create("b", 15L, 15L),
                                DataPoint.create("c", 15L, 15L)
                        ).isSortedAccordingTo(ordering);
            }
        }

        this.dataset = DatasetCloseWatcher.wrap(staticDataset);
    }
}
