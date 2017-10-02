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

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * Represents a VTL Function.
 */
public abstract class VTLFunction extends VTLExpression {

    private final Map<String, VTLExpression> context;

    protected VTLFunction(Map<String, VTLExpression> context) {
        this.context = context;
    }

    public static Builder create(Class<? extends VTLObject> returnType) {
        return new Builder(returnType);
    }

    @Override
    public Class<? extends VTLObject> getType() {
        return null;
    }

    @FunctionalInterface
    interface Body {
        VTLObject execute();
    }

    /**
     * Builder for VTLFunction.
     */
    private static class Builder {

        private final Class<? extends VTLObject> type;
        ImmutableMap.Builder<String, Class<? extends VTLObject>> signature;
        ImmutableMap.Builder<String, VTLObject> defaultValues;

        private Builder(Class<? extends VTLObject> type) {
            this.type = type;
        }

        public Builder withArgument(String name, Class<? extends VTLObject> type) {
            signature.put(name, type);
            return this;
        }

        public Builder withArgument(String name, VTLObject defaultValue) {
            defaultValues.put(name, defaultValue);
            return withArgument(name, defaultValue.getClass());
        }

        public VTLFunction build(Map<String, VTLExpression> context) {
            throw new UnsupportedOperationException("TODO");
        }
    }
}
