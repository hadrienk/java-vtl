package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO: Implement "operator" and  "function" interfaces.
 */
public class DropOperator extends AbstractUnaryDatasetOperation {

    private final Set<Component> components;

    public DropOperator(Dataset dataset, Set<Component> components) {
        super(checkNotNull(dataset, "the dataset was null"));
        this.components = checkNotNull(components, "the component list was null");

        checkArgument(!components.isEmpty(), "the list of component to drop was null");
    }

    /**
     * Compute the new data structure.
     */
    @Override
    protected DataStructure computeDataStructure() {
        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (Map.Entry<String, Component> componentEntry : getChild().getDataStructure().entrySet()) {
            Component component = componentEntry.getValue();
            if (!components.contains(component) || component.isIdentifier()) {
                newDataStructure.put(componentEntry);
            }
        }
        return newDataStructure.build();
    }

    @Override
    public Stream<DataPoint> get() {
        DataStructure structure = getDataStructure();
        return getChild().get().map(
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
        helper.add("structure", getDataStructure());
        return helper.omitNullValues().toString();
    }
}
