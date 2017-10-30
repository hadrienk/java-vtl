package no.ssb.vtl.model;

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

/*-
 * #%L
 * java-vtl-model
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.base.MoreObjects;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Component represent values of a dataset.
 */
public class Component {

    private final Class<?> type;
    private final Role role;

    // Use data structure static methods to create component.
    Component(Class<?> type, Role role) {
        this.type = checkNotNull(type);
        this.role = checkNotNull(role);
    }

    public Class<?> getType() {
        return type;
    }

    public Role getRole() {
        return role;
    }

    public boolean isIdentifier() {
        return getRole() == Role.IDENTIFIER;
    }

    public boolean isMeasure() {
        return getRole() == Role.MEASURE;
    }

    public boolean isAttribute() {
        return getRole() == Role.ATTRIBUTE;
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }


    @Override
    public int hashCode() {
        return System.identityHashCode(this);
    }


    @Override
    public String toString() {
        String name = getClass().getSimpleName() + "@" + Integer.toHexString(hashCode());
        return MoreObjects.toStringHelper(name)
                .addValue(type.getSimpleName())
                .addValue(role)
                .toString();
    }

    public enum Role {
        IDENTIFIER,
        MEASURE,
        ATTRIBUTE
    }
}
