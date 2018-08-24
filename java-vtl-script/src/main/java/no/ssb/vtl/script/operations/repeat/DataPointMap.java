package no.ssb.vtl.script.operations.repeat;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableListIterator;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLObject;

import java.util.Map;
import java.util.function.Supplier;

public class DataPointMap extends ForwardingMap<String, VTLObject> {

    private final Map<String, VTLObject> delegate;
    private DataPoint dataPoint;

    public DataPointMap setDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
        return this;
    }

    public DataPointMap(DataStructure structure) {
        ImmutableMap.Builder<String, Supplier<VTLObject>> builder = ImmutableMap.builder();
        ImmutableList<String> names = ImmutableSet.copyOf(structure.keySet()).asList();
        UnmodifiableListIterator<String> it = names.listIterator();
        while (it.hasNext()) {
            int index = it.nextIndex();
            String name = it.next();
            builder.put(name, () -> dataPoint.get(index));
        }
        this.delegate = Maps.transformValues(builder.build(), Supplier::get);
    }

    @Override
    protected Map<String, VTLObject> delegate() {
        return delegate;
    }
}
