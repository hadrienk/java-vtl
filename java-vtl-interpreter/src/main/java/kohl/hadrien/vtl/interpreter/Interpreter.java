package kohl.hadrien.vtl.interpreter;

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
import kohl.hadrien.vtl.model.*;
import kohl.hadrien.vtl.script.VTLScriptEngine;
import kohl.hadrien.vtl.connector.Connector;
import kohl.hadrien.vtl.connector.ConnectorException;

import javax.script.ScriptException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Random;
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
            t.printStackTrace(System.err);
        }

    }

    private static void printDataset(PrintStream output, Dataset dataset) {
        // Quickly print stream for now.
        for (String name : dataset.getDataStructure().names()) {
            output.print(name);
            output.print("[");
            output.print(dataset.getDataStructure().roles().get(name).getSimpleName());
            output.print(",");
            output.print(dataset.getDataStructure().types().get(name).getSimpleName());
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
                return (o, aClass) -> o;
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

            Random random = new Random();

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

                                    //Integer id = "random".equals(identifier) ? random.nextInt() : integer;
                                    Integer id = random.nextInt(Integer.SIZE - 1);
                                    ImmutableMap<String, Object> values = ImmutableMap.of(
                                            "id", id,
                                            "measure", "measure" + integer,
                                            "attribute", "attribute" + integer
                                    );
                                    return dataStructure.wrap(values);
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
