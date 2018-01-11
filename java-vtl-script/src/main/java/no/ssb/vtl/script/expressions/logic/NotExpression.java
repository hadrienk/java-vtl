package no.ssb.vtl.script.expressions.logic;

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

import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;

public class NotExpression implements VTLExpression<VTLBoolean> {

    private final VTLExpression operand;

    public NotExpression(VTLExpression operand) {
        this.operand = checkNotNull(operand);
    }

    @Override
    public VTLBoolean resolve(Bindings bindings) {
        VTLObject resolved = operand.resolve(bindings);
        if(resolved.get() == null)
            return VTLBoolean.of((Boolean) null);

        VTLBoolean value = (VTLBoolean) resolved;
        return VTLBoolean.of(!value.get());
    }

    @Override
    public Class<VTLBoolean> getVTLType() {
        return VTLBoolean.class;
    }
}
