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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Datastructure of a {@link Dataset}.
 * <p>
 * In addition to defining the type and roles of variable in the data sets, the data structure decouples
 * the creation of the {@link kohl.hadrien.Dataset.Tuple}.
 */
public abstract class DataStructure {

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Class<? extends Component> role1, Class<?> type1) {
        return new DataStructure() {
            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return converter;
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return ImmutableMap.of(name1, role1);
            }

            @Override
            public Map<String, Class<?>> types() {
                return ImmutableMap.of(name1, type1);
            }

            @Override
            public Set<String> names() {
                return ImmutableSet.of(name1);
            }
        };
    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Class<? extends Component> role1, Class<?> type1,
                                   String name2, Class<? extends Component> role2, Class<?> type2) {
        return new DataStructure() {
            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return converter;
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return ImmutableMap.of(name1, role1, name2, role2);
            }

            @Override
            public Map<String, Class<?>> types() {
                return ImmutableMap.of(name1, type1, name2, type2);
            }

            @Override
            public Set<String> names() {
                return ImmutableSet.of(name1, name2);
            }
        };
    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Class<? extends Component> role1, Class<?> type1,
                                   String name2, Class<? extends Component> role2, Class<?> type2,
                                   String name3, Class<? extends Component> role3, Class<?> type3) {
        return new DataStructure() {
            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return converter;
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return ImmutableMap.of(name1, role1, name2, role2, name3, role3);
            }

            @Override
            public Map<String, Class<?>> types() {
                return ImmutableMap.of(name1, type1, name2, type2, name3, type3);
            }

            @Override
            public Set<String> names() {
                return ImmutableSet.of(name1, name2, name3);
            }
        };
    }

    /**
     * Creates a new data structure.
     */
    public static DataStructure of(BiFunction<Object, Class<?>, ?> converter,
                                   String name1, Class<? extends Component> role1, Class<?> type1,
                                   String name2, Class<? extends Component> role2, Class<?> type2,
                                   String name3, Class<? extends Component> role3, Class<?> type3,
                                   String name4, Class<? extends Component> role4, Class<?> type4) {
        return new DataStructure() {
            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return converter;
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return ImmutableMap.of(name1, role1, name2, role2, name3, role3, name4, role4);
            }

            @Override
            public Map<String, Class<?>> types() {
                return ImmutableMap.of(name1, type1, name2, type2, name3, type3, name4, type4);
            }

            @Override
            public Set<String> names() {
                return ImmutableSet.of(name1, name2, name3, name4);
            }
        };
    }


    public abstract BiFunction<Object, Class<?>, ?> converter();

    public Component wrap(String name, Object value) {
        checkArgument(types().containsKey(name) && roles().containsKey(name),
                "could not find %s in data structure %s", name, this);

        return new AbstractComponent() {
            @Override
            public String name() {
                return name;
            }

            @Override
            public Class<?> type() {
                return types().get(name);
            }

            @Override
            public Class<? extends Component> role() {
                return roles().get(name);
            }

            @Override
            public Object get() {
                return converter().apply(value, type());
            }
        };
    }

    public Dataset.Tuple wrap(Map<String, Object> objects) {

        List<Component> components = Lists.newArrayList();
        for (Map.Entry<String, Object> entry : objects.entrySet())
            components.add(wrap(entry.getKey(), entry.getValue()));

        return Dataset.Tuple.create(components);

    }

    public abstract Map<String, Class<? extends Component>> roles();

    public abstract Map<String, Class<?>> types();

    public abstract Set<String> names();

    /**
     * A forwarding data structure.
     * <p>
     * All calls are forwarded to the instance returned by the abstract method
     * {@link ForwardingDataStructure#delegate()}.
     */
    public abstract class ForwardingDataStructure extends DataStructure {

        protected abstract DataStructure delegate();

        @Override
        public BiFunction<Object, Class<?>, ?> converter() {
            return delegate().converter();
        }

        @Override
        public Dataset.Tuple wrap(Map<String, Object> objects) {
            return delegate().wrap(objects);
        }

        @Override
        public Map<String, Class<? extends Component>> roles() {
            return delegate().roles();
        }

        @Override
        public Map<String, Class<?>> types() {
            return delegate().types();
        }

        @Override
        public Set<String> names() {
            return delegate().names();
        }

    }
}
