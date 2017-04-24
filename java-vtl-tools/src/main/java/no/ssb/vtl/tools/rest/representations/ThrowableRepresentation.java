package no.ssb.vtl.tools.rest.representations;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Json representation of a {@link java.lang.Throwable}.
 */
public class ThrowableRepresentation {

    @JsonIgnore
    private Throwable t;

    public ThrowableRepresentation(Throwable t) {
        this.t = t;
    }

    public String getMessage() {
        return t.getMessage();
    }

    public String getStackTrace() {
        StringWriter sw = new StringWriter();
        t.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
