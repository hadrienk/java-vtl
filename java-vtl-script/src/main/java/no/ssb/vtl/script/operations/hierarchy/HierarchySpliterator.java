package no.ssb.vtl.script.operations.hierarchy;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;
import com.google.common.collect.PeekingIterator;
import com.google.common.graph.ImmutableValueGraph;
import com.google.common.graph.ValueGraph;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A Spliterator that accumulates the values required to compute
 */
public class HierarchySpliterator implements Spliterator<DataPoint> {

    private final ImmutableValueGraph<VTLObject, Composition> hierarchy;
    private final Comparator<DataPoint> sliceComparator;
    private final SlicingIterator slicingIterator;

    /**
     * Iterator that stops when predicate returns false with the peek() value of source.
     */
    private static class BreakerIterator extends AbstractIterator<DataPoint> {

        private final Predicate<DataPoint> breaker;
        private final PeekingIterator<DataPoint> source;

        private BreakerIterator(PeekingIterator<DataPoint> source, Predicate<DataPoint> breaker) {
            this.breaker = checkNotNull(breaker);
            this.source = checkNotNull(source);
        }

        @Override
        protected DataPoint computeNext() {
            if (source.hasNext() && breaker.test(source.peek())) {
                return source.next();
            } else {
                return endOfData();
            }
        }
    }

    /**
     * Iterator that stops as soon as predicate returns false.
     */
    private static class SlicingIterator extends AbstractIterator<Iterator<DataPoint>> {

        private final Comparator<DataPoint> comparator;
        private final PeekingIterator<DataPoint> source;

        private SlicingIterator(Iterator<DataPoint> source, Comparator<DataPoint> comparator) {
            this.comparator = checkNotNull(comparator);
            this.source = Iterators.peekingIterator(checkNotNull(source));
        }

        @Override
        protected Iterator<DataPoint> computeNext() {
            if (source.hasNext()) {
                DataPoint first = source.peek();
                return new BreakerIterator(source, dataPoint -> comparator.compare(first, dataPoint) == 0);
            } else {
                return endOfData();
            }
        }
    }

    public HierarchySpliterator(Spliterator<DataPoint> source,
                                Comparator<DataPoint> slicer,
                                ValueGraph<VTLObject, Composition> hierarchy
    ) {
        this.sliceComparator = Objects.requireNonNull(slicer);
        //if (!this.sliceComparator.equals(source.getComparator())) {
        //    throw new IllegalArgumentException();
        //}
        this.hierarchy = ImmutableValueGraph.copyOf(Objects.requireNonNull(hierarchy));
        slicingIterator = new SlicingIterator(Spliterators.iterator(source), slicer);

    }

    @Override
    public boolean tryAdvance(Consumer<? super DataPoint> action) {
        if (!slicingIterator.hasNext()) {
            return false;
        }

        Iterator<DataPoint> next = slicingIterator.next();

        HierarchyAccumulator<VTLObject> accumulator = HierarchyAccumulator.sumAccumulatorFor(Long.class);

        Map<VTLObject, VTLObject> map = Maps.newHashMap();

        for (VTLObject successor : hierarchy.nodes()) {
            map.put(successor, new VTLObject() {

                @Override
                public String toString() {
                    StringBuilder builder = new StringBuilder("(");
                    for (VTLObject predecessor : hierarchy.predecessors(successor)) {
                        Composition composition = hierarchy.edgeValue(predecessor, successor);
                        switch (composition) {
                            case COMPLEMENT:
                                builder.append("-");
                                break;
                            case UNION:
                                builder.append("+");
                                break;
                        }
                        builder.append(map.get(predecessor).toString());
                    }
                    return builder.append(")").toString();
                }

                @Override
                public Object get() {
                    VTLObject result = accumulator.identity();
                    for (VTLObject predecessor : hierarchy.predecessors(successor)) {
                        VTLObject predecessorValue = map.getOrDefault(predecessor, accumulator.identity());
                        Composition composition = hierarchy.edgeValue(predecessor, successor);
                        result = accumulator.accumulator(composition).apply(result, predecessorValue);
                    }
                    return result;
                }
            });
        }

        if (next.hasNext()) {
            DataPoint buffer = null;
            while (next.hasNext()) {
                DataPoint point = next.next();
                if (buffer == null) {
                    buffer = (DataPoint) point.clone();
                }
                VTLObject identifierValue = point.get(0);
                if (map.containsKey(identifierValue)) {
                    map.put(identifierValue, point.get(1));
                }
                // TODO: send if not filter.
            }
            for (Map.Entry<VTLObject, VTLObject> entry : map.entrySet()) {
                VTLObject identifier = entry.getKey();
                VTLObject value = entry.getValue();
                buffer.set(0, identifier);
                buffer.set(1, value);
                action.accept((DataPoint) buffer.clone());
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Spliterator<DataPoint> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }
}
