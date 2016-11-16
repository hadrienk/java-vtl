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
import kohl.hadrien.vtl.script.support.CombinedList;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class JoinOperation implements Supplier<Dataset> {

    // The datasets to operate on.
    private final Map<String, Dataset> datasets;
    // The dimensions to join on.
    private final Set<String> dimensions;
    // Holds the operations of the join.
    private final List<Function<JoinTuple, JoinTuple>> clauses;


    public JoinOperation(Map<String, Dataset> namedDatasets) {
        // Find the common identifier.
        // Only if > 1

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
        dimensions = components.entrySet().stream()
                .filter(entry -> entry.getCount() == namedDatasets.size())
                .map(Multiset.Entry::getElement)
                .filter(component -> component.role.isAssignableFrom(Identifier.class))
                .map(comp -> comp.name)
                .collect(Collectors.toSet());
        // TODO: Throw exception if identifier not valid.

        // TODO: Add default clauses.
        clauses = Lists.newArrayList();
    }

    public Map<String, Dataset> getDatasets() {
        return datasets;
    }

    public Set<String> getDimensions() {
        return dimensions;
    }

    /**
     * Returns the initial Dataset that is the result of the join.
     * <p>
     * Inner, Outer and Cross implementations of JoinOperation override this method.
     */
    abstract Dataset join();

    @Override
    public Dataset get() {
        Dataset dataset = join();
        return new Dataset() {

            Stream<Dataset.Tuple> rows = dataset.get();

            @Override
            public DataStructure getDataStructure() {
                return dataset.getDataStructure();
            }

            @Override
            public Stream<Tuple> get() {
                for (Function<JoinTuple, JoinTuple> clause : clauses) {
                    rows = rows.map(tuple -> clause.apply((JoinTuple) tuple));
                }
                return rows;
            }
        };
    }

    public List<Function<JoinTuple, JoinTuple>> getClauses() {
        return clauses;
    }

    /**
     * Holds the "working dataset" tuples.
     */
    static final class JoinTuple extends Dataset.AbstractTuple {

        private final List<Identifier> ids;
        private Multimap<String, Component> values = LinkedHashMultimap.create();

        public JoinTuple(List<Identifier> ids) {
            this.ids = checkNotNull(ids);
        }

        public List<Identifier> getKeyIds() {
            return ids;
        }

        public Multimap<String, Component> getValues() {
            return values;
        }

        public void setValues(Multimap<String, Component> values) {
            this.values = values;
        }

        @Override
        protected List<Component> delegate() {
            return new CombinedList<>((List) ids(), values());
        }

        @Override
        public List<Identifier> ids() {
            return ids;
        }

        @Override
        public List<Component> values() {
            return new CombinedList<>(Lists.newArrayList(values.values()));
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
