package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class KeepOperator implements Dataset {

    // The dataset we are applying the KeepOperator on.
    private final Dataset dataset;
    private final Set<Component> components;

    private DataStructure cache;

    public KeepOperator(Dataset dataset, Set<Component> names) {
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
     */
    private DataStructure computeDataStructure() {
        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (Map.Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
            Component component = componentEntry.getValue();
            if (components.contains(component) || component.isIdentifier()) {
                newDataStructure.put(componentEntry);
            }
        }
        return newDataStructure.build();
    }

    @Override
    public Stream<Tuple> get() {
        DataStructure structure = getDataStructure();
        return dataset.get().map(
                dataPoints -> {
                    dataPoints.removeIf(dataPoint -> !structure.containsValue(dataPoint.getComponent()));
                    return dataPoints;
                }
        );
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        helper.addValue(components);
        helper.add("structure", cache);
        return helper.omitNullValues().toString();
    }
}
