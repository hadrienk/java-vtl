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

public final class DataPointMap {

    private final DataStructure structure;
    private final ImmutableList<String> names;

    class View extends ForwardingMap<String, VTLObject> {

        private final DataPoint point;
        private final Map<String, VTLObject> delegate;

        protected View(DataPoint point) {
            this.point = point;
            ImmutableMap.Builder<String, Supplier<VTLObject>> builder = ImmutableMap.builder();
            UnmodifiableListIterator<String> it = names.listIterator();
            while (it.hasNext()) {
                int index = it.nextIndex();
                String name = it.next();
                builder.put(name, () -> this.point.get(index));
            }
            this.delegate = Maps.transformValues(builder.build(), Supplier::get);
        }

        DataPoint unwrap() {
            return point;
        }

        @Override
        protected Map<String, VTLObject> delegate() {
            return this.delegate;
        }
    }

    public View wrap(DataPoint dataPoint) {
        return new View(dataPoint);
    }

    public DataPointMap(DataStructure structure) {
        this.structure = structure;
        ImmutableMap.Builder<String, Supplier<VTLObject>> builder = ImmutableMap.builder();
        names = ImmutableSet.copyOf(structure.keySet()).asList();
    }
}
