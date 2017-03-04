package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLPredicate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class FilterOperation extends AbstractUnaryDatasetOperation {

    private final Predicate<DataPoint> predicate;

    public FilterOperation(Dataset dataset, VTLPredicate vtlPredicate) {
        super(checkNotNull(dataset, "the dataset was null"));
        checkNotNull(vtlPredicate, "the predicate was null");
        this.predicate = vtlPredicate.toPredicate(false);
    }
    
    protected DataStructure computeDataStructure() {
        return getChild().getDataStructure();
    }


    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        Map<Boolean, List<DataPoint>> predicateResultMap = getChild().getData().collect(Collectors.partitioningBy(predicate));
        helper.addValue(predicateResultMap);
        helper.add("structure", getDataStructure());
        return helper.omitNullValues().toString();
    }

    @Override
    public Stream<DataPoint> getData() {
        return getChild().getData().filter(predicate);
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
