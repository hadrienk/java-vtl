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

import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLString;

public class VTLIntegerFromString extends AbstractVTLFunction<VTLInteger> {

    private static final Argument<VTLString> DS = new Argument<>("ds", VTLString.class);
    private static VTLIntegerFromString instance;
    private VTLString NULL = VTLString.of((String) null);

    private VTLIntegerFromString() {
        super("integer_from_string", VTLInteger.class, DS);
    }

    public static VTLIntegerFromString getInstance() {
        if (instance == null)
            instance = new VTLIntegerFromString();
        return instance;
    }

    @Override
    protected VTLInteger safeInvoke(TypeSafeArguments arguments) {
        VTLString value = arguments.getNullable(DS, NULL);

        if (value.get() == null)
            return VTLInteger.of((Long) null);
        else
            return VTLInteger.of(Integer.parseInt(value.get()));
    }
}
