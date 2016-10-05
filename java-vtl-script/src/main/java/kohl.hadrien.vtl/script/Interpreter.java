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
import org.antlr.v4.runtime.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A VTL interpreter.
 */
public class Interpreter {

    static VTLLexer lexer;
    static VTLParser parser;
    private static SyntaxErrorReporter reporter;

    public static void main(String... args) throws IOException {

        VTLScriptEngine vtlScriptEngine = new VTLScriptEngine(getFakeConnector());

        String read;
        ConsoleReader console = new ConsoleReader(
                "Java VTL",
                new FileInputStream(FileDescriptor.in),
                new FileOutputStream(FileDescriptor.out),
                TerminalFactory.create()
        );
        console.setPrompt("vtl> ");
        console.setPaginationEnabled(true);
        console.setBellEnabled(true);

        PrintWriter output = new PrintWriter(console.getOutput());
        PrintStream error = System.err;

        while ((read = console.readLine()) != null) {

            if ("".equals(read))
                continue;

            if (!isStatementValid(read, output))
                continue;

            try {
                Object result = vtlScriptEngine.eval(read);

                if (result instanceof Dataset) {
                    Dataset dataset = (Dataset) result;
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

                output.println(result);
                output.flush();
            } catch (Throwable t) {
                error.println(t.getMessage());
            }
        }

    }

    private static boolean isStatementValid(String statement, PrintWriter output) {

        lexer = new VTLLexer(new ANTLRInputStream(""));
        parser = new VTLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        reporter = new SyntaxErrorReporter(System.err);

        parser.addErrorListener(reporter);

        try {
            parser.setInputStream(new ANTLRInputStream(statement));
            parser.reset();
            parser.statement();
        } catch (Throwable t) {
            // Ignore.
        } finally {
            lexer.reset();
            parser.reset();
        }
        return !reporter.hasFailed();
    }

    static Connector getFakeConnector() {

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

    static class SyntaxErrorReporter extends BaseErrorListener {

        private final PrintStream error;
        volatile boolean failed = false;

        SyntaxErrorReporter(PrintStream error) {
            this.error = checkNotNull(error);
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            failed = true;
            error.printf("syntax error:(%d,%d) %s.\n", line, charPositionInLine, msg);
        }

        public boolean hasFailed() {
            if (failed) {
                failed = false;
                return true;
            }
            return false;
        }
    }

}
