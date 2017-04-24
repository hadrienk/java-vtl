package no.ssb.vtl.tools.rest.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * Json representation of the result of an execution.
 */
public class ResultRepresentation {

    @JsonProperty
    private List<DatasetRepresentation> datasets = Lists.newArrayList();

    public List<DatasetRepresentation> getDatasets() {
        return datasets;
    }

    public void setDatasets(List<DatasetRepresentation> datasets) {
        this.datasets = datasets;
    }

}
