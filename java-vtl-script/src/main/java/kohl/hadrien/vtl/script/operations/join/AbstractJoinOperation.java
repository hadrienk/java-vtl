package kohl.hadrien.vtl.script.operations.join;
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
import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.DataPoint;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.script.operations.RenameOperation;
import kohl.hadrien.vtl.script.support.CombinedList;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;
import static kohl.hadrien.vtl.model.Component.Role;

/**
 * Abstract join operation.
 */
public abstract class AbstractJoinOperation implements Dataset {

    // The datasets the join operates on.
    private final Map<String, Dataset> datasets = Maps.newHashMap();

    // The identifiers that will be used to join the datasets.
    private final Set<String> commonIdentifierNames;

    // Holds the operations of the join.
    private final List<JoinClause> clauses = Lists.newArrayList();
    //private final Iterator<JoinClause> clauseIterator = ;

    private WorkingDataset workingDataset;

    public AbstractJoinOperation(Map<String, Dataset> namedDatasets) {

        checkArgument(
                !namedDatasets.isEmpty(),
                "join operation impossible on empty dataset list"
        );

        // Find the common identifier.
        Multiset<Component> components = HashMultiset.create();
        for (Dataset dataset : namedDatasets.values()) {
            DataStructure structure = dataset.getDataStructure();
            components.addAll(structure.values());
        }

        commonIdentifierNames = components.entrySet().stream()
                .filter(entry -> entry.getCount() == namedDatasets.size())
                .map(Multiset.Entry::getElement)
                .filter(component -> component.getRole() == Role.IDENTIFIER)
                .map(Component::getName)
                .collect(Collectors.toSet());
        checkArgument(!commonIdentifierNames.isEmpty(), "could not find common identifiers in the datasets %s", namedDatasets);


        // Rename all the components except the common identifiers.
        for (String datasetName : namedDatasets.keySet()) {
            Dataset dataset = namedDatasets.get(datasetName);

            Map<String, String> newNames = Maps.newHashMap();
            Map<String, Component.Role> newRoles = Maps.newHashMap();

            for (Component component : dataset.getDataStructure().values()) {
                String newName;
                if (commonIdentifierNames.contains(component.getName())) {
                    newName = component.getName();
                } else {
                    newName = datasetName.concat(".").concat(component.getName());
                }
                newNames.put(component.getName(), newName);
                newRoles.put(component.getName(), component.getRole());
            }
            this.datasets.put(datasetName, new RenameOperation(dataset, newNames, newRoles));
        }

    }

    Map<String, Dataset> getDatasets() {
        return datasets;
    }

    Set<String> getIds() {
        return commonIdentifierNames;
    }

    public List<JoinClause> getClauses() {
        return clauses;
    }

    abstract WorkingDataset workDataset();

    @Override
    public Stream<Tuple> get() {

        if (workingDataset != null) {
            return workingDataset.get();
        }

        workingDataset = workDataset();
        for (JoinClause clause : clauses) {
            workingDataset = clause.apply(workingDataset);
        }
        return workingDataset.get();
    }

    @Override
    public DataStructure getDataStructure() {
        if (workingDataset != null) {
            return workingDataset.getDataStructure();
        }

        workingDataset = workDataset();
        for (JoinClause clause : clauses) {
            workingDataset = clause.apply(workingDataset);
        }
        return workingDataset.getDataStructure();
    }

    /**
     * Holds the "working dataset" tuples.
     * <p>
     * The identifier components are immutables. The methods add, remove and set are forwarded to the values.
     */
    static final class JoinTuple extends Dataset.AbstractTuple {

        private final ImmutableList<DataPoint> ids;
        private final List<DataPoint> values = Lists.newArrayList();

        public JoinTuple(List<DataPoint> ids) {
            this.ids = ImmutableList.copyOf(ids);
        }

        @Override
        protected List<DataPoint> delegate() {
            return new CombinedList<DataPoint>(ids, values);
        }

        @Override
        protected boolean standardAdd(DataPoint element) {
            return super.standardAdd(element);
        }

        @Override
        protected boolean standardRemove(Object object) {
            return super.standardRemove(object);
        }

        @Override
        public boolean add(DataPoint e) {
            return values.add(e);
        }

        @Override
        public DataPoint remove(int index) {
            return values.remove(index);
        }

        @Override
        public DataPoint set(int index, DataPoint element) {
            return values.set(index, element);
        }

    }
}
