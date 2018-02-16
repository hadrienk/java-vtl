package no.ssb.vtl.script.expressions.arithmetic;

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
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.expressions.AbstractNullFirstExpression;

/**
 * Returns type Float if one of its operand is Float.
 */
public abstract class AbstractArithmeticExpression extends AbstractNullFirstExpression {

    // Arithmetic expression are often composed so we need caching.
    private Class type = null;

    AbstractArithmeticExpression(VTLExpression leftOperand, VTLExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    public Class getVTLType() {
        if (type == null) {
            if (hasOperandOfType(VTLNumber.class)) {
                type = VTLNumber.class;
            }
            else if (hasOperandOfType(VTLFloat.class))
                type = VTLFloat.class;
            else
                type = VTLInteger.class;
        }
        return type;
    }

    private boolean hasOperandOfType(Class clazz) {
        return getLeftOperand().getVTLType() == clazz || getRightOperand().getVTLType() == clazz;
    }

    @Override
    protected VTLObject compute(VTLObject leftOperand, VTLObject rightOperand) {
        return compute((VTLNumber) leftOperand, (VTLNumber) rightOperand);
    }

    abstract protected VTLNumber compute(VTLNumber leftOperand, VTLNumber rightOperand);
}
