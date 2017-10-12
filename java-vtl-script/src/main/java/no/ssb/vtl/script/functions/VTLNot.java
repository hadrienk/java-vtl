package no.ssb.vtl.script.functions;

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

import no.ssb.vtl.model.VTLBoolean;

public class VTLNot extends AbstractVTLFunction<VTLBoolean> {
    private static final Argument<VTLBoolean> OPERAND = new Argument<>("operand", VTLBoolean.class);
    private static VTLNot instance;

    private VTLNot() {
        super("not", VTLBoolean.class, OPERAND);
    }

    public static VTLNot getInstance() {
        if (instance == null)
            instance = new VTLNot();
        return instance;
    }

    @Override
    protected VTLBoolean safeInvoke(TypeSafeArguments arguments) {
        VTLBoolean booleanNull = VTLBoolean.of((Boolean) null);
        VTLBoolean operand = arguments.getNullable(OPERAND, booleanNull);
        return operand == booleanNull ? booleanNull : VTLBoolean.of(!operand.get());
    }
}
