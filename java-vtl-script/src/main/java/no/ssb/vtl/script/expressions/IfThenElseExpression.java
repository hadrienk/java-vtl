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
import no.ssb.vtl.model.VTLTyped;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * The if then else expression.
 */
public class IfThenElseExpression implements VTLExpression {

    private final ImmutableMap<VTLExpression, VTLExpression> conditionToExpression;
    private final VTLExpression defaultExpression;
    private final Class vtlType;

    private IfThenElseExpression(ImmutableMap<VTLExpression, VTLExpression> conditionToExpression,
                                 VTLExpression defaultExpression, Class vtlType) {
        checkArgument(vtlType != null, "could not infer data type. One value must be typed in if-then-else expressions");
        this.vtlType = vtlType;
        this.conditionToExpression = conditionToExpression;
        this.defaultExpression = defaultExpression;
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
        private Class returnType;
        private final ImmutableMap.Builder<VTLExpression, VTLExpression> builder = ImmutableMap.builder();

        private static boolean isNull(VTLTyped typed) {
            return VTLObject.class.equals(typed.getVTLType());
        }

        private VTLExpression checkValueType(VTLExpression value) {
            if (isNull(value))
                return value;

            if (returnType == null)
                returnType = value.getVTLType();

            checkArgument(
                    returnType.equals(value.getVTLType()),
                    "All return values must have the same type %s but was %s",
                    returnType.getName(),
                    value.getVTLType().getName()
            );
            return value;
        }

        private VTLExpression checkCondition(VTLExpression condition) {
            checkArgument(
                    condition.getVTLType().equals(VTLBoolean.class),
                    "Condition must return a %s, but was %s",
                    VTLBoolean.class.getName(),
                    condition.getVTLType().getName()
            );
            return condition;
        }

        public Builder(VTLExpression defaultExpression) {
            this.defaultExpression = defaultExpression;
            if (!isNull(defaultExpression))
                this.returnType = defaultExpression.getVTLType();
        }

        public Builder addCondition(VTLExpression cond, VTLExpression value) {
            builder.put(checkCondition(cond), checkValueType(value));
            return this;
        }

        public IfThenElseExpression build() {
            // TODO: test duplicate condition.
            return new IfThenElseExpression(builder.build(), defaultExpression, returnType);
        }
    }

}
