package kohl.hadrien.console.server;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import kohl.hadrien.vtl.parser.VTLLexer;
import kohl.hadrien.vtl.parser.VTLParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.tool.GrammarParserInterpreter;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by hadrien on 08/12/2016.
 */
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.ALL_VALUE
)
@RestController
public class ValidatorController {


    @RequestMapping(
            path = "/validate",
            method = RequestMethod.POST
    )
    public List<Error> validate(Reader script) throws IOException {

        VTLLexer lexer = new VTLLexer(new ANTLRInputStream(script));
        VTLParser parser = new VTLParser(new BufferedTokenStream(lexer));

        lexer.removeErrorListeners();
        parser.removeErrorListeners();

        parser.setErrorHandler(new GrammarParserInterpreter.BailButConsumeErrorStrategy());

        ErrorListener errorListener = new ErrorListener();
        lexer.addErrorListener(errorListener);
        parser.addErrorListener(errorListener);

        parser.start();


        return errorListener.getErrors();

    }

    public static class Error {

        private final Integer line;
        private final Integer column;
        private final String message;
        private final RecognitionException exception;

        public Error(Integer line, Integer column, String message, RecognitionException exception) {
            this.line = line;
            this.column = column;
            this.message = message;
            this.exception = exception;
        }

        public Integer getLine() {
            return line;
        }

        public Integer getColumn() {
            return column;
        }

        public String getMessage() {
            return message;
        }

        @JsonIgnore
        public RecognitionException getException() {
            return exception;
        }
    }

    public static class ErrorListener extends BaseErrorListener {

        private List<Error> errors = Lists.newArrayList();

        public List<Error> getErrors() {
            return errors;
        }

        @Override
        public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
            errors.add(new Error(line, charPositionInLine, msg, e));
        }
    }
}
