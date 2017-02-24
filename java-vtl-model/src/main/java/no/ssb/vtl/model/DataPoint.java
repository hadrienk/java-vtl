package no.ssb.vtl.model;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingList;

import java.util.List;

public interface DataPoint extends List<VTLObject> {

    static DataPoint create(List<VTLObject> components) {
        return new AbstractDataPoint() {
            @Override
            protected List<VTLObject> delegate() {
                return components;
            }
        };
    }
    
    abstract class AbstractDataPoint extends ForwardingList<VTLObject> implements DataPoint {

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(DataPoint.class)
                    .add("values", delegate())
                    .toString();
        }

    }
}
