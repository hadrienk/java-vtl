package kohl.hadrien;

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

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;

import java.util.function.Supplier;

/**
 * Component represent values of a dataset.
 */
public abstract class Component<T> implements Supplier<T> {

    private final Class<T> clazz;

    public Component(Class<T> clazz) {
        this.clazz = checkNotNull(clazz);
    }

    protected abstract String name();

    @Override
    public int hashCode() {
        return get().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        return this.get().equals(((Component) other).get());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(get())
                .toString();
    }
}
