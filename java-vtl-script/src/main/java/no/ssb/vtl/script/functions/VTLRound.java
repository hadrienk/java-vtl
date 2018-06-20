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
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLNumber;

import java.math.BigDecimal;

import static java.lang.String.format;

public class VTLRound extends AbstractVTLFunction<VTLFloat> {

    private static final String ARGUMENT_GREATER_THAT_ZERO = "%s must be greater than zero, was %s";
    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final Argument<VTLInteger> DECIMALS = new Argument<>("decimals", VTLInteger.class);
    private static VTLRound instance;

    private VTLRound() {
        super("round", VTLFloat.class, DS, DECIMALS);
    }

    public static VTLRound getInstance() {
        if (instance == null) {
            instance = new VTLRound();
        }
        return instance;
    }

    @Override
    protected VTLFloat safeInvoke(TypeSafeArguments arguments) {

        VTLNumber ds = arguments.getNullable(DS, VTLFloat.NULL);
        VTLInteger decimals = arguments.get(DECIMALS);

        if (VTLFloat.NULL.equals(ds)) {
            return VTLFloat.NULL;
        }

        if (decimals.get() == null || decimals.get().intValue() < 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, DECIMALS, decimals)
            );
        }

        double castValue = ds.get().doubleValue();
        if (!Double.isFinite(castValue)) {
            return VTLFloat.of(castValue);
        }

        BigDecimal bigDecimal = BigDecimal.valueOf(castValue);
        BigDecimal rounded = bigDecimal.setScale(decimals.get().intValue(), BigDecimal.ROUND_HALF_UP);

        return VTLFloat.of(rounded.doubleValue());
    }
}
