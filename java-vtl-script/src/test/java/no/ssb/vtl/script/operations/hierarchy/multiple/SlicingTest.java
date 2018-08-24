package no.ssb.vtl.script.operations.hierarchy.multiple;

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

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.PeekingIterator;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.model.VTLObject;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.Role.ATTRIBUTE;
import static no.ssb.vtl.model.Component.Role.IDENTIFIER;
import static no.ssb.vtl.model.Component.Role.MEASURE;

public class SlicingTest {

    private Dataset left;
    private Dataset right;

    @Before
    public void setUp() throws Exception {

        left = StaticDataset.create()
                .addComponent("id1", IDENTIFIER, String.class)
                .addComponent("id2", IDENTIFIER, String.class)
                .addComponent("m1", MEASURE, Long.class)
                .addComponent("a1", ATTRIBUTE, String.class)

                .addPoints("1", "A", 1L, "1-A")
                .addPoints("1", "B", 2L, "1-B")
                .addPoints("1", "C", 4L, "1-C")
                .addPoints("2", "A", 8L, "2-A")
                .addPoints("2", "B", 16L, "2-B")
                .addPoints("2", "C", 32L, "2-C")
                .addPoints("3", "A", 64L, "3-A")
                .addPoints("3", "B", 128L, "3-B")
                .addPoints("3", "C", 256L, "3-C")
                .build();

        right = StaticDataset.create()
                .addComponent("id1", IDENTIFIER, String.class)
                .addComponent("id2", IDENTIFIER, String.class)
                .addComponent("m1", MEASURE, Long.class)
                .addComponent("a1", ATTRIBUTE, String.class)

                .addPoints("2", "E", 1L, "1-A")
                .addPoints("2", "F", 2L, "1-B")
                .addPoints("2", "G", 4L, "1-C")
                .addPoints("3", "E", 8L, "2-A")
                .addPoints("3", "F", 16L, "2-B")
                .addPoints("3", "G", 32L, "2-C")
                .addPoints("4", "E", 64L, "3-A")
                .addPoints("4", "F", 128L, "3-B")
                .addPoints("4", "G", 256L, "3-C")

                .build();
    }

    @Test
    public void simpleTest() {

        /*
         * a: BBBCCCDD
         * b: ---CC-DDDEEE--
         * c: AA----DD-EE-FF
         * r:    CCCDD----
         *       CC-DDDEEE
         *       ---DD-EE-
         */
        List<String> a = Arrays.asList(
                "B", "B", "B", "C", "C", "C", "D", "D"
        );

        Iterator<Iterator<String>> slice = slice(a.iterator(), String::compareTo);
        while (slice.hasNext()) {
            Iterator<String> next = slice.next();
            while (next.hasNext()) {
                System.out.println(next.next());
            }
        }

        List<String> b = Arrays.asList(
                "C", "C", "D", "D", "D", "E", "E", "E"
        );

        List<String> c = Arrays.asList(
                "A", "A", "D", "D", "E", "E", "F", "F"
        );

        List<String> empty = Collections.emptyList();

        List<Iterator<String>> sync = sync(Arrays.asList(
                slice(a.iterator(), String::compareTo),
                slice(b.iterator(), String::compareTo),
                slice(c.iterator(), String::compareTo),
                slice(empty.iterator(), String::compareTo)
        ), String::compareTo);
        boolean done = false;
        while (!done) {
            done = true;
            for (Iterator<String> iterator : sync) {
                if (iterator.hasNext()) {
                    done = false;
                    System.out.println(iterator.next());
                }
            }
        }

    }

    private <T> Stream<T> sync(
            Map<String, Stream<T>> sources,
            Function<Map<String, Stream<T>>,
                    Stream<T>> merger,
            Comparator<T> comparator
    ) {
        Map<String, Iterator<Iterator<String>>> map = new HashMap<>();
        for (String name : sources.keySet()) {
            Spliterator<T> spliterator = sources.get(name).spliterator();
            Iterator<T> iterator = Spliterators.iterator(spliterator);
            PeekingIterator<T> peekingIterator = Iterators.peekingIterator(iterator);
            if (peekingIterator.hasNext()) {

            } else {
                //map.put(name, peekingIterator);
            }
        }
        return null;
    }

    private <T> List<Iterator<T>> sync(List<Iterator<Iterator<T>>> sources, Comparator<T> comparator) {
        List<Iterator<T>> result = Lists.newArrayList();
        T max = null;
        for (Iterator<Iterator<T>> source : sources) {
            if (source.hasNext()) {
                PeekingIterator<T> peekingIterator = Iterators.peekingIterator(source.next());
                if (max == null) {
                    max = peekingIterator.peek();
                } else {
                    if (comparator.compare(peekingIterator.peek(), max) > 0) {
                        max = peekingIterator.peek();
                    }
                }

                //result.add(new AbstractIterator<T>() {
//
                //    PeekingIterator<T> current = peekingIterator;
//
                //    @Override
                //    protected T computeNext() {
//
                //        while (current.hasNext()) {
                //            if (comparator.compare(current.peek(), max) == 0) {
                //                return current.next();
                //            } else {
                //                current.next();
                //            }
                //        }
                //        if (!peekingIterator.hasNext()) {
//
                //        }
                //        return null;
                //    }
                //});
            }
        }
        return result;
    }

    /**
     * Using a comparator, return a new iterator for each value change.
     */
    private <T> Iterator<Iterator<T>> slice(Iterator<T> iterator, Comparator<T> comparator) {
        PeekingIterator<T> source = Iterators.peekingIterator(iterator);
        return new AbstractIterator<Iterator<T>>() {

            @Override
            protected Iterator<T> computeNext() {
                if (source.hasNext()) {
                    T last = source.peek();
                    // TODO: ensure usage of returned iterator.
                    return new AbstractIterator<T>() {
                        @Override
                        protected T computeNext() {
                            if (source.hasNext() && comparator.compare(last, source.peek()) == 0) {
                                return source.next();
                            } else {
                                return endOfData();
                            }
                        }
                    };
                } else {
                    return endOfData();
                }
            }
        };
    }

    @Test
    public void pocTest() {


        DataPointMap leftView = new DataPointMap(left.getDataStructure().keySet());
        DataPointMap rightView = new DataPointMap(right.getDataStructure().keySet());

        PeekingIterator<DataPointMap> leftIterator = Iterators.peekingIterator(
                Iterators.transform(
                        left.getData().iterator(),
                        leftView::setDataPoint
                )
        );

        PeekingIterator<DataPointMap> rightIterator = Iterators.peekingIterator(
                Iterators.transform(
                        left.getData().iterator(),
                        leftView::setDataPoint
                )
        );


        left.getData().map(leftView::setDataPoint).iterator();

        /*
         * Given left and right datasets. Repeating on t1:
         */

        Dataset result = doThing(
                ImmutableMap.of("left", left, "right", right),
                Sets.newHashSet("id1"),
                (slice, datasets) -> {
                    // Repeat.
                    Dataset bla = null;
                    return bla;
                });
    }

    private Dataset doThing(Map<String, Dataset> datasets, Set<String> slice, BiFunction<Map<String, Dataset>, Set<VTLObject>, Dataset> handler) {
        // Each stream is connected to the underlying stream and a shared "slice"
        return null;

    }

    private final class DataPointMap extends ForwardingMap<String, VTLObject> {

        private final Map<String, VTLObject> mapView;
        private DataPoint dataPoint;

        private DataPointMap(ImmutableSet<String> set) {
            ImmutableList<String> columnNames = set.asList();
            ImmutableMap.Builder<String, Integer> indicesBuilder = ImmutableMap.builder();
            Integer i = 0;
            for (String columnName : columnNames) {
                indicesBuilder.put(columnName, i++);
            }
            ImmutableMap<String, Integer> indices = indicesBuilder.build();
            mapView = Maps.asMap(set, column -> dataPoint.get(indices.get(column)));
        }

        private DataPointMap(Set<String> set) {
            this(ImmutableSet.copyOf(set));
        }

        public final DataPointMap setDataPoint(DataPoint point) {
            this.dataPoint = point;
            return this;
        }

        @Override
        protected final Map<String, VTLObject> delegate() {
            return this.mapView;
        }
    }

    private class TransformIterator<T, R> extends AbstractIterator<R> {

        private final Function<T, R> transformer;
        private final Iterator<T> source;

        private TransformIterator(Iterator<T> source, Function<T, R> transformer) {
            this.transformer = transformer;
            this.source = source;
        }

        @Override
        protected R computeNext() {
            if (source.hasNext()) {
                return transformer.apply(source.next());
            } else {
                return endOfData();
            }
        }
    }

    private class WindowIterator<T> extends AbstractIterator<T> {

        private final Predicate<T> fromPredicate;
        private final Predicate<T> toPredicate;
        private final PeekingIterator<T> source;

        private WindowIterator(Iterator<T> source, Predicate<T> fromPredicate, Predicate<T> toPredicate) {
            this.source = Iterators.peekingIterator(source);
            this.fromPredicate = fromPredicate;
            this.toPredicate = toPredicate;
        }

        @Override
        protected T computeNext() {
            while (source.hasNext() && fromPredicate.test(peek())) {
                source.next();
            }
            return null;
        }
    }

    /**
     * Return an iterator that iterates on source as long as predicate.test(source.peek()) is true.
     *
     * @param <T>
     */
    private class PredicateIterator<T> extends AbstractIterator<T> {

        private final PeekingIterator<T> source;
        private final Predicate<T> predicate;

        private PredicateIterator(Iterator<T> source, Predicate<T> predicate) {
            this.source = Iterators.peekingIterator(source);
            this.predicate = predicate;
        }

        @Override
        protected T computeNext() {
            if (source.hasNext() && predicate.test(source.peek())) {
                return source.next();
            } else {
                return endOfData();
            }
        }
    }
}
