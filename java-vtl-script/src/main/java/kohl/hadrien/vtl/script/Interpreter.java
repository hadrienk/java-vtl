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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import kohl.hadrien.*;
import kohl.hadrien.vtl.script.connector.Connector;
import kohl.hadrien.vtl.script.connector.ConnectorException;

import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Interpreter {

    public static void main(String... args) throws IOException {

        VTLScriptEngine vtlScriptEngine = new VTLScriptEngine(getFakeConnector());


        try (
                PrintStream output = System.out;
                PrintStream error = System.err;
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        ) {

            output.print(">");

            String read;
            while ((read = input.readLine()) != null) {
                try {
                    Object result = vtlScriptEngine.eval(read);
                    if (result instanceof Dataset) {
                        Dataset dataset = (Dataset) result;
                        printDataset(output, dataset);
                    }
                    output.println(result);
                    output.flush();
                } catch (ScriptException e) {
                    e.printStackTrace(error);
                } finally {
                    output.print(">");
                }
            }

        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    private static void printDataset(PrintStream output, Dataset dataset) {
        // Quickly print stream for now.
        for (String name : dataset.getDataStructure().names()) {
            output.print(name);
            output.print("[");
            output.print(dataset.getDataStructure().roles().get(name));
            output.print(",");
            output.print(dataset.getDataStructure().types().get(name));
            output.print("]");
        }
        output.println();
        for (Dataset.Tuple tuple : (Iterable<Dataset.Tuple>) dataset.stream()::iterator) {
            for (Component component : tuple) {
                output.print(component.get());
                output.print(",");
            }
            output.println();
        }
    }

    static Connector getFakeConnector() {

        DataStructure dataStructure = new DataStructure() {

            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return null;
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return ImmutableMap.of(
                        "id", Identifier.class,
                        "measure", Measure.class,
                        "attribute", Attribute.class
                );
            }

            @Override
            public Map<String, Class<?>> types() {
                return ImmutableMap.of(
                        "id", String.class,
                        "measure", String.class,
                        "attribute", String.class
                );
            }

            @Override
            public Set<String> names() {
                return ImmutableSet.of("id", "measure", "attribute");
            }
        };

        return new Connector() {

            @Override
            public boolean canHandle(String identifier) {
                return true;
            }

            @Override
            public Dataset getDataset(String identifier) throws ConnectorException {
                return new Dataset() {

                    @Override
                    public Stream<Tuple> get() {

                        return IntStream.rangeClosed(1, 100).boxed()
                                .map(integer -> {
                                    Identifier<Integer> identifier = new Identifier<Integer>() {
                                        @Override
                                        public String name() {
                                            return "id";
                                        }

                                        @Override
                                        public Class<?> type() {
                                            return Integer.class;
                                        }

                                        @Override
                                        public Class<? extends Component<Integer>> role() {
                                            return this.getClass();
                                        }

                                        @Override
                                        public Integer get() {
                                            return integer;
                                        }
                                    };

                                    Measure<String> measure = new Measure<String>() {
                                        @Override
                                        public String name() {
                                            return "measure";
                                        }

                                        @Override
                                        public Class<?> type() {
                                            return String.class;
                                        }

                                        @Override
                                        public Class<? extends Component<String>> role() {
                                            return this.getClass();
                                        }

                                        @Override
                                        public String get() {
                                            return "measure" + integer;
                                        }
                                    };

                                    Attribute<String> attribute = new Attribute<String>() {
                                        @Override
                                        public String name() {
                                            return "attribute";
                                        }

                                        @Override
                                        public Class<?> type() {
                                            return String.class;
                                        }

                                        @Override
                                        public Class<? extends Component<String>> role() {
                                            return this.getClass();
                                        }

                                        @Override
                                        public String get() {
                                            return "attribute" + integer;
                                        }
                                    };

                                    return Tuple.create(Arrays.asList(
                                            identifier, measure, attribute
                                    ));

                                });
                    }

                    @Override
                    public DataStructure getDataStructure() {
                        return dataStructure;
                    }
                };

            }

            @Override
            public Dataset putDataset(String identifier, Dataset dataset) throws ConnectorException {
                return dataset;
            }
        };
    }

}
