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

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Ints;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents a VTL Function.
 */
public interface VTLFunction<T extends VTLObject> extends VTLTyped<T> {

    Signature getSignature();

    T invoke(List<VTLObject> arguments);

    T invoke(Map<String, VTLObject> namedArguments);

    T invoke(List<VTLObject> arguments, Map<String, VTLObject> namedArguments);

    abstract class Signature extends ForwardingMap<String, Argument<?>> {

        private final int optionalSize;
        private final int requiredSize;

        // @formatter:off
        private Signature(int optionalSize, int requiredSize) {
            this.optionalSize = optionalSize;this.requiredSize = requiredSize;}

        public static Builder builder() {
            return new Builder();
        }

        public int getOptionalSize() {
            return optionalSize;
        }
        // @formatter:on

        public int getRequiredSize() {
            return requiredSize;
        }

        public static class Builder {
            private ImmutableMap.Builder<String, Argument<?>> builder = ImmutableMap.builder();

            public Builder addArgument(String name, Class<? extends VTLObject> type, boolean required) {
                builder.put(name, new Argument<>(type, required));
                return this;
            }

            public Builder addArgument(String name, Class<? extends VTLObject> type) {
                builder.put(name, new Argument<>(type));
                return this;
            }

            public Builder addArgument(String name, Argument<?> argument) {
                builder.put(name, argument);
                return this;
            }

            public Builder addArgument(Entry<? extends String, ? extends Argument<?>> entry) {
                builder.put(entry);
                return this;
            }

            public Builder addArguments(Map<? extends String, ? extends Argument<?>> map) {
                builder.putAll(map);
                return this;
            }

            public Builder addArguments(Iterable<Entry<? extends String, ? extends Argument<?>>> entries) {
                builder.putAll(entries);
                return this;
            }

            public Signature build() {
                ImmutableMap<String, Argument<?>> immutableMap = builder.build();

                long requiredSize = immutableMap.values().stream().filter(Argument::isRequired).count();
                long optionalSize = immutableMap.values().stream().filter(Argument::isOptional).count();

                return new Signature(Ints.checkedCast(optionalSize), Ints.checkedCast(requiredSize)) {
                    @Override
                    protected Map<String, Argument<?>> delegate() {
                        return immutableMap;
                    }
                };
            }
        }
    }

    class Argument<A extends VTLObject> implements VTLTyped<A> {

        private final Class<A> type;
        private final boolean required;

        public Argument(Class<A> type) {
            this(type, true);
        }

        public Argument(Class<A> type, boolean required) {
            this.type = checkNotNull(type);
            this.required = required;
        }

        public boolean isRequired() {
            return required;
        }

        public boolean isOptional() {
            return !isRequired();
        }

        @Override
        public Class<A> getVTLType() {
            return type;
        }
    }
}
