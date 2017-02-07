package no.ssb.vtl.script.operations;

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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Rename operation.
 *
 * TODO: Implement {@link Dataset}
 */
public class RenameOperation implements Dataset {

    private final Dataset dataset;

    private final ImmutableMap<Component, String> newNames;
    private final ImmutableMap<Component, Component.Role> newRoles;
    private final Map<Component, Component> mapping = Maps.newIdentityHashMap();

    private DataStructure cache;

//    @Deprecated
//    public RenameOperation(Dataset dataset, Map<String, String> names, Map<String, Component.Role> roles) {
//        this.dataset = checkNotNull(dataset, "dataset was null");
//
//        // Checks that names key and values are unique.
//        HashBiMap.create(names);
//
//        checkArgument(names.keySet().containsAll(roles.keySet()), "keys %s not present in %s",
//                Sets.difference(roles.keySet(), names.keySet()), names.keySet());
//
//        this.names = ImmutableMap.copyOf(names);
//        this.roles = ImmutableMap.copyOf(roles);
//    }

    public RenameOperation(Dataset dataset, Map<Component, String> newNames) {
        this(dataset, newNames, Collections.emptyMap());
    }

    public RenameOperation(Dataset dataset, Map<Component, String> newNames, Map<Component, Component.Role> newRoles) {
        this.dataset = checkNotNull(dataset);
        this.newNames = ImmutableMap.copyOf(newNames);
        if (newRoles.isEmpty()) {
            LinkedHashMap<Component, Component.Role> roles = Maps.newLinkedHashMap();
            for (Component component : newNames.keySet()) {
                roles.put(component, component.getRole());
            }
            this.newRoles = ImmutableMap.copyOf(roles);
        } else {
            this.newRoles = ImmutableMap.copyOf(newRoles);
          // TODO: Check keySets of newNames and newRoles is ok.
        }
    }

    @Override
    public DataStructure getDataStructure() {
        return cache = (cache == null ? computeDataStructure() : cache);
    }

    private DataStructure computeDataStructure() {

        Map<Component, String> newNames = Maps.newIdentityHashMap();
        Map<Component, Component.Role> newRoles = Maps.newIdentityHashMap();
        newNames.putAll(this.newNames);
        newRoles.putAll(this.newRoles);

        Map<String, String> names = Maps.newLinkedHashMap();
        Map<String, Class<?>> types = Maps.newLinkedHashMap();
        Map<String, Component.Role> roles = Maps.newLinkedHashMap();
        for (Map.Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
            Component component = componentEntry.getValue();
            String oldName = componentEntry.getKey();

            String newName = newNames.getOrDefault(component, component.getName());
            Component.Role newRole = newRoles.getOrDefault(component, component.getRole());
            Class<?> newType = component.getType();

            types.put(newName, newType);
            roles.put(newName, newRole);
            names.put(oldName, newName);
        }

        /*
            Components are immutable so we need to create a "mapping" in order to
            recreate new data points with the new Component.
         */
        DataStructure newDataStructure = DataStructure.of(dataset.getDataStructure().converter(), types, roles);
        for (Map.Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
            Component oldComponent = componentEntry.getValue();
            Component newComponent = newDataStructure.get(names.get(componentEntry.getKey()));
            mapping.put(oldComponent, newComponent);
        }

//        Set<String> oldNames = dataset.getDataStructure().keySet();
//        checkArgument(oldNames.containsAll(names.keySet()),
//                "the data set %s did not contain components with names %s", dataset,
//                Sets.difference(names.keySet(), oldNames)
//        );
//
//        // Copy.
//        final Map<String, Component.Role> oldRoles, newRoles;
//        oldRoles = dataset.getDataStructure().getRoles();
//        newRoles = Maps.newHashMap();
//
//        final Map<String, Class<?>> oldTypes, newTypes;
//        oldTypes = dataset.getDataStructure().getTypes();
//        newTypes = Maps.newHashMap();
//
//
//
//        for (String oldName : oldNames) {
//            String newNames = names.getOrDefault(oldName, oldName);
//            newTypes.put(newNames, oldTypes.get(oldName));
//            newRoles.put(newNames, roles.getOrDefault(oldName, oldRoles.get(oldName)));
//            mapping.put(oldName, newNames);
//        }


        return newDataStructure;
    }

    @Override
    public Stream<Tuple> get() {
        return dataset.get().map(points -> {
            points.replaceAll(point -> new DataPoint(mapping.get(point.getComponent())) {
                @Override
                public Object get() {
                    return point.get();
                }
            });
            return points;
        });
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        Integer limit = 5;
        Iterables.limit(Iterables.concat(
                newNames.entrySet(),
                Collections.singletonList("and " + (newNames.entrySet().size() - limit) + " more")
        ), Math.min(limit, newNames.entrySet().size())).forEach(
                helper::addValue
        );
        return helper.toString();
    }
}
