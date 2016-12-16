package kohl.hadrien.vtl.script.operations;

import com.google.common.collect.Maps;
import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.DataPoint;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.script.support.CombinedList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class KeepOperator implements Dataset {

    // The dataset we are applying the KeepOperator on.
    private final Dataset dataset;
    private final Set<String> components;

    private DataStructure cache;

    public KeepOperator(Dataset dataset, Set<String> names) {
        this.dataset = checkNotNull(dataset, "the dataset was null");
        this.components = checkNotNull(names, "the component list was null");

        checkArgument(!names.isEmpty(), "the list of component to keep was null");
    }

    @Override
    public DataStructure getDataStructure() {
        return cache = (cache == null ? computeDataStructure() : cache);
    }

    /**
     * Compute the new data structure.
     * @return
     */
    private DataStructure computeDataStructure() {
        DataStructure structure = dataset.getDataStructure();
        Map<String, Component.Role> roles = Maps.newHashMap();
        Map<String, Class<?>> types = Maps.newHashMap();
        for (String componentName : structure.keySet()) {
            // Must keep ID TODO: Should we fail here? Or should the failure come from the WorkingDataset?
            // TODO: As it is now, the ids of the working dataset are immutable anyways.
            if (components.contains(componentName) || structure.get(componentName).isIdentifier()) {
                Class<?> type = structure.getTypes().get(componentName);
                Component.Role role = structure.getRoles().get(componentName);
                roles.put(componentName, role);
                types.put(componentName, type);
            }
        }
        return DataStructure.of(
                structure.converter(),
                types,
                roles
        );
    }

    @Override
    public Stream<Tuple> get() {
        DataStructure structure = getDataStructure();
        return dataset.get().map(
                dataPoints -> {
                    List<DataPoint> keptDatapoints = dataPoints.values();
                    keptDatapoints.removeIf(dataPoint -> !structure.containsKey(dataPoint.getName()));
                    return new AbstractTuple() {
                        @Override
                        protected List<DataPoint> delegate() {
                            return new CombinedList<DataPoint>(dataPoints.ids(), keptDatapoints);
                        }
                    };
                }
        );
    }
}
