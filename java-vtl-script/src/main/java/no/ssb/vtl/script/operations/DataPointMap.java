package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Filtering;
import no.ssb.vtl.model.OrderingSpecification;
import no.ssb.vtl.model.VTLObject;

import java.util.Comparator;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;

public class DataPointMap {

    private final ToIntFunction<String> hash;
    private DataPoint dataPoint;

    public DataPointMap(ToIntFunction<String> hash) {
        this.hash = hash;
    }

    public static Predicate<? super DataPointMap> createPredicate(DataStructure dataStructure, Filtering filtering) {
        throw new UnsupportedOperationException("TODO");
    }

    public static Comparator<DataPointMap> createComparator(DataStructure structure, OrderingSpecification specification) {
        throw new UnsupportedOperationException("TODO");
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
