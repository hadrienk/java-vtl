package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Arild Johan Takvam-Borge
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

import java.math.BigDecimal;

public class VTLExp extends AbstractVTLFunction<Number> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);

    @VisibleForTesting
    VTLExp() {
        super("exp", Number.class, DS);
    }

    @Override
    protected VTLObject<Number> safeInvoke(TypeSafeArguments arguments) {

        VTLNumber ds = arguments.get(DS);

        if (ds.get() == null) {
            return VTLObject.of((Number)null);
        }

        double exp = Math.exp(ds.get().doubleValue());

        //The VTL spec does not specify the number of decimals, but the examples have 3
        BigDecimal rounded = new BigDecimal(exp).setScale(3, BigDecimal.ROUND_HALF_UP);

        return VTLNumber.of(rounded.doubleValue());
    }
}
