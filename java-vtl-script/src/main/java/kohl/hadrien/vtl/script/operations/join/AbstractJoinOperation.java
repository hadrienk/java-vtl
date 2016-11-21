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
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.model.Identifier;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Abstract join operation.
 * <p>
 * Implementations should provide the joined stream and its data structure.
 * TODO: Define a common interface for operation that expose {@link DataStructure}?
 */
public abstract class AbstractJoinOperation implements Dataset {

    // The datasets to operate on.
    // TODO: Abstract this as a Context/Bindings to allow reusability of the Clauses.
    private final Map<String, Dataset> datasets;

    // The ids to join on.
    private final Set<String> ids;

    // Holds the operations of the join.
    private final List<JoinClause> clauses;

    public AbstractJoinOperation(Map<String, Dataset> namedDatasets) {
        checkArgument(
                !namedDatasets.isEmpty(),
                "join operation impossible on empty dataset list"
        );

        // Find the common identifier.
        Multiset<Comp> components = HashMultiset.create();
        for (Dataset dataset : namedDatasets.values()) {
            // TODO: create DataPoint so we can use Component equality.
            DataStructure structure = dataset.getDataStructure();
            structure.names();
            for (String key : structure.names()) {
                components.add(new Comp() {{
                    name = key;
                    type = structure.types().get(key);
                    role = structure.roles().get(key);
                }});
            }
        }

        datasets = namedDatasets;
        ids = components.entrySet().stream()
                .filter(entry -> entry.getCount() == namedDatasets.size())
                .map(Multiset.Entry::getElement)
                .filter(component -> component.role.isAssignableFrom(Identifier.class))
                .map(comp -> comp.name)
                .collect(Collectors.toSet());
        // TODO: Throw exception if identifier not valid.

        // TODO: Add default clauses.
        clauses = Lists.newArrayList();
    }

    Map<String, Dataset> getDatasets() {
        return datasets;
    }

    Set<String> getIds() {
        return ids;
    }

    public List<JoinClause> getClauses() {
        return clauses;
    }

    abstract Stream<Tuple> joinStream();

    abstract DataStructure joinStructure();

    @Override
    public Stream<Tuple> get() {
        Stream<Tuple> dataset = joinStream();
        for (JoinClause clause : clauses) {
            dataset = dataset.map(clause::transformTuple);
        }
        return dataset;
    }

    @Override
    public DataStructure getDataStructure() {
        DataStructure structure = joinStructure();
        for (JoinClause clause : clauses) {
            structure = clause.transformDataStructure(structure);
        }
        return structure;
    }

    /**
     * Holds the "working dataset" tuples.
     */
    static final class JoinTuple extends Dataset.AbstractTuple {

        private final ImmutableList<Identifier> ids;
        private Multimap<String, Component> values = LinkedHashMultimap.create();

        public JoinTuple(List<Identifier> ids) {
            this.ids = ImmutableList.copyOf(ids);
        }

        protected Multimap<String, Component> getValues() {
            return values;
        }

        public void setValues(Multimap<String, Component> values) {
            this.values = values;
        }

        @Override
        protected List<Component> delegate() {
            return new kohl.hadrien.vtl.script.support.CombinedList<>((List) ids(), values());
        }

        @Override
        public List<Identifier> ids() {
            return ids;
        }

        @Override
        public List<Component> values() {
            return new kohl.hadrien.vtl.script.support.CombinedList<>(Lists.newArrayList(values.values()));
        }
    }

    // TODO: create DataPoint so we can use Component equality.
    private class Comp {
        String name;

        Class<?> type;

        Class<? extends Component> role;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Comp comp = (Comp) o;
            return Objects.equals(name, comp.name) &&
                    Objects.equals(type, comp.type) &&
                    Objects.equals(role, comp.role);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, type, role);
        }
    }
}
