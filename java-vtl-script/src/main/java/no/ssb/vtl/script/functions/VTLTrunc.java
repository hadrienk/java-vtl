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

import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;

import java.math.BigDecimal;

import static java.lang.String.format;

public class VTLTrunc extends AbstractVTLFunction<VTLFloat> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final Argument<VTLNumber> DECIMALS = new Argument<>("decimals", VTLNumber.class);
    private static VTLTrunc instance;

    private VTLTrunc() {
        super("trunc", VTLFloat.class, DS, DECIMALS);
    }

    public static VTLTrunc getInstance() {
        if (instance == null) {
            instance = new VTLTrunc();
        }
        return instance;
    }

    @Override
    protected VTLFloat safeInvoke(TypeSafeArguments arguments) {

        VTLNumber ds = arguments.get(DS);
        VTLNumber decimals = arguments.get(DECIMALS);

        if (ds.get() == null) {
            return VTLObject.of((Double) null);
        }
        if (decimals.get() == null || decimals.get().intValue() < 0) {
            throw new IllegalArgumentException(
                    format("%s must be equal to or greater than zero, was %s", DECIMALS, decimals)
            );
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(ds.get().doubleValue());
        BigDecimal rounded = bigDecimal.setScale(decimals.get().intValue(), BigDecimal.ROUND_DOWN);

        return VTLObject.of(rounded.doubleValue());
    }
}
