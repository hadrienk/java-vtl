package no.ssb.vtl.script.operations.join;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;

import java.util.function.Consumer;

/**
 * A consumer that expands the size of the {@link DataPoint} it receives.
 */
public class DataPointCapacityExpander implements Consumer<DataPoint> {

    private final int newSize;

    public DataPointCapacityExpander(int newCapacity) {
        this.newSize = newCapacity;
    }

    @Override
    public void accept(DataPoint dataPoint) {
        dataPoint.ensureCapacity(newSize);
        while (dataPoint.size() < newSize) {
            dataPoint.add(VTLObject.NULL);
        }
    }
}
