package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.FilteringSpecification;
import no.ssb.vtl.model.OrderingSpecification;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

public class DatasetOperationWrapper extends AbstractDatasetOperation {

    private final Dataset dataset;

    public DatasetOperationWrapper(Dataset dataset) {
        super(Collections.emptyList());
        this.dataset = checkNotNull(dataset);
    }

    @Override
    protected DataStructure computeDataStructure() {
        return dataset.getDataStructure();
    }

    @Override
    public Boolean supportsFiltering(FilteringSpecification filtering) {
        return true;
    }

    @Override
    public Boolean supportsOrdering(OrderingSpecification filtering) {
        return true;
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
