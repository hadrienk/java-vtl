package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;

import java.util.function.ToIntFunction;

public class DataPointMap {

    private final ToIntFunction<String> hash;
    private DataPoint dataPoint;

    public DataPointMap(ToIntFunction<String> hash) {
        this.hash = hash;
    }

    public DataPoint getDataPoint() {
        return this.dataPoint;
    }

    public void setDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
    }

    public VTLObject get(String key) {
        return this.dataPoint.get(hash.applyAsInt(key));
    }

}
