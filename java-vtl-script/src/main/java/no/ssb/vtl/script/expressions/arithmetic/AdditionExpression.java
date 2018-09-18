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

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLNumber;

public class AdditionExpression extends AbstractArithmeticExpression {

    public AdditionExpression(VTLExpression leftOperand, VTLExpression rightOperand) {
        super(leftOperand, rightOperand);
    }

    @Override
    protected VTLNumber compute(VTLNumber augend, VTLNumber addend) {
        return augend.add(addend);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(getLeftOperand())
                .addValue(getRightOperand())
                .toString();
    }
}
