package no.ssb.vtl.script.expressions;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.model.VTLTyped;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Always resolved expression.
 */
public class LiteralExpression implements VTLExpression {

    private final VTLObject literal;

    public LiteralExpression(VTLObject literal) {
        this.literal = checkNotNull(literal);
    }

    @Override
    public String toString() {
        return literal.toString();
    }

    @Override
    public Class<?> getVTLType() {
        if (literal instanceof VTLTyped)
            return ((VTLTyped) literal).getVTLType();
        return VTLObject.class;
    }

    @Override
    public VTLObject resolve(Bindings dataPoint) {
        return literal;
    }
}
