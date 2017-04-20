package no.ssb.vtl.tools.rest.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.ssb.vtl.model.DataStructure;

/**
 * Json representation of a {@link no.ssb.vtl.model.DataStructure}.
 */
@Deprecated
public class StructureRepresentation {

    @JsonProperty
    private DataStructure dataStructure;

    public DataStructure getDataStructure() {
        return dataStructure;
    }

    public void setDataStructure(DataStructure dataStructure) {
        this.dataStructure = dataStructure;
    }
}
