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

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.operations.RenameOperation;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.RandomAccess;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;
import static no.ssb.vtl.model.Component.*;

/**
 * Abstract join operation.
 */
public abstract class AbstractJoinOperation {

    // The datasets the join operates on.
    private final Map<String, Dataset> datasets;

    // The identifiers that will be used to join the datasets.
    private final Set<String> commonIdentifierNames;

    private final Bindings joinScope;
    
    public AbstractJoinOperation(Map<String, Dataset> namedDatasets) {

        checkArgument(
                !namedDatasets.isEmpty(),
                "join operation impossible on empty dataset list"
        );
    
        Multiset<Component> components = getComponents(namedDatasets);
    
        Set<Component> commonIdentifiers = components.entrySet()
                .stream()
                .filter(entry -> entry.getCount() == namedDatasets.size())
                .map(Multiset.Entry::getElement)
                .filter(component -> component.getRole() == Role.IDENTIFIER)
                .collect(Collectors.toSet());
    
        commonIdentifierNames = commonIdentifiers.stream()
                .map(Component::getName)
                .collect(Collectors.toSet());
        
        checkArgument(!commonIdentifierNames.isEmpty(), "could not find common identifiers in the datasets %s", namedDatasets);
    
        joinScope = createJoinScope(namedDatasets, commonIdentifiers);

        // Rename all the components except the common identifiers.
        this.datasets = createDataset(namedDatasets);

    }
    
    private Multiset<Component> getComponents(Map<String, Dataset> namedDatasets) {
        List<Component> componentsList = namedDatasets.values().stream()
                .flatMap(dataset -> dataset.getDataStructure().values().stream())
                .collect(Collectors.toList());
        return HashMultiset.create(componentsList);
    }
    
    private Map<String, Dataset> createDataset(Map<String, Dataset> namedDatasets) {
        Map<String, Dataset> datasets = Maps.newHashMap();
        for (String datasetName : namedDatasets.keySet()) {
            Dataset dataset = namedDatasets.get(datasetName);
            
            Map<String, String> newNames = Maps.newHashMap();
            Map<String, Role> newRoles = Maps.newHashMap();
            
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
            datasets.put(datasetName, new RenameOperation(dataset, newNames, newRoles));
        }
        return datasets;
    }
    
    private Bindings createJoinScope(Map<String, Dataset> namedDatasets, Set<Component> commonIdentifiers) {
        Bindings bindings = new SimpleBindings();
        namedDatasets.forEach(bindings::put);
        for (Map.Entry<String, Dataset> dataset : namedDatasets.entrySet()) {
        
            Collection<Component> datasetComponents = dataset.getValue().getDataStructure().values();
            for (Component component : datasetComponents) {
                bindings.put(String.format("%s.%s", dataset.getKey(), component.getName()), component);
            }
        }
        commonIdentifiers.forEach(component -> bindings.put(component.getName(), component));
        return bindings;
    }
    
    public Set<String> getCommonIdentifierNames() {
        return commonIdentifierNames;
    }
    
    public Bindings getJoinScope() {
        return joinScope;
    }
    
    public abstract WorkingDataset workDataset();
    
    Map<String, Dataset> getDatasets() {
        return datasets;
    }

    Set<String> getIds() {
        return commonIdentifierNames;
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
