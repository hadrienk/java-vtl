package no.ssb.vtl.model;

import com.google.common.collect.ImmutableList;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Base class for DatasetOperations.
 */
public abstract class AbstractDatasetOperation implements Dataset {

    private final ImmutableList<Dataset> children;
    private DataStructure cache;

    protected AbstractDatasetOperation(List<Dataset> children) {
        this.children = ImmutableList.copyOf(checkNotNull(children));
    }

    @Override
    public final DataStructure getDataStructure() {
        return cache = (cache == null ? computeDataStructure() : cache);
    }

    protected abstract DataStructure computeDataStructure();

    public ImmutableList<Dataset> getChildren() {
        return children;
    }

}
