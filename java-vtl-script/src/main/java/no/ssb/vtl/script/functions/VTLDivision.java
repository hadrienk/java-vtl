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

import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLNumber;

public class VTLDivision extends AbstractVTLFunction<VTLFloat> {

    private static final Argument<VTLNumber> LEFT = new Argument<>("left", VTLNumber.class);
    private static final Argument<VTLNumber> RIGHT = new Argument<>("right", VTLNumber.class);
    private static VTLDivision instance;

    private VTLDivision() {
        super("/", VTLFloat.class, LEFT, RIGHT);
    }

    public static VTLDivision getInstance() {
        if (instance == null)
            instance = new VTLDivision();
        return instance;
    }

    @Override
    protected VTLFloat safeInvoke(TypeSafeArguments arguments) {
        VTLNumber left = arguments.get(LEFT);
        VTLNumber right = arguments.get(RIGHT);
        return (VTLFloat) left.divide(right);
    }
}

