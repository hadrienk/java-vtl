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

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.model.Identifier;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public abstract class JoinOperation implements Supplier<Dataset> {

    // The datasets to operate on.
    private final Map<String, Dataset> datasets;

    // The dimensions to join on.
    private final Set<Component> dimensions;
    // Holds the
    private final List<Function<Dataset, Dataset>> clauses;

    public JoinOperation(Map<String, Dataset> namedDatasets) {
        // Find the common identifier.
        // Only if > 1

        Multiset<Component<?>> components = HashMultiset.create();
        for (Dataset dataset : namedDatasets.values()) {
            // TODO: create DataPoint so we can use Components.
            //components.addAll(dataset.getDataStructure());
        }

        datasets = namedDatasets;
        dimensions = components.entrySet().stream()
                .filter(entry -> entry.getCount() == namedDatasets.size())
                .map(Multiset.Entry::getElement)
                .filter(component -> component instanceof Identifier)
                .collect(Collectors.toSet());
        // TODO: Throw exception if identifier not valid.

        // TODO: Add default clauses.
        clauses = Lists.newArrayList();
    }

    public List<Function<Dataset, Dataset>> getClauses() {
        return clauses;
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
        for (Function<Dataset, Dataset> clause : clauses) {
            dataset = clause.apply(dataset);
        }
        return dataset;
    }
}
