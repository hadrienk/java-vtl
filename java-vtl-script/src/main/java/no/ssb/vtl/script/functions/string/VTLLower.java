package no.ssb.vtl.script.functions.string;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
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


import no.ssb.vtl.model.VTLString;
import no.ssb.vtl.script.functions.AbstractVTLFunction;
import no.ssb.vtl.script.functions.TypeSafeArguments;

public final class VTLLower extends AbstractVTLFunction<VTLString> {

    private static final Argument<VTLString> VALUE = new Argument<>("value", VTLString.class);
    private static VTLLower instance;

    public static VTLLower getInstance() {
        if (instance == null) {
            instance = new VTLLower();
        }
        return instance;
    }

    private VTLLower() {
        super("lower", VTLString.class, VALUE);
    }

    @Override
    protected VTLString safeInvoke(TypeSafeArguments arguments) {
        VTLString string = arguments.get(VALUE);
        if (string.get() == null)
            return string;
        return VTLString.of(string.get().toLowerCase());
    }
}

