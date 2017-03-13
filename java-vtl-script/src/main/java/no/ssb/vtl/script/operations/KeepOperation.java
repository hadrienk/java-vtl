package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.AbstractUnaryDatasetOperation;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.*;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class KeepOperation extends AbstractUnaryDatasetOperation {

    private final Set<Component> components;

    public KeepOperation(Dataset dataset, Set<Component> names) {
        super(checkNotNull(dataset, "the dataset was null"));
        this.components = checkNotNull(names, "the component list was null");

        checkArgument(!names.isEmpty(), "the list of component to keep was null");
    }

    /**
     * Compute the new data structure.
     */
    @Override
    protected DataStructure computeDataStructure() {
        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (Map.Entry<String, Component> componentEntry : getChild().getDataStructure().entrySet()) {
            Component component = componentEntry.getValue();
            if (components.contains(component) || component.isIdentifier()) {
                newDataStructure.put(componentEntry);
            }
        }
        return newDataStructure.build();
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        helper.addValue(components);
        helper.add("structure", getDataStructure());
        return helper.omitNullValues().toString();
    }

    @Override
    public Stream<DataPoint> getData() {
        DataStructure oldStructure = getChild().getDataStructure();
        HashSet<Component> oldComponents = Sets.newLinkedHashSet(oldStructure.values());
        HashSet<Component> newComponents = Sets.newLinkedHashSet(getDataStructure().values());
        LinkedList<Component> componentsToRemove = Lists.newLinkedList(Sets.difference(oldComponents, newComponents));
        return getChild().getData().map(
                dataPoints -> {
                    Iterator<Component> descendingIterator = componentsToRemove.descendingIterator();
                    while (descendingIterator.hasNext()) {
                        Component component = descendingIterator.next();
                        int index = oldStructure.indexOf(component);
                        dataPoints.remove(index);
                    }
                    return dataPoints;
                }
        );
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return getChild().getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return getChild().getSize();
    }
}
