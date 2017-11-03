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

import static java.lang.String.*;

public class VTLSubstr extends AbstractVTLFunction<VTLString> {

    private static final String ARGUMENT_GREATER_THAT_ZERO = "%s must be greater than zero, was %s";
    private static final Argument<VTLString> DS = new Argument<>("ds", VTLString.class);
    private static final Argument<VTLInteger> START_POSITION = new Argument<>("startPosition", VTLInteger.class);
    private static final Argument<VTLInteger> LENGTH = new Argument<>("length", VTLInteger.class);
    private static VTLSubstr instance;
    private VTLString STRING_NULL = VTLString.of((String) null);
    private VTLInteger INTEGER_NULL = VTLInteger.of((Long) null);

    private VTLSubstr() {
        super("substr", VTLString.class, DS, START_POSITION, LENGTH);
    }

    public static VTLSubstr getInstance() {
        if (instance == null)
            instance = new VTLSubstr();
        return instance;
    }

    @Override
    protected VTLString safeInvoke(TypeSafeArguments arguments) {
        VTLString value = arguments.getNullable(DS, STRING_NULL);
        VTLInteger startPosition = arguments.getNullable(START_POSITION, INTEGER_NULL);
        VTLInteger length = arguments.getNullable(LENGTH, INTEGER_NULL);

        if (value.get() == null || value.get().isEmpty()) {
            return VTLString.of((String) null);
        }

        if (startPosition.get() == null) {
            return VTLString.of(value.get());
        }

        if (startPosition.get().intValue() < 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, START_POSITION, startPosition)
            );
        }

        if (length.get() != null && length.get().intValue() < 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, LENGTH, length)
            );
        }

        int substringLength = value.get().length() - 1 - startPosition.get().intValue();
        if (substringLength < 0) {
            //deviation from the VTL specification 1.1
            return VTLString.of("");
        }

        if (ignoreLengthArgument(startPosition.get(), length.get(), value.get())) {
            return VTLString.of(value.get().substring(startPosition.get().intValue()));
        } else {
            Long endIndex = startPosition.get() + length.get();
            return VTLString.of(value.get().substring(startPosition.get().intValue(), endIndex.intValue()));
        }
    }

    private boolean ignoreLengthArgument(Long startPosition, Long length, String value) {
        if (length == null) {
            return true;
        }

        Long endIndex = startPosition + length - 1;
        return endIndex > value.length() - 1;
    }
}
