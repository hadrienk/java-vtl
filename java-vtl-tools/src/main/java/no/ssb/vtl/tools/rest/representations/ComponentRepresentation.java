package no.ssb.vtl.tools.rest.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import no.ssb.vtl.model.Component;

/**
 * Json representation of a {@link no.ssb.vtl.model.Component}.
 */
public class ComponentRepresentation {

    @JsonProperty
    private String name;

    @JsonProperty
    private Component.Role role;

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

//    @JsonProperty
//    public Class<?> getClassType() {
//        return type;
//    }

    public Component.Role getRole() {
        return role;
    }

    public void setRole(Component.Role role) {
        this.role = role;
    }

}
