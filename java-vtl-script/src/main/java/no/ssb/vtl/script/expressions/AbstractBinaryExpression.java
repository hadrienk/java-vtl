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

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.VTLExpression;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Binary expression that avoid calling resolve if one of its operand is null.
 */
public abstract class AbstractBinaryExpression implements VTLExpression {

    private final VTLExpression leftOperand;
    private final VTLExpression rightOperand;

    protected AbstractBinaryExpression(VTLExpression leftOperand, VTLExpression rightOperand) {
        this.leftOperand = checkNotNull(leftOperand);
        this.rightOperand = checkNotNull(rightOperand);
    }

    public VTLExpression getLeftOperand() {
        return leftOperand;
    }

    public VTLExpression getRightOperand() {
        return rightOperand;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .addValue(leftOperand)
                .addValue(rightOperand)
                .toString();
    }
}
