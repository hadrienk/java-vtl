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
import jline.TerminalFactory;
import jline.console.ConsoleReader;
import kohl.hadrien.vtl.connector.Connector;
import kohl.hadrien.vtl.connector.ConnectorException;
import kohl.hadrien.vtl.model.*;
import kohl.hadrien.vtl.script.VTLScriptEngine;
import org.fusesource.jansi.AnsiConsole;

import javax.script.ScriptException;
import java.io.*;
import java.util.Map;
import java.util.Random;
import java.util.Set;
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
                AnsiConsole.systemInstall();
                console.getOutput().write(validator.getError());
                AnsiConsole.systemUninstall();
                continue;
            }

            try {
                Object result = vtlScriptEngine.eval(read);

                if (result instanceof Dataset)
                    printDataset(console, (Dataset) result);

                output.println(result);
                output.flush();
            } catch (ScriptException se) {
                error.println(se.getMessage());
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

    private void printDataset(ConsoleReader console, Dataset dataset) throws IOException {
        Map<String, Class<? extends Component>> roles = dataset.getDataStructure().roles();
        Map<String, Class<?>> types = dataset.getDataStructure().types();
        Set<String> names = dataset.getDataStructure().names();

        // Header
        String header = names.stream()
                .map(name -> format(
                        "%s[%s,%s]",
                        name,
                        roles.get(name).getSimpleName(),
                        types.get(name).getSimpleName()
                        )
                )
                .collect(Collectors.joining(","));

        Stream<String> rows = dataset.stream().map(row -> {
            Map<String, Object> asMap = row.stream().collect(Collectors.toMap(
                    Component::name, Component::get
            ));
            return names.stream()
                    .map(asMap::get)
                    .map(Object::toString)
                    .collect(Collectors.joining(","));
        });

        Integer width = console.getTerminal().getWidth() - 1;
        console.println(header);
        for (String row : (Iterable<? extends String>) rows::iterator) {

            if (width-- > 0)
                console.println(row);

            if (width <= 0) {
                console.print("...");
                console.flush();
                int c = console.readCharacter();
                if (c == '\r' || c == '\n')
                    width = 1;
                else if (c == 'q')
                    break;
                else
                    width = console.getTerminal().getWidth() - 1;
            }
        }
    }

}
