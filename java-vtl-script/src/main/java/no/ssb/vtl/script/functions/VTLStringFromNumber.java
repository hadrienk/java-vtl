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
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLString;

public class VTLStringFromNumber extends AbstractVTLFunction<VTLString> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static VTLStringFromNumber instance;
    private VTLNumber NULL = VTLNumber.of((Long) null);

    private VTLStringFromNumber() {
        super("string_from_number", VTLString.class, DS);
    }

    public static VTLStringFromNumber getInstance() {
        if (instance == null)
            instance = new VTLStringFromNumber();
        return instance;
    }

    @Override
    protected VTLString safeInvoke(TypeSafeArguments arguments) {
        VTLNumber value = arguments.getNullable(DS, NULL);

        if (value.get() == null)
            return VTLString.of((String) null);
        else {
            if (value instanceof VTLInteger) {
                return VTLString.of(Long.toString(value.get().longValue()));
            } else if (value instanceof VTLFloat) {
                return VTLString.of(Double.toString(value.get().doubleValue()));
            } else {
                throw new UnsupportedOperationException("Type " + value.getClass() + " not supported");
            }
        }
    }
}
