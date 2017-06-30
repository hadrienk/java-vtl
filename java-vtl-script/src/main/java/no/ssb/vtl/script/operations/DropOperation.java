package no.ssb.vtl.script.operations;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

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

/**
 * TODO: Implement "operator" and  "function" interfaces.
 */
public class DropOperation extends AbstractUnaryDatasetOperation {

    private final Set<Component> components;

    public DropOperation(Dataset dataset, Set<Component> components) {
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
