package no.ssb.vtl.model;

import com.google.common.collect.ImmutableList;

public abstract class AbstractUnaryDatasetOperation extends AbstractDatasetOperation {

    protected AbstractUnaryDatasetOperation(Dataset child) {
        super(ImmutableList.of(child));
    }

    public Dataset getChild() {
        return getChildren().get(0);
    }
}
