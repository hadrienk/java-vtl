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

import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;

import static java.lang.String.format;

public class VTLLn extends AbstractVTLFunction<VTLFloat> {

    private static final String ARGUMENT_GREATER_THAT_ZERO = "%s must be greater than zero, was %s";
    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static VTLLn instance;

    private VTLLn() {
        super("ln", VTLFloat.class, DS);
    }

    public static VTLLn getInstance() {
        if (instance == null) {
            instance = new VTLLn();
        }
        return instance;
    }

    @Override
    protected VTLFloat safeInvoke(TypeSafeArguments arguments) {
        VTLNumber ds = arguments.get(DS);

        if (ds.get() == null) {
            return VTLObject.of((Double) null);
        }

        if(ds.get().intValue() <= 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, DS, ds)
            );
        }

        return VTLNumber.of(Math.log(ds.get().doubleValue()));
    }
}
