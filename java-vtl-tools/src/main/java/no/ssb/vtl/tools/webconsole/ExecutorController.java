package no.ssb.vtl.tools.webconsole;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.VTLScriptEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static no.ssb.vtl.model.Component.*;

/**
 * Simple execution service that allows executions of a VTL expression
 * and returns the context.
 */
@CrossOrigin(origins = "http://localhost:8000/")
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

    @ExceptionHandler
    @ResponseStatus()
    public Object handleError(Throwable t) {
        return new ErrorRepresentation(t);
    }

    @RequestMapping(
            path = "/execute",
            method = RequestMethod.POST
    )
    public Collection<String> execute(Reader script) throws IOException, ScriptException {
        vtlEngine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);
        vtlEngine.eval(script);
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
        final Dataset dataset = (Dataset) bindings.get(id);
        DataStructure structure = dataset.getDataStructure();
        return () -> {
            return dataset.getData().map(dataPoints -> {
                Map<String, Object> map = Maps.newHashMap();
                for (Map.Entry<Component, VTLObject> entry : structure.asMap(dataPoints).entrySet()) {
                    map.put(structure.getName(entry.getKey()), entry.getValue());
                }
                return map;
            }).iterator();
        };
    }

    @RequestMapping(
            path = "/execute2",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object execute(@RequestBody ExecutionWrapper execution) throws ScriptException {

        Bindings bindings = vtlEngine.createBindings();

        for (DatasetWrapper dataset : execution.getDatasets()) {
            String name = dataset.getName();
            bindings.put(name, convertToDataset(dataset));
        }
        vtlEngine.eval(execution.getExpression(), bindings);

        ResultWrapper datasets = new ResultWrapper();
        for (Map.Entry<String, Object> entry : bindings.entrySet()) {
            datasets.getDatasets().add(
                    convertToDatasetWrapper(entry.getKey(), (Dataset) entry.getValue())
            );
        }
        return datasets;
    }

    private DatasetWrapper convertToDatasetWrapper(String name, Dataset dataset) {
        DatasetWrapper wrapper = new DatasetWrapper();
        wrapper.setName(name);
        List<ComponentWrapper> structure = wrapper.getStructure();
        for (Map.Entry<String, Component> component : dataset.getDataStructure().entrySet()) {
            ComponentWrapper componentWrapper = new ComponentWrapper();
            componentWrapper.setName(component.getKey());
            componentWrapper.setRole(component.getValue().getRole());
            componentWrapper.setType(component.getValue().getType());
            structure.add(componentWrapper);
        }
        List<List<Object>> data = dataset.get()
                .map(tuple -> tuple.stream()
                        .map(VTLObject::get).collect(Collectors.toList())
                ).collect(Collectors.toList());
        wrapper.setData(data);
        return wrapper;
    }

    private Dataset convertToDataset(DatasetWrapper dataset) {
        DataStructure.Builder builder = DataStructure.builder();
        for (ComponentWrapper component : dataset.getStructure()) {
            builder.put(component.getName(), component.getRole(), component.getClassType());
        }

        final DataStructure structure = builder.build();
        return new Dataset() {
            @Override
            public Stream<DataPoint> get() {
                return null;
            }

            @Override
            public DataStructure getDataStructure() {
                return structure;
            }

            @Override
            public Stream<DataPoint> getData() {
                return dataset.data.stream().map(objects -> {
                    DataPoint dataPoint = structure.wrap();
                    objects.stream().map(VTLObject::of).forEach(dataPoint::add);
                    return dataPoint;
                });
            }

            @Override
            public Optional<Map<String, Integer>> getDistinctValuesCount() {
                return Optional.empty();
            }

            @Override
            public Optional<Long> getSize() {
                return Optional.empty();
            }
        };
    }

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

    static class ResultWrapper {

        @JsonProperty
        private List<DatasetWrapper> datasets = Lists.newArrayList();

        public List<DatasetWrapper> getDatasets() {
            return datasets;
        }

        public void setDatasets(List<DatasetWrapper> datasets) {
            this.datasets = datasets;
        }

    }

    /**
     * Json wrapper for executions.
     */
    static class ExecutionWrapper {

        @JsonProperty
        private String expression;
        @JsonProperty
        private List<DatasetWrapper> datasets = Lists.newArrayList();

        public String getExpression() {
            return expression;
        }

        public void setExpression(String expression) {
            this.expression = expression;
        }

        public List<DatasetWrapper> getDatasets() {
            return datasets;
        }

        public void setDatasets(List<DatasetWrapper> datasets) {
            this.datasets = datasets;
        }
    }

    static class DatasetWrapper {

        @JsonProperty
        private String name;
        @JsonProperty
        private List<ComponentWrapper> structure = Lists.newArrayList();
        @JsonProperty
        private List<List<Object>> data = Lists.newArrayList();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ComponentWrapper> getStructure() {
            return structure;
        }

        public void setStructure(List<ComponentWrapper> structure) {
            this.structure = structure;
        }

        public List<List<Object>> getData() {
            return data;
        }

        public void setData(List<List<Object>> data) {
            this.data = data;
        }
    }

    static class ComponentWrapper {

        @JsonProperty
        private String name;
        @JsonProperty
        private Role role;
        private Class<?> type;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @JsonProperty
        public Class<?> getType() {
            return type;
        }

        @JsonProperty
        public void setType(Class<?> type) {
            this.type = type;
        }

        @JsonProperty
        public Class<?> getClassType() {
            return type;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

    }
}
