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
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import kohl.hadrien.*;
import kohl.hadrien.vtl.script.connector.Connector;
import kohl.hadrien.vtl.script.connector.ConnectorException;
import kohl.hadrien.vtl.script.interpreter.VTLValidator;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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

    private static Connector getFakeConnector() {

        DataStructure dataStructure = new DataStructure(ImmutableMap.of(
                "id", Identifier.class,
                "measure", Measure.class,
                "attribute", Attribute.class
        ));

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
                                    Identifier<Integer> identifier = new Identifier<Integer>(Integer.class) {
                                        @Override
                                        protected String name() {
                                            return "id";
                                        }

                                        @Override
                                        public Integer get() {
                                            return integer;
                                        }
                                    };

                                    Measure<String> measure = new Measure<String>(String.class) {
                                        @Override
                                        protected String name() {
                                            return "measure";
                                        }

                                        @Override
                                        public String get() {
                                            return "measure" + integer;
                                        }
                                    };

                                    Attribute<String> attribute = new Attribute<String>(String.class) {
                                        @Override
                                        protected String name() {
                                            return "attribute";
                                        }

                                        @Override
                                        public String get() {
                                            return "attribute" + integer;
                                        }
                                    };

                                    return new AbstractTuple() {


                                        @Override
                                        public List<Identifier> ids() {
                                            return Arrays.asList(identifier);
                                        }

                                        @Override
                                        public List<Component> values() {
                                            return Arrays.asList(measure, attribute);
                                        }
                                    };
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
        console.println(dataset.getDataStructure().entrySet().stream()
                .map(entry -> {
                    String key = entry.getKey();
                    String role = entry.getValue().getSimpleName();
                    return String.format("%s[%s,%s]", key, role, "TODO");
                })
                .collect(Collectors.joining(","))
        );
        console.printColumns(dataset.stream().map(tuple ->
                tuple.stream()
                        .map(Supplier::get)
                        .map(Object::toString)
                        .collect(Collectors.joining(","))
        ).collect(Collectors.toList()));
    }

}
