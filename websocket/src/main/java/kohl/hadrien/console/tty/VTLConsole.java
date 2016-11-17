package kohl.hadrien.console.tty;

import io.termd.core.readline.Function;
import io.termd.core.readline.Keymap;
import io.termd.core.readline.Readline;
import io.termd.core.tty.TtyConnection;
import io.termd.core.util.Helper;
import kohl.hadrien.console.tty.parsing.ParsingResult;
import kohl.hadrien.console.tty.parsing.SyntaxError;
import kohl.hadrien.console.tty.parsing.SyntaxErrorListener;
import kohl.hadrien.vtl.parser.VTLLexer;
import kohl.hadrien.vtl.parser.VTLParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Consumer;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A termd interpreter.
 */
public class VTLConsole implements Consumer<TtyConnection> {

    private static final String MOTD = "" +
            "Java VTL interpreter version "
            + VTLConsole.class.getPackage().getImplementationVersion() + ".\n" +
            "Type .help for command list.\n\n";

    private static final String VTL = "" +
            "    ___       ___       ___   \n" +
            "   /\\__\\     /\\  \\     /\\__\\  \n" +
            "  /:/ _/_    \\:\\  \\   /:/  /  \n" +
            " |::L/\\__\\   /::\\__\\ /:/__/   \n" +
            " |::::/  /  /:/\\/__/ \\:\\  \\   \n" +
            "  L;;/__/   \\/__/     \\:\\__\\  \n" +
            "                       \\/__/\n";

    private ScriptEngine engine;

    @Override
    public void accept(TtyConnection ttyConnection) {
        InputStream inputrc = Keymap.class.getResourceAsStream("inputrc");
        Keymap keymap = new Keymap(inputrc);
        Readline readline = new Readline(keymap);

        List<Function> functions = Helper.loadServices(Thread.currentThread().getContextClassLoader(), Function.class);
        for (io.termd.core.readline.Function function : functions) {
            readline.addFunction(function);
        }

        readline.addFunction(new Function() {
            @Override
            public String name() {
                return "test";
            }

            @Override
            public void apply(Readline.Interaction interaction) {
                System.err.print(interaction);
            }
        });

        ttyConnection.write(VTL);
        ttyConnection.write(MOTD);

        ScriptEngineManager manager = new ScriptEngineManager();
        engine = checkNotNull(manager.getEngineByName("VTLJava"));


        read(ttyConnection, readline);
    }

    private void read(TtyConnection ttyConnection, Readline readline) {
        readline.readline(ttyConnection, "vtl> ", line -> {
            try {
                if (!line.isEmpty()) {
                    ParsingResult parsingResult = parseVTL(line);
                    if (parsingResult.syntaxErrorListener.getSyntaxErrors().isEmpty())
                        engine.eval(line);

                    printErrors(ttyConnection, parsingResult);
                }
            } catch (ScriptException e) {
                StringWriter stack = new StringWriter();
                e.printStackTrace(new PrintWriter(stack));
                ttyConnection.write(stack.toString());
            } finally {
                read(ttyConnection, readline);
            }
        });
    }

    private void printErrors(TtyConnection connection, ParsingResult parsingResult) {
        for (SyntaxError syntaxError : parsingResult.syntaxErrorListener.getSyntaxErrors()) {
            int a, b; // Start and stop index
            RecognitionException cause = syntaxError.getException();
            if (cause instanceof LexerNoViableAltException) {
                a = ((LexerNoViableAltException) cause).getStartIndex();
                b = ((LexerNoViableAltException) cause).getStartIndex() + 1;
            } else {
                Token offendingToken = (Token) syntaxError.getOffendingSymbol();
                a = offendingToken.getStartIndex();
                b = offendingToken.getStopIndex() + 1;
            }
            // TODO: Highlight errors.
            String errorString = String.format("\033[31msyntax error:(%d,%d-%s) %s.\033[39;49m\n", 1, a, b, syntaxError.getMessage());
            connection.write(errorString);
        }
    }

    ParsingResult parseVTL(String text) {
        ANTLRInputStream input = new ANTLRInputStream(text);
        VTLLexer lexer = new VTLLexer(input);
        VTLParser parser = new VTLParser(new CommonTokenStream(lexer));

        SyntaxErrorListener listener = new SyntaxErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(listener);
        lexer.removeErrorListeners();
        lexer.addErrorListener(listener);

        ParseTree t = parser.start();
        return new ParsingResult(parser, t, listener);
    }
}
