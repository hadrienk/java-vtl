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

public class VTLMod extends AbstractVTLFunction<Number> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final Argument<VTLNumber> DENOMINATOR = new Argument<>("denominator", VTLNumber.class);
    public static final String ARGUMENT_NULL_OR_ZERO = "%s cannot be null or zero, was %s";

    @VisibleForTesting
    VTLMod() {
        super("mod", Number.class, DS, DENOMINATOR);
    }

    @Override
    protected VTLObject<Number> safeInvoke(TypeSafeArguments arguments) {

        VTLNumber ds = arguments.get(DS);
        VTLNumber denominator = arguments.get(DENOMINATOR);

        if (ds.get() == null) {
            return VTLObject.of((Number)null);
        }
        if (denominator.get() == null || denominator.get().doubleValue() == 0.0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_NULL_OR_ZERO, DENOMINATOR, denominator)
            );
        }

        return VTLNumber.of(ds.get().doubleValue() % denominator.get().doubleValue());
    }

}
