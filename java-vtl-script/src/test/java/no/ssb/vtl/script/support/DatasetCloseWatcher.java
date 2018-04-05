package no.ssb.vtl.script.support;

import com.google.common.collect.ForwardingObject;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.Order;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public abstract class DatasetCloseWatcher extends ForwardingObject implements Dataset {

    private int counter = 0;

    private DatasetCloseWatcher() {
        // disable
    }

    protected abstract Dataset delegate();

    public boolean allStreamWereClosed() {
        return counter == 0;
    }

    public static DatasetCloseWatcher wrap(Dataset dataset) {
        return new DatasetCloseWatcher() {

            @Override
            protected Dataset delegate() {
                return dataset;
            }
        };
    }

    @Override
    public Stream<DataPoint> getData() {
        return wrap(delegate().getData());
    }

    private void increase() {
        this.counter++;
    }

    private void decrease() {
        this.counter--;
    }

    private Stream<DataPoint> wrap(Stream<DataPoint> stream) {
        this.increase();
        return stream.onClose(this::decrease);
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order orders, Filtering filtering, Set<String> components) {
        return delegate().getData(orders, filtering, components).map(this::wrap);
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order order) {
        return delegate().getData(order).map(this::wrap);
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Filtering filtering) {
        return getData(filtering).map(this::wrap);
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Set<String> components) {
        return getData(components).map(this::wrap);
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return delegate().getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return delegate().getSize();
    }

    @Override
    public DataStructure getDataStructure() {
        return delegate().getDataStructure();
    }
}
