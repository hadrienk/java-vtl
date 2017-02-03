package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * TODO: Implement "operator" and  "function" interfaces.
 */
public class DropOperator implements Dataset {
    // The dataset we are applying the KeepOperator on.
    private final Dataset dataset;
    private final Set<Component> components;

    private DataStructure cache;

    public DropOperator(Dataset dataset, Set<Component> names) {
        this.dataset = checkNotNull(dataset, "the dataset was null");
        this.components = checkNotNull(names, "the component list was null");

        checkArgument(!names.isEmpty(), "the list of component to drop was null");
    }

    @Override
    public DataStructure getDataStructure() {
        return cache = (cache == null ? computeDataStructure() : cache);
    }

    /**
     * Compute the new data structure.
     *
     * @return
     */
    private DataStructure computeDataStructure() {
        DataStructure structure = dataset.getDataStructure();
        Map<String, Component.Role> roles = Maps.newHashMap();
        Map<String, Class<?>> types = Maps.newHashMap();
        for (Map.Entry<String, Component> componentEntry : structure.entrySet()) {
            Component component = componentEntry.getValue();
            if (!components.contains(component) || component.isIdentifier()) {
                Class<?> type = component.getType();
                Component.Role role = component.getRole();
                roles.put(componentEntry.getKey(), role);
                types.put(componentEntry.getKey(), type);
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
                    dataPoints.removeIf(dataPoint -> !structure.containsKey(dataPoint.getName()));
                    return dataPoints;
                }
        );
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        Integer limit = 5;
        Iterables.limit(Iterables.concat(
                components,
                Collections.singletonList("and " + (components.size() - limit) + " more")
        ), Math.min(limit, components.size())).forEach(
                helper::addValue
        );
        return helper.toString();
    }
}
