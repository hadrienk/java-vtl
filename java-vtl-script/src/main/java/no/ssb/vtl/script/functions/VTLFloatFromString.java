package no.ssb.vtl.script.functions;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Pawel Buczek
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import no.ssb.vtl.model.VTLFloat;
import no.ssb.vtl.model.VTLString;

public class VTLFloatFromString extends AbstractVTLFunction<VTLFloat> {

    private static final Argument<VTLString> DS = new Argument<>("ds", VTLString.class);
    private static VTLFloatFromString instance;
    private VTLString NULL = VTLString.of((String) null);

    private VTLFloatFromString() {
        super("float_from_string", VTLFloat.class, DS);
    }

    public static VTLFloatFromString getInstance() {
        if (instance == null)
            instance = new VTLFloatFromString();
        return instance;
    }

    @Override
    protected VTLFloat safeInvoke(TypeSafeArguments arguments) {
        VTLString value = arguments.getNullable(DS, NULL);

        if (value.get() == null)
            return VTLFloat.of((Double) null);
        else {
            if (value.get().contains(",")) {
                String replaced = value.get().replace(",", ".");
                return VTLFloat.of(Double.valueOf(replaced));
            }
            return VTLFloat.of(Double.valueOf(value.get()));
        }
    }
}
