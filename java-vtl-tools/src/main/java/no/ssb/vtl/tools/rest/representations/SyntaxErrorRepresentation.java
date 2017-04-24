package no.ssb.vtl.tools.rest.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;
import no.ssb.vtl.tools.rest.controllers.ValidatorController;
import org.antlr.v4.runtime.RecognitionException;

/**
 * Json representation of syntax errors.
 *
 * @see ValidatorController
 */
public class SyntaxErrorRepresentation {

    private final Integer startLine;
    private final Integer stopLine;
    private final Integer startColumn;
    private final Integer stopColumn;
    private final String message;
    private final ThrowableRepresentation exception;

    public SyntaxErrorRepresentation(Integer startLine, Integer stopLine, Integer startColumn, Integer stopColumn, String message, RecognitionException exception) {
        this.startLine = startLine;
        this.stopLine = stopLine;
        this.startColumn = startColumn;
        this.stopColumn = stopColumn;
        this.message = message;

        ThrowableRepresentation throwableRepresentation = null;
        if (exception != null)
            throwableRepresentation = new ThrowableRepresentation(exception);
        this.exception = throwableRepresentation;
    }

    public Integer getStartLine() {
        return startLine;
    }

    public Integer getStopLine() {
        return stopLine;
    }

    public Integer getStartColumn() {
        return startColumn;
    }

    public Integer getStopColumn() {
        return stopColumn;
    }

    public String getMessage() {
        return message;
    }

    @JsonIgnore
    public ThrowableRepresentation getException() {
        return exception;
    }
}
