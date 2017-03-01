package no.ssb.vtl.model;

import com.google.common.base.MoreObjects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DataPoint extends ArrayList<VTLObject> {

    ArrayList<VTLObject> delegate;

    protected DataPoint(int initialCapacity) {
        super(initialCapacity);
    }

    protected DataPoint() {
    }

    protected DataPoint(Collection<? extends VTLObject> c) {
        super(c);
    }

    public static DataPoint create(int initialCapacity) {
        return new DataPoint(Collections.nCopies(initialCapacity, VTLObject.NULL));
    }

    public static DataPoint create(List<VTLObject> components) {
        return new DataPoint(components);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(DataPoint.class)
                .add("values", this.iterator())
                .toString();
    }
}
