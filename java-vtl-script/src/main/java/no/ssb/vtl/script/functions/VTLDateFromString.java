package no.ssb.vtl.script.functions;

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

import no.ssb.vtl.model.VTLDate;
import no.ssb.vtl.model.VTLString;
import no.ssb.vtl.script.VTLScriptEngine;

import java.time.Instant;

import static com.google.common.base.Preconditions.checkArgument;

public class VTLDateFromString extends AbstractVTLFunction<VTLDate> {

    private static final Argument<VTLString> DS = new Argument<>("ds", VTLString.class);
    private static final Argument<VTLString> FORMAT = new Argument<>("format", VTLString.class);
    private static VTLDateFromString instance;
    private VTLString NULL = VTLString.of((String) null);

    private VTLDateFromString() {
        super("date_from_string", VTLDate.class, DS, FORMAT);
    }

    public static VTLDateFromString getInstance() {
        if (instance == null)
            instance = new VTLDateFromString();
        return instance;
    }

    @Override
    protected VTLDate safeInvoke(TypeSafeArguments arguments) {
        VTLString value = arguments.getNullable(DS, NULL);
        VTLString format = arguments.get(FORMAT);

        checkArgument(VTLDate.canParse(format.get()), "date format %s unsupported", format.get());
        if (value.get() == null)
            return VTLDate.of((Instant) null);
        else
            return VTLDate.of(value.get(), format.get(), VTLScriptEngine.getTimeZone());
    }
}
