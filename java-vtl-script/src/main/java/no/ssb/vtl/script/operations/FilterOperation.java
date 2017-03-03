package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

public class FilterOperation extends AbstractUnaryDatasetOperation {

    private final Predicate<DataPoint> predicate;

    public FilterOperation(Dataset dataset, Predicate<DataPoint> predicate) {
        super(checkNotNull(dataset, "the dataset was null"));
        this.predicate = checkNotNull(predicate, "the predicate was null");
    }

    protected DataStructure computeDataStructure() {
        return getChild().getDataStructure();
    }

    @Override
    @Deprecated
    public Stream<DataPoint> get() {
        return getData().map(o -> o);
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        Map<Boolean, List<DataPoint>> predicateResultMap = getChild().stream().collect(Collectors.partitioningBy(predicate));
        helper.addValue(predicateResultMap);
        helper.add("structure", getDataStructure());
        return helper.omitNullValues().toString();
    }

    @Override
    public Stream<? extends DataPoint> getData() {
        return getChild().filter(predicate).stream();
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getSize() {
        return Optional.empty();
    }
}
