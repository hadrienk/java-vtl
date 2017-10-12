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

import no.ssb.vtl.model.VTLNumber;

public class VTLMultiplication extends AbstractVTLFunction<VTLNumber> {

    private static final Argument<VTLNumber> LEFT = new Argument<>("left", VTLNumber.class);
    private static final Argument<VTLNumber> RIGHT = new Argument<>("right", VTLNumber.class);
    private static VTLMultiplication instance;

    private VTLMultiplication() {
        super("*", VTLNumber.class, LEFT, RIGHT);
    }

    public static VTLMultiplication getInstance() {
        if (instance == null)
            instance = new VTLMultiplication();
        return instance;
    }

    @Override
    protected VTLNumber safeInvoke(TypeSafeArguments arguments) {
        VTLNumber left = arguments.get(LEFT);
        VTLNumber right = arguments.get(RIGHT);
        return left.multiply(right);
    }
}
