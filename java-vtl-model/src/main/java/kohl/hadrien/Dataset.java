package kohl.hadrien;

/*-
 * #%L
 * java-vtl-model
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

import com.codepoetics.protonpack.Streamable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingList;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A data structure that allows relational operations.
 */
public interface Dataset extends Streamable<Dataset.Tuple> {

    Set<List<Identifier>> cartesian();


    /**
     * Returns the data structure of the DataSet.
     */
    DataStructure getDataStructure();

    interface Tuple extends List<Component>, Comparable<Tuple> {

        static Tuple create(List<Component> components) {
            return new AbstractTuple() {
                @Override
                protected List<Component> delegate() {
                    return components;
                }
            };
        }

        List<Identifier> ids();

        List<Component> values();

        Tuple combine(Tuple tuple);

    }

    abstract class AbstractTuple extends ForwardingList<Component> implements Tuple {

        @Override
        public List<Identifier> ids() {
            return stream()
                    .filter(component -> component.role().isAssignableFrom(Identifier.class))
                    .map(component -> new Identifier() {
                        @Override
                        public String name() {
                            return component.name();
                        }

                        @Override
                        public Class<?> type() {
                            return component.type();
                        }

                        @Override
                        public Class<? extends Component> role() {
                            return component.role();
                        }

                        @Override
                        public Object get() {
                            return component.get();
                        }

                        @Override
                        public String toString() {
                            return MoreObjects.toStringHelper(role())
                                    .add(name(), get().toString())
                                    .toString();
                        }
                    })
                    .collect(Collectors.toList());
        }

        @Override
        public List<Component> values() {
            return stream()
                    .filter(component -> !component.role().isAssignableFrom(Identifier.class))
                    .map(component -> {
                        // TODO
                        if (component.role().isAssignableFrom(Measure.class)) {
                            return new Measure() {
                                @Override
                                public String name() {
                                    return component.name();
                                }

                                @Override
                                public Class<?> type() {
                                    return component.type();
                                }

                                @Override
                                public Class<? extends Component> role() {
                                    return component.role();
                                }

                                @Override
                                public Object get() {
                                    return component.get();
                                }

                                @Override
                                public String toString() {
                                    return MoreObjects.toStringHelper(role())
                                            .add("name", name())
                                            .addValue(get()).toString();
                                }
                            };
                        } else {
                            return new Attribute() {
                                @Override
                                public String name() {
                                    return component.name();
                                }

                                @Override
                                public Class<?> type() {
                                    return component.type();
                                }

                                @Override
                                public Class<? extends Component> role() {
                                    return component.role();
                                }

                                @Override
                                public Object get() {
                                    return component.get();
                                }

                                @Override
                                public String toString() {
                                    return MoreObjects.toStringHelper(role())
                                            .add("name", name())
                                            .addValue(get()).toString();
                                }
                            };
                        }
                    })
                    .collect(Collectors.toList());
        }

        @Override
        public int compareTo(Tuple o) {
            Comparator comparator = Comparator.naturalOrder();
            Iterator<Identifier> li = this.ids().iterator();
            Iterator<Identifier> ri = o.ids().iterator();
            int i = 0;
            while (li.hasNext() && ri.hasNext()) {
                i = comparator.compare(li.next().get(), ri.next().get());
                if (i != 0)
                    return i;
            }
            return i;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(Tuple.class)
                    .add("id", ids())
                    .add("values", values())
                    .toString();
        }

        @Override
        public int hashCode() {
            return ids().hashCode();
        }


        @Override
        public Tuple combine(Tuple tuple) {
            return new AbstractTuple() {

                @Override
                protected List<Component> delegate() {
                    return new CombinedList<>(this, tuple.values());
                }

            };
        }
    }

    class CombinedList<T, A extends T, B extends T> extends AbstractList<T> implements RandomAccess {

        final List<A> a;
        final List<B> b;

        public CombinedList(List<A> a, List<B> b) {
            this.a = checkNotNull(a);
            this.b = checkNotNull(b);
        }

        @Override
        public int size() {
            return a.size() + b.size();
        }

        @Override
        public T get(int index) {
            if (index < a.size())
                return a.get(index);
            return b.get(index - a.size());
        }

        @Override
        public T set(int index, T element) {
            if (index < a.size())
                return a.set(index, (A) element);
            return b.set(index - a.size(), (B) element);
        }
    }

}
