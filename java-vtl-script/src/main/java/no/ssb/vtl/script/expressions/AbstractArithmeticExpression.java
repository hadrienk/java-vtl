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
import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Expression with two operands that will not call resolve if one of the operand is null.
 */
public abstract class AbstractArithmeticExpression implements VTLExpression {

    private final VTLExpression leftOperand;
    private final VTLExpression rightOperand;

    protected AbstractArithmeticExpression(VTLExpression leftOperand, VTLExpression rightOperand) {
        this.leftOperand = checkNotNull(leftOperand);
        this.rightOperand = checkNotNull(rightOperand);
    }

    @Override
    public Class getVTLType() {
        if (leftOperand.getVTLType() == VTLFloat.class || rightOperand.getVTLType() == VTLFloat.class)
            return VTLFloat.class;
        else
            return VTLInteger.class;
    }

    @Override
    public final VTLObject resolve(Bindings bindings) {
        VTLObject leftOperand = this.leftOperand.resolve(bindings);
        VTLObject rightOperand = this.rightOperand.resolve(bindings);

        if (leftOperand.get() == null || rightOperand.get() == null)
            return VTLObject.NULL;
        else
            return compute(leftOperand, rightOperand);
    }

    abstract VTLObject compute(VTLObject leftOperand, VTLObject rightOperand);
}
