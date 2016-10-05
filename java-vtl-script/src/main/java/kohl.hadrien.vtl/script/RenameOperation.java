package kohl.hadrien.vtl.script;

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

import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import kohl.hadrien.Component;
import kohl.hadrien.DataStructure;
import kohl.hadrien.Dataset;
import kohl.hadrien.Identifier;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Rename operation.
 */
public class RenameOperation implements Function<Dataset, Dataset> {

    private final ImmutableMap<String, String> names;
    private final ImmutableMap<String, Class<? extends Component>> roles;

    public RenameOperation(Map<String, String> names, Map<String, Class<? extends Component>> roles) {
        HashBiMap.create(names);
        checkArgument(names.keySet().containsAll(roles.keySet()), "keys %s not present in %s",
                Sets.difference(roles.keySet(), names.keySet()), names.keySet());
        this.names = ImmutableMap.copyOf(names);
        this.roles = ImmutableMap.copyOf(roles);
    }

    @Override
    public Dataset apply(final Dataset dataset) {

        // Copy.
        Map<String, Class<? extends Component>> oldDataStructure = dataset.getDataStructure();
        ImmutableMap.Builder<String, Class<? extends Component>> newDataStructure = ImmutableMap.builder();

        checkArgument(oldDataStructure.keySet().containsAll(names.keySet()),
                "the dataset %s did not contain components with names %s", dataset,
                Sets.difference(names.keySet(), oldDataStructure.keySet())
        );

        for (String oldName : dataset.getDataStructure().keySet()) {
            String newName = names.getOrDefault(oldName, oldName);
            Class<? extends Component> newComponent;
            newComponent = roles.getOrDefault(newName, oldDataStructure.get(oldName));
            newDataStructure.put(newName, newComponent);
        }

        return new Dataset() {
            @Override
            public Set<List<Identifier>> cartesian() {
                return null;
            }

            @Override
            public DataStructure getDataStructure() {
                return new DataStructure(newDataStructure.build());
            }

            @Override
            public Stream<Tuple> get() {
                return dataset.get();
            }
        };
    }
}
