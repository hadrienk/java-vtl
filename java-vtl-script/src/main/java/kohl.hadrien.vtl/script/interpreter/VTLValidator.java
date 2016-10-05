package kohl.hadrien.vtl.script.interpreter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import kohl.hadrien.VTLLexer;
import kohl.hadrien.VTLParser;
import org.antlr.v4.runtime.*;

import java.util.List;

public class VTLValidator {

    private final VTLLexer lexer;
    private final VTLParser parser;
    private List<String> errors = Lists.newArrayList();

    public VTLValidator() {

        lexer = new VTLLexer(new ANTLRInputStream(""));
        parser = new VTLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        SyntaxErrorReporter reporter = new SyntaxErrorReporter();
        parser.addErrorListener(reporter);
    }

    public boolean isValidStatement(String statement) {
        try {
            errors.clear();
            parser.setInputStream(new ANTLRInputStream(statement));
            parser.reset();
            parser.statement();
        } catch (Throwable t) {
            // Ignore.
        }
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        ImmutableList<String> errors = ImmutableList.copyOf(this.errors);
        return errors;
    }

    private class SyntaxErrorReporter extends BaseErrorListener {
        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            errors.add(String.format("syntax error:(%d,%d) %s.\n", line, charPositionInLine, msg));
        }
    }
}
