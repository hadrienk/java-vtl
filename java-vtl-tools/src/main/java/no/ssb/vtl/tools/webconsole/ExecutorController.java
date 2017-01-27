package no.ssb.vtl.tools.webconsole;

import com.codepoetics.protonpack.Streamable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.script.VTLScriptEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.script.Bindings;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

/**
 * Simple execution service that allows executions of a VTL expression
 * and returns the context.
 */
@RequestMapping(
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        consumes = MediaType.ALL_VALUE
)
@RestController
public class ExecutorController {

    @Autowired
    public VTLScriptEngine vtlEngine;

    @Autowired
    @Qualifier("vtlBindings")
    public Bindings bindings;

    static class ErrorRepresentation {

        @JsonIgnore
        private Throwable t;

        public ErrorRepresentation(Throwable t) {
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

    @ExceptionHandler
    @ResponseStatus()
    public Object handleError(Throwable t) {
        return new ErrorRepresentation(t);
    }

    @RequestMapping(
            path = "/execute",
            method = RequestMethod.POST
    )
    public Set<String> execute(Reader script) throws IOException, ScriptException {
        vtlEngine.eval(script, bindings);
        return bindings.keySet();
    }

    @RequestMapping(
            path = "/dataset/{id}/structure",
            method = RequestMethod.GET
    )
    public Object getStructure(@PathVariable String id) {
        return bindings.get(id);
    }

    @RequestMapping(
            path = "/dataset/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (bindings.containsKey(id)) {
            bindings.remove(id);
            return ResponseEntity.ok(null);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(
            path = "/dataset/{id}/data",
            method = RequestMethod.GET
    )
    public Iterable<Map<String, Object>> getData(@PathVariable String id) {
        Object dataset = bindings.get(id);
        Streamable<Map<String, Object>> streamable = ((Dataset) dataset).map(dataPoints -> {
            Map<String, Object> map = Maps.newHashMap();
            for (DataPoint dataPoint : dataPoints) {
                map.put(dataPoint.getName(), dataPoint.get());
            }
            return map;
        });
        return () -> streamable.get().iterator();
    }
}
