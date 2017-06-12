package no.ssb.vtl.test;

import com.google.common.collect.Lists;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import org.assertj.core.api.Condition;

import java.util.List;

import static no.ssb.vtl.model.Component.Role;
import static org.assertj.core.api.Assertions.allOf;

/**
 * Conditions for Dataset
 */
public class ComponentConditions {

    public static Condition<Dataset> datasetThat() {
        return null;
    }

    /**
     * Private factory method.
     */
    private static Condition<Component> componentWith(
            Condition<Component> name,
            Condition<Component> role,
            Condition<Component> type) {
        List<Condition<Component>> conditions = Lists.newArrayList();
        if (name != null)
            conditions.add(name);
        if (role != null)
            conditions.add(role);
        if (type != null)
            conditions.add(type);
        return allOf(conditions);
    }

    public static Condition<Component> identifierWith(String name) {
        return componentWith(Role.IDENTIFIER);
    }

    public static Condition<Component> identifierWith(Class<?> type) {
        return componentWith(Role.IDENTIFIER, type);
    }

    public static Condition<Component> attributeWith(String name) {
        return componentWith(Role.ATTRIBUTE);
    }

    public static Condition<Component> componentWith(Role role) {
        return new Condition<>(
                c -> c.getRole().equals(role),
                "component with role: [%s]", role);
    }

    public static Condition<Component> componentWith(Class<?> type) {
        return new Condition<>(
                c -> c.getType().isAssignableFrom(type),
                "component with type: [%s]", type);
    }

    public static Condition<Component> componentWith(Role role, Class<?> type) {
        return allOf(
                componentWith(role),
                componentWith(type)
        );
    }
}
