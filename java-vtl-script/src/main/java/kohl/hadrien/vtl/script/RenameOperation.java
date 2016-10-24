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

import com.google.common.collect.*;
import kohl.hadrien.Component;
import kohl.hadrien.DataStructure;
import kohl.hadrien.Dataset;
import kohl.hadrien.Identifier;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
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

        Set<String> oldNames = dataset.getDataStructure().names();
        checkArgument(oldNames.containsAll(names.keySet()),
                "the data set %s did not contain components with names %s", dataset,
                Sets.difference(names.keySet(), oldNames)
        );

        // Copy.
        final Map<String, Class<? extends Component>> oldRoles, newRoles;
        oldRoles = dataset.getDataStructure().roles();
        newRoles = Maps.newHashMap();

        final Map<String, Class<?>> oldTypes, newTypes;
        oldTypes = dataset.getDataStructure().types();
        newTypes = Maps.newHashMap();

        final Map<String, String> mapping = HashBiMap.create();

        for (String oldName : oldNames) {
            String newName = names.getOrDefault(oldName, oldName);
            newTypes.put(newName, oldTypes.get(oldName));
            newRoles.put(newName, roles.getOrDefault(oldName, oldRoles.get(oldName)));
            mapping.put(oldName, newName);
        }

        final DataStructure renamedStructure = new DataStructure() {

            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return dataset.getDataStructure().converter();
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return Collections.unmodifiableMap(newRoles);
            }

            @Override
            public Map<String, Class<?>> types() {
                return Collections.unmodifiableMap(newTypes);
            }

            @Override
            public Set<String> names() {
                return Collections.unmodifiableSet(Sets.newHashSet(types().keySet()));
            }
        };

        return new Dataset() {
            @Override
            public Set<List<Identifier>> cartesian() {
                return null;
            }

            @Override
            public DataStructure getDataStructure() {
                return renamedStructure;
            }

            @Override
            public Stream<Tuple> get() {
                return dataset.get().map(components -> {

                    components.replaceAll(component -> new Component() {

                        @Override
                        public Object get() {
                            return component.get();
                        }

                        @Override
                        public String name() {
                            return mapping.get(component.name());
                        }

                        @Override
                        public Class<?> type() {
                            return newTypes.get(name());
                        }

                        @Override
                        public Class<? extends Component> role() {
                            return newRoles.get(name());
                        }

                        @Override
                        public String toString() {
                            return get().toString();
                        }
                    });
                    return components;
                });
            }
        };
    }
}
