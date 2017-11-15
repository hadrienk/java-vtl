package no.ssb.vtl.script.expressions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * Copyright (C) 2016 - 2017 Pawel Buczek
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
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;

/**
 * Helper class that resolves only needed VTLExpressions.
 */
public class IfThenElseExpressionHelper implements VTLExpression {

    private final ImmutableMap<VTLExpression, VTLExpression> conditionToExpression;
    private final VTLExpression defaultExpression;
    private final Class vtlType;

    private IfThenElseExpressionHelper(ImmutableMap<VTLExpression, VTLExpression> conditionToExpression,
                                       VTLExpression defaultExpression) {
        this.conditionToExpression = conditionToExpression;
        this.defaultExpression = defaultExpression;
        vtlType = defaultExpression.getVTLType();
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        for (VTLExpression conditionExpression : conditionToExpression.keySet()) {
            VTLObject resolved = conditionExpression.resolve(bindings);
            if (resolved.get() != null && resolved.get().equals(true)) {
                return conditionToExpression.get(conditionExpression).resolve(bindings);
            }
        }

        return defaultExpression.resolve(bindings);
    }

    @Override
    public Class getVTLType() {
        return vtlType;
    }

    public static class Builder {
        private final VTLExpression defaultExpression;
        private final Class returnType;
        private final ImmutableMap.Builder<VTLExpression, VTLExpression> builder = ImmutableMap.builder();

        public Builder(VTLExpression defaultExpression) {
            this.defaultExpression = defaultExpression;
            this.returnType = defaultExpression.getVTLType();
        }

        public Builder addCondition(VTLExpression cond, VTLExpression value) {
            if (!cond.getVTLType().equals(VTLBoolean.class)) {
                throw new IllegalArgumentException("Condition must return a " + VTLBoolean.class.getName()
                        + ", but was " + cond.getVTLType().getName());
            }

            if (!returnType.equals(value.getVTLType())) {
                throw new IllegalArgumentException("All return values must have the same type " + returnType.getName()
                        + " but was " + value.getVTLType().getName());
            }

            builder.put(cond, value);
            return this;
        }

        public IfThenElseExpressionHelper build() {
            return new IfThenElseExpressionHelper(builder.build(), defaultExpression);
        }
    }

}
