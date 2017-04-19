package no.ssb.vtl.tools.webconsole;

import com.google.common.collect.Lists;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.tools.webconsole.entity.SyntaxError;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * A validator service that returns syntax errors for expressions.
 */
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.ALL_VALUE
)
@RestController
public class ValidatorController {


    /**
     * Validate a VTL Expression
     *
     * @param expression
     * @return a list of Error if any
     * @throws IOException
     */
    @RequestMapping(
            path = "/validate",
            method = RequestMethod.POST
    )
    @Cacheable(cacheNames="expressions", key="@hasher.apply(#expression)")
    public List<SyntaxError> validate(@RequestBody String expression) throws IOException {

        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new BufferedTokenStream(lexer));

        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        parser.setErrorHandler(new GrammarParserInterpreter.BailButConsumeErrorStrategy());

        ErrorListener errorListener = new ErrorListener();
        lexer.addErrorListener(errorListener);
        parser.addErrorListener(new DiagnosticErrorListener());
        parser.addErrorListener(errorListener);

        parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);

        parser.start();

        return errorListener.getSyntaxErrors();

    }

    /**
     * An {@link org.antlr.v4.runtime.ANTLRErrorListener} implementation that collects errors and create {@link SyntaxError}
     */
    public static class ErrorListener extends BaseErrorListener {

        private List<SyntaxError> syntaxErrors = Lists.newArrayList();

        public List<SyntaxError> getSyntaxErrors() {
            return syntaxErrors;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            int startLine = line, stopLine = line;
            int startColumn = charPositionInLine, stopColumn = charPositionInLine;
            if (offendingSymbol instanceof Token) {
                Token symbol = (Token) offendingSymbol;
                int start = symbol.getStartIndex();
                int stop = symbol.getStopIndex();
                if (start >= 0 && stop >= 0) {
                    stopColumn = startColumn + (stop - start) + 1;
                }
            }
            syntaxErrors.add(new SyntaxError(startLine, stopLine, startColumn, stopColumn, msg, e));
        }
    }
}
