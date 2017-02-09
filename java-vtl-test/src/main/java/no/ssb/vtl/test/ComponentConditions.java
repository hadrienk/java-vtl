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
        return componentWith(name, Role.IDENTIFIER);
    }

    public static Condition<Component> identifierWith(String name, Class<?> type) {
        return componentWith(name, Role.IDENTIFIER, type);
    }

    public static Condition<Component> measureWith(String name) {
        return componentWith(name, Role.MEASURE);
    }

    public static Condition<Component> measureWith(String name, Class<?> type) {
        return componentWith(name, Role.MEASURE, type);
    }

    public static Condition<Component> attributeWith(String name) {
        return componentWith(name, Role.ATTRIBUTE);
    }

    public static Condition<Component> attributeWith(String name, Class<?> type) {
        return componentWith(name, Role.ATTRIBUTE, type);
    }

    public static Condition<Component> componentWith(String name) {
        return new Condition<>(
                c -> c.getName().equals(name),
                "component with name: [%s]", name);
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

    public static Condition<Component> componentWith(String name, Role role) {
        return componentWith(
                componentWith(name),
                componentWith(role),
                null
        );
    }

    public static Condition<Component> componentWith(String name, Role role, Class<?> type) {
        return componentWith(
                componentWith(name),
                componentWith(role),
                componentWith(type)
        );
    }
}
