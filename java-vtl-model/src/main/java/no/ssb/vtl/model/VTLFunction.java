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

import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Strings.isNullOrEmpty;

/**
 * Represents a VTL Function.
 */
public abstract class VTLFunction extends VTLExpression {

    private final String id;
    private final Map<String, Class<? extends VTLObject>> typeSignature;
    private final Map<String, ? extends VTLObject> defaultValues;
    private final Class<? extends VTLObject> returnType;
    private final Body body;

    VTLFunction(
            String id,
            Class<? extends VTLObject> returnType,
            Map<String, Class<? extends VTLObject>> typeSignature,
            Map<String, ? extends VTLObject> defaultValues,
            Body body
    ) {
        this.id = id;
        this.returnType = returnType;
        this.typeSignature = typeSignature;
        this.defaultValues = defaultValues;
        this.body = body;
    }

    @Override
    public String toString() {
        MoreObjects.ToStringHelper toStringHelper = MoreObjects.toStringHelper(this)
                .add("id", this.id);
        for (String name : typeSignature.keySet()) {
            Class<? extends VTLObject> type = typeSignature.get(name);
            if (defaultValues.containsKey(name)) {
                VTLObject defaultValue = defaultValues.get(name);
                toStringHelper.add(
                        name,  type + " = " + defaultValue);
            } else {
                toStringHelper.add(name, type);
            }
        }
        toStringHelper.addValue(getType());
        return toStringHelper.toString();
    }

    public static Builder create(String id, Class<? extends VTLObject> returnType) {
        return new Builder(id, returnType);
    }

    @Override
    public Class<? extends VTLObject> getType() {
        return returnType;
    }

    @FunctionalInterface
    public interface Body {
        VTLObject execute(Map<String, VTLObject> binding);
    }

    /**
     * Builder for VTLFunction.
     */
    public static class Builder {

        private final Class<? extends VTLObject> returnType;
        private final String id;

        ImmutableMap.Builder<String, Class<? extends VTLObject>> signature;
        ImmutableMap.Builder<String, VTLObject> defaultValues;

        private Builder(String id, Class<? extends VTLObject> returnType) {
            checkArgument(!isNullOrEmpty(id));
            this.id = id;
            this.returnType = checkNotNull(returnType);
        }

        public Builder withArgument(String name, Class<? extends VTLObject> type) {
            signature.put(name, type);
            return this;
        }

        public Builder withArgument(String name, VTLObject defaultValue) {
            defaultValues.put(name, defaultValue);
            return withArgument(name, defaultValue.getClass());
        }

        public VTLFunction build(Body body) {

            return new VTLFunction(id, this.returnType, signature.build(), defaultValues.build(), body) {
                @Override
                public VTLObject apply(DataPoint dataPoint) {
                    throw new UnsupportedOperationException("TODO");
                }
            };
        }
    }
}
