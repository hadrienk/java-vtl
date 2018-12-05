package no.ssb.vtl.script.operations;

import com.google.common.collect.ImmutableList;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;

import java.util.function.ToIntFunction;

/**
 * Wrapper class to access values of a DataPoint by column name.
 */
public class DataPointMap {

    private final ToIntFunction<String> hash;
    private DataPoint dataPoint;

    public DataPointMap(ToIntFunction<String> hash) {
        this.hash = hash;
    }

    public DataPointMap(DataStructure dataStructure) {
        ImmutableList<String> indices = ImmutableList.copyOf(dataStructure.keySet());
        this.hash = indices::indexOf;
    }

    public DataPointMap(Dataset dataset) {
        DataStructure dataStructure = dataset.getDataStructure();
        ImmutableList<String> indices = ImmutableList.copyOf(dataStructure.keySet());
        this.hash = indices::indexOf;
    }

    /**
     * Returns the current underlying Datapoint value.
     */
    public DataPoint getDataPoint() {
        return this.dataPoint;
    }

    /**
     * Update the underlying Datapoint value.
     */
    public void setDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
    }

    /**
     * Returns the element at the specified column in this list.
     *
     * @param column column of the element to return
     * @return the element at the specified column
     */
    public VTLObject get(String column) {
        return this.dataPoint.get(hash.applyAsInt(column));
    }

    /**
     * Replaces the element at the specified column with the specified element.
     *
     * @param column  column of the element to replace.
     * @param element element to be stored at the specified column
     * @return the element previously at the specified position
     */
    public VTLObject set(String column, VTLObject element) {
        return this.dataPoint.set(hash.applyAsInt(column), element);
    }

}
