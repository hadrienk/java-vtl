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
import com.google.common.collect.Lists;
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import kohl.hadrien.*;
import kohl.hadrien.vtl.script.connector.Connector;
import kohl.hadrien.vtl.script.connector.ConnectorException;
import kohl.hadrien.vtl.script.interpreter.VTLValidator;

import java.io.*;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.String.format;

/**
 * A VTL interpreter.
 */
public class Interpreter implements Runnable {

    private static final String MOTD = "" +
            "Java VTL interpreter version " + Interpreter.class.getPackage().getImplementationVersion() + ".\n" +
            "Type .help for command list.";

    private final VTLValidator validator;
    private final ConsoleReader console;
    private final VTLScriptEngine vtlScriptEngine;

    public Interpreter(String... args) throws IOException {

        console = setupConsole();
        vtlScriptEngine = setupEngine();
        validator = new VTLValidator();

    }

    public static void main(String... args) throws IOException {

        Interpreter interpreter = new Interpreter();
        Thread thread = new Thread(interpreter);

        try {
            thread.start();
            thread.join();
        } catch (Throwable t) {
            thread.interrupt();
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
            public BiFunction<String, Object, Component> converter() {
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
                    public Set<List<Identifier>> cartesian() {
                        return null;
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

    private VTLScriptEngine setupEngine() {
        return new VTLScriptEngine(getFakeConnector());
    }

    private ConsoleReader setupConsole() throws IOException {
        ConsoleReader console;
        console = new ConsoleReader(
                "Java VTL",
                new FileInputStream(FileDescriptor.in),
                new FileOutputStream(FileDescriptor.out),
                TerminalFactory.create()
        );

        console.setPrompt("vtl> ");
        return console;
    }

    @Override
    public void run() {
        try {
            console.println(MOTD);

            repl();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                console.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void repl() throws IOException {

        PrintWriter output = new PrintWriter(console.getOutput());
        PrintStream error = System.err;
        String read;
        while ((read = console.readLine()) != null) {

            if ("".equals(read))
                continue;

            // Commands.
            if (read.trim().startsWith(".")) {
                if (!executeCommand(read.trim()))
                    break;
                continue;
            }


            if (!validator.isValidStatement(read)) {
                validator.getErrors().forEach(error::println);
                continue;
            }

            try {
                Object result = vtlScriptEngine.eval(read);

                if (result instanceof Dataset)
                    printDataset((Dataset) result);

                output.println(result);
                output.flush();
            } catch (Throwable t) {
                error.println(t.getMessage());
            }
        }
        console.flush();
    }

    private boolean executeCommand(String command) throws IOException {
        if (command.startsWith(".help"))
            return printHelp();

        if (command.startsWith(".exit") || command.startsWith(".quit"))
            return false;

        console.println("Unknown command" + command);
        return true;

    }

    private boolean printHelp() throws IOException {
        console.println(".quit");
        console.println(".exit");
        console.println(".show datasetExpression");
        return true;
    }

    private void printDataset(Dataset dataset) throws IOException {
        Map<String, Class<? extends Component>> roles = dataset.getDataStructure().roles();
        Map<String, Class<?>> types = dataset.getDataStructure().types();

        // Header
        List<String> columns = Lists.newArrayList();
        for (String name : dataset.getDataStructure().names())
            columns.add(format("%s[%s,%s]", name, roles.get(name).getSimpleName(), types.get(name).getSimpleName()));
        console.println(columns.stream().collect(Collectors.joining(",")));

        // Rows
        Iterator<Dataset.Tuple> iterator = dataset.stream().iterator();
        while (iterator.hasNext()) {
            columns.clear();
            Dataset.Tuple row = iterator.next();
            Map<String, Object> asMap = row.stream().collect(Collectors.toMap(
                    Component::name, Component::get
            ));
            for (String name : dataset.getDataStructure().names())
                columns.add(asMap.get(name).toString());

            console.println(columns.stream().collect(Collectors.joining(",")));
        }
    }

}
