package no.ssb.vtl.script.operations.join;
/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.collect.*;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import javax.script.Bindings;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Abstract join operation.
 */
public abstract class AbstractJoinOperation {

    // The datasets the join operates on.
    private final Map<String, Dataset> datasets;
    private final Bindings joinScope;

    public AbstractJoinOperation(Map<String, Dataset> namedDatasets) {

        checkArgument(
                !namedDatasets.isEmpty(),
                "join operation impossible on empty dataset list"
        );

        this.datasets = namedDatasets;
        this.joinScope = new JoinScopeBindings(this.datasets);

    }

    /**
     * Compute a multimap with components eligible as keys.
     */
    protected ImmutableMultimap<Dataset, Component> getPossibleKeyComponents() {
        Set<String> names = Sets.newHashSet();
        Set<Class<?>> types = Sets.newHashSet();

        Multimap<Dataset, Component> possibleKeyComponents = HashMultimap.create();
        for (Dataset dataset : datasets.values()) {
            for (Map.Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
                String name = componentEntry.getKey();
                Component component = componentEntry.getValue();
                if (component.isIdentifier() && names.contains(name) && types.contains(component.getType())) {
                    checkArgument(
                            possibleKeyComponents.put(dataset, component),
                            "the component %s in the dataset %s was already seen",
                            componentEntry, dataset
                    );
                }
                names.add(name);
                types.add(component.getType());
            }
        }

        return ImmutableMultimap.copyOf(possibleKeyComponents);
    }

    /**
     * Compute the DataStructure of the join dataset.
     */
    protected DataStructure getDataStructure() {

        // Optimization.
        if (datasets.size() == 1) {
            return datasets.values().iterator().next().getDataStructure();
        }

        Set<String> ids = Sets.newHashSet();
        DataStructure.Builder newDataStructure = DataStructure.builder();
        for (String datasetName : datasets.keySet()) {
            DataStructure structure = datasets.get(datasetName).getDataStructure();
            for (Map.Entry<String, Component> componentEntry : structure.entrySet()) {
                if (!componentEntry.getValue().isIdentifier()) {
                    newDataStructure.put(datasetName.concat("_".concat(componentEntry.getKey())), componentEntry.getValue());
                } else {
                    if (ids.add(componentEntry.getKey())) {
                        newDataStructure.put(componentEntry);
                    }
                }
            }
        }
        return newDataStructure.build();
    }

    public Bindings getJoinScope() {
        return joinScope;
    }

    public abstract WorkingDataset workDataset();

    Map<String, Dataset> getDatasets() {
        return datasets;
    }

    /**
     * Holds the "working dataset" tuples.
     */
    static final class JoinTuple extends Dataset.AbstractTuple implements RandomAccess {

        private final List<DataPoint> delegate = Lists.newArrayList();

        public JoinTuple(List<DataPoint> ids) {
            this.delegate.addAll(ids);
        }

        @Override
        protected List<DataPoint> delegate() {
            return delegate;
        }

    }

}
