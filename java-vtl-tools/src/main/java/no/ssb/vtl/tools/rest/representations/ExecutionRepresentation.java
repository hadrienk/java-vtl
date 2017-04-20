package no.ssb.vtl.tools.rest.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Json representation of an execution.
 */
public class ExecutionRepresentation {

    @JsonProperty
    private String expression;

    @JsonProperty
    private List<DatasetRepresentation> datasets = Lists.newArrayList();

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public List<DatasetRepresentation> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<DatasetRepresentation> datasets) {
        this.datasets = datasets;
    }
}
