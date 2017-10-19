package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2017 Arild Johan Takvam-Borge
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

import static java.lang.String.format;

public class VTLLog extends AbstractVTLFunction<VTLNumber> {

    private static final String ARGUMENT_GREATER_THAT_ZERO = "%s must be greater than zero, was %s";
    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final Argument<VTLNumber> BASE = new Argument<>("base", VTLNumber.class);
    private static VTLLog instance;

    private VTLLog() {
        super("log", VTLNumber.class, DS, BASE);
    }

    public static VTLLog getInstance() {
        if (instance == null) {
            instance = new VTLLog();
        }
        return instance;
    }

    @Override
    protected VTLNumber safeInvoke(TypeSafeArguments arguments) {
        VTLNumber ds = arguments.get(DS);
        VTLNumber base = arguments.get(BASE);

        if (ds.get() == null) {
            return VTLNumber.of((Double) null);
        }

        //The number must be greater than zero
        if(ds.get().intValue() <= 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, DS, ds)
            );
        }

        //The base must be greater than zero
        if (base.get() == null || base.get().intValue() <= 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, BASE, base)
            );
        }

        double result = Math.log(ds.get().doubleValue()) / Math.log(base.get().doubleValue());

        return VTLNumber.of(result);
    }
}
