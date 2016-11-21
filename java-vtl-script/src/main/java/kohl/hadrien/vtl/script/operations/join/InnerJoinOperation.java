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

import kohl.hadrien.vtl.model.Component;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;
import kohl.hadrien.vtl.script.support.JoinSpliterator;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Represent an inner join on datasets.
 */
public class InnerJoinOperation extends AbstractJoinOperation {

    public InnerJoinOperation(Map<String, Dataset> namedDatasets) {
        super(namedDatasets);
    }


    @Override
    DataStructure joinStructure() {
        return null;
    }

    @Override
    Stream<Tuple> joinStream() {

        Collection<Dataset> datasets = getDatasets().values();
        checkArgument(!datasets.isEmpty(), "no dataset for the join %s", this);

        // Optimization.
        if (datasets.size() == 1) {
            return datasets.iterator().next().get();
        }

        // Simple algorithm for now.
        final Set<String> dimensions = getIds();

        Comparator<List<Component>> keyComparator = new Comparator<List<Component>>() {
            @Override
            public int compare(List<Component> l, List<Component> r) {
                // TODO: Tuple should expose method to handle this.
                // TODO: Evaluate migrating to DataPoint.
                // TODO: When using on, the left over identifiers should be transformed to measures.
                Map<String, Comparable> lIds = l.stream()
                        .collect(Collectors.toMap(
                                Component::name,
                                t -> (Comparable) t.get()
                        ));
                Map<String, Object> rIds = r.stream()
                        .collect(Collectors.toMap(
                                Component::name,
                                Supplier::get
                        ));
                for (String key : dimensions) {
                    int res = lIds.get(key).compareTo(rIds.get(key));
                    if (res != 0)
                        return res;
                }
                return 0;
            }
        };

        BiFunction<JoinTuple, Dataset.Tuple, JoinTuple> merger = new BiFunction<JoinTuple, Dataset.Tuple, JoinTuple>() {
            Integer id = 0;

            @Override
            public JoinTuple apply(JoinTuple joinTuple, Dataset.Tuple components) {
                joinTuple.getValues().putAll(Integer.toString(++id), components.values());
                return joinTuple;
            }
        };

        Iterator<Dataset> iterator = datasets.iterator();
        Stream<JoinTuple> result = iterator.next().get().map(components -> {
            JoinTuple joinTuple = new JoinTuple(components.ids());
            joinTuple.getValues().putAll("0", components.values());
            return joinTuple;
        });

        while (iterator.hasNext()) {
            result = StreamSupport.stream(
                    new JoinSpliterator<>(
                            keyComparator,
                            result.spliterator(),
                            iterator.next().get().spliterator(),
                            new Function<JoinTuple, List<Component>>() {
                                @Override
                                public List<Component> apply(JoinTuple l) {
                                    return (List) l.ids();
                                }
                            },
                            new Function<Dataset.Tuple, List<Component>>() {
                                @Override
                                public List<Component> apply(Dataset.Tuple r) {
                                    return (List) r.ids();
                                }
                            },
                            merger
                    ), false
            );
        }
        return result.map(tuple -> (Tuple) tuple);
    }
}
