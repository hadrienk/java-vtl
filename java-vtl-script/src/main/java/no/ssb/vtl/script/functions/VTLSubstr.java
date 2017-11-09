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

        if (startPosition.get() == null || startPosition.get().intValue() < 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, START_POSITION, startPosition)
            );
        }

        if (length.get() != null && length.get().intValue() < 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAT_ZERO, LENGTH, length)
            );
        }

        if (startPosition.get().intValue() >= value.get().length()) {
            //deviation from the VTL specification 1.1
            return VTLString.of("");
        }

        int endPosition = calculateEndPosition(startPosition.get().intValue(), length.get(), value.get().length());

        return VTLString.of(value.get().substring(startPosition.get().intValue(), endPosition));
    }

    private int calculateEndPosition(int startPosition, Long length, int valueLength) {
        if (length == null) {
            return valueLength;
        }

        int endPosition = startPosition + length.intValue();
        return Math.min(endPosition, valueLength);
    }
}
