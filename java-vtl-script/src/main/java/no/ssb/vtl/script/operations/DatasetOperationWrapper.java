package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.Ordering;
import no.ssb.vtl.model.OrderingSpecification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Wraps a normal dataset to a dataset operation.
 */
public class DatasetOperationWrapper extends AbstractDatasetOperation {

    private static class DatasetOperationSpliterator implements Spliterator<DataPointMap> {

        private final DataStructure structure;
        private final Spliterator<DataPoint> delegate;
        private final DataPointMap buffer;

        public DatasetOperationSpliterator(Spliterator<DataPoint> delegate, DataStructure structure) {
            this.structure = checkNotNull(structure);
            this.delegate = checkNotNull(delegate);

            // TODO: Could actually be the fastest..
            List<String> hash = new ArrayList<>(structure.keySet());
            this.buffer = new DataPointMap(hash::indexOf);
        }

        @Override
        public boolean tryAdvance(Consumer<? super DataPointMap> action) {
            return delegate.tryAdvance(dataPoint -> {
                buffer.setDataPoint(dataPoint);
                action.accept(buffer);
            });
        }

        @Override
        public Spliterator<DataPointMap> trySplit() {
            Spliterator<DataPoint> newDelegate = delegate.trySplit();
            if (newDelegate != null) {
                return new DatasetOperationSpliterator(newDelegate, structure);
            } else {
                return null;
            }
        }

        @Override
        public long estimateSize() {
            return delegate.estimateSize();
        }

        @Override
        public int characteristics() {
            return delegate.characteristics();
        }
    }

    private final Dataset dataset;

    public DatasetOperationWrapper(Dataset dataset) {
        super(Collections.emptyList());
        this.dataset = checkNotNull(dataset);
    }

    private Stream<DataPoint> ensureStream(Ordering orders, Filtering filtering, Set<String> components) {
        return dataset.getData(orders, filtering, components).orElseGet(() ->
                dataset.getData().sorted(orders).filter(filtering).map(o -> {
                    // TODO
                    return o;
                })
        );
    }

    @Override
    public Stream<DataPoint> computeData(Ordering orders, Filtering filtering, Set<String> components) {
        if (dataset instanceof AbstractDatasetOperation) {
            return ((AbstractDatasetOperation) dataset).computeData(orders, filtering, components);
        } else {
            return ensureStream(orders, filtering, components);
        }

    }

    @Override
    protected DataStructure computeDataStructure() {
        return dataset.getDataStructure();
    }

    @Override
    public FilteringSpecification unsupportedFiltering(FilteringSpecification filtering) {
        return Filtering.ALL;
    }

    @Override
    public OrderingSpecification unsupportedOrdering(OrderingSpecification filtering) {
        return Ordering.ANY;
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return dataset.getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return dataset.getSize();
    }
}
