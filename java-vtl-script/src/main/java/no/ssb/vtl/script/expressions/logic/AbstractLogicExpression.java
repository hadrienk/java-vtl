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

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.VTLBoolean;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractLogicExpression implements VTLExpression {

    private final VTLExpression rightOperand;
    private final VTLExpression leftOperand;

    AbstractLogicExpression(VTLExpression leftOperand, VTLExpression rightOperand) {
        this.leftOperand = checkNotNull(leftOperand);
        this.rightOperand = checkNotNull(rightOperand);
    }

    @Override
    public Class getVTLType() {
        return VTLBoolean.class;
    }

    @Override
    public final VTLObject resolve(Bindings bindings) {

        VTLObject left = leftOperand.resolve(bindings);
        VTLObject right = rightOperand.resolve(bindings);

        return compute(
                left.get() == null ? VTLBoolean.of((Boolean) null) : (VTLBoolean) left,
                right.get() == null ? VTLBoolean.of((Boolean) null) : (VTLBoolean) right
        );
    }

    protected static boolean isNull(VTLBoolean value) {
        Boolean aBoolean = value.get();
        return aBoolean == null;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .addValue(leftOperand)
                .addValue(rightOperand)
                .toString();
    }

    protected abstract VTLBoolean compute(VTLBoolean leftOperand, VTLBoolean rightOperand);

    public VTLExpression getRightOperand() {
        return rightOperand;
    }

    public VTLExpression getLeftOperand() {
        return leftOperand;
    }
}
