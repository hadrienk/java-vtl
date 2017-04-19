package no.ssb.vtl.tools.webconsole.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.antlr.v4.runtime.RecognitionException;

import java.io.Reader;

/**
 * Json representation of syntax errors.
 *
 * @see no.ssb.vtl.tools.webconsole.ValidatorController#validate(Reader)
 */
public class SyntaxError {

    private final Integer startLine;
    private final Integer stopLine;
    private final Integer startColumn;
    private final Integer stopColumn;
    private final String message;
    private final RecognitionException exception;

    public SyntaxError(Integer startLine, Integer stopLine, Integer startColumn, Integer stopColumn, String message, RecognitionException exception) {
        this.startLine = startLine;
        this.stopLine = stopLine;
        this.startColumn = startColumn;
        this.stopColumn = stopColumn;
        this.message = message;
        this.exception = exception;
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
    public RecognitionException getException() {
        return exception;
    }
}
