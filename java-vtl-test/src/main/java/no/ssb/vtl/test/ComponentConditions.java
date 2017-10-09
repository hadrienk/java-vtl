package no.ssb.vtl.test;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

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
