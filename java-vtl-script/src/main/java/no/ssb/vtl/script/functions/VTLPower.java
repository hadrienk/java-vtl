package no.ssb.vtl.script.functions;

/*
 * -
 *  * ========================LICENSE_START=================================
 * * Java VTL
 *  *
 * %%
 * Copyright (C) 2017 Arild Johan Takvam-Borge
 *  *
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

import com.google.common.annotations.VisibleForTesting;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;

import static java.lang.String.format;

public class VTLPower extends AbstractVTLFunction<Number> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final Argument<VTLNumber> EXP = new Argument<>("base", VTLNumber.class);
    public static final String ARGUMENT_MUST_BE_NUMBER = "%s must be a valid number, was %s";

    @VisibleForTesting
    VTLPower() {
        super("power", Number.class, DS, EXP);
    }

    @Override
    protected VTLObject<Number> safeInvoke(TypeSafeArguments arguments) {
        VTLNumber ds = arguments.get(DS);
        VTLNumber exp = arguments.get(EXP);

        if (ds.get() == null) {
            return VTLObject.of((Number) null);
        }

        if (exp.get() == null) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_MUST_BE_NUMBER, EXP, exp)
            );
        }

        return VTLNumber.of(Math.pow(ds.get().doubleValue(), exp.get().doubleValue()));
    }
}
