package no.ssb.vtl.tools.rest.controllers;

import com.google.common.collect.Lists;
import no.ssb.vtl.parser.VTLLexer;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.tools.rest.representations.SyntaxErrorRepresentation;
import no.ssb.vtl.tools.rest.representations.ThrowableRepresentation;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
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

    @ExceptionHandler
    @ResponseStatus()
    public Object handleError(Throwable t) {
        return new ThrowableRepresentation(t);
    }

    /**
     * Validate a VTL Expression
     */
    @RequestMapping(
            path = "/validate",
            method = RequestMethod.POST,
            consumes = {
                    MediaType.ALL_VALUE,
                    MediaType.TEXT_PLAIN_VALUE
            }
    )
    @Cacheable(cacheNames = "expressions", key = "@hasher.apply(#expression)")
    public List<SyntaxErrorRepresentation> validate(
            @RequestBody(required = false) String expression
    ) throws IOException {

        if (expression == null)
            return Collections.emptyList();

        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(expression));
        VTLParser parser = new VTLParser(new BufferedTokenStream(lexer));

        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        parser.setErrorHandler(new GrammarParserInterpreter.BailButConsumeErrorStrategy());

        ErrorListener errorListener = new ErrorListener();
        lexer.addErrorListener(errorListener);
        parser.addErrorListener(new DiagnosticErrorListener());
        parser.addErrorListener(errorListener);
    
        //Should not be reported as an error. The expression works TODO: A warning might be more appropriate
//        parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);

        parser.start();

        return errorListener.getSyntaxErrors();

    }

    /**
     * An {@link org.antlr.v4.runtime.ANTLRErrorListener} implementation that collects
     * errors and create {@link SyntaxErrorRepresentation}
     */
    public static class ErrorListener extends BaseErrorListener {

        private List<SyntaxErrorRepresentation> syntaxErrors = Lists.newArrayList();

        public List<SyntaxErrorRepresentation> getSyntaxErrors() {
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
            syntaxErrors.add(new SyntaxErrorRepresentation(startLine, stopLine, startColumn, stopColumn, msg, e));
        }
    }
}
