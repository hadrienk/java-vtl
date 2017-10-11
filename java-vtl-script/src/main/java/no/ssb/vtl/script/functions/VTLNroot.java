package no.ssb.vtl.script.functions;

/*
 * -
 *  * ========================LICENSE_START=================================
 * * Java VTL
 *  *
 * %%
 * Copyright (C) 2017 Arild Johan Takvam-Borge
 *  *
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

import com.google.common.annotations.VisibleForTesting;
import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;

import static java.lang.String.format;

public class VTLNroot extends AbstractVTLFunction<VTLNumber> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final Argument<VTLNumber> INDEX = new Argument<>("index", VTLNumber.class);

    public static final String ARGUMENT_NULL_OR_ZERO = "%s cannot be null or zero, was %s";
    public static final String ARGUMENT_WRONG_TYPE = "%s must be an integer, was %s";
    public static final String ARGUMENT_GREATER_THAN_ZERO_EVEN_INDEX = "%s must be greater than zero when %s is even, was %s";

    @VisibleForTesting
    VTLNroot() {
        super("nroot", VTLNumber.class, DS, INDEX);
    }

    @Override
    protected VTLNumber safeInvoke(TypeSafeArguments arguments) {
        VTLNumber ds = arguments.get(DS);
        VTLNumber index = arguments.get(INDEX);

        if (ds.get() == null) {
            return VTLObject.of((Number)null);
        }

        //index cannot be 0 or zero
        if (index.get() == null || index.get().intValue() == 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_NULL_OR_ZERO, INDEX, index)
            );
        }

        int indexInt = index.get().intValue();
        double value = ds.get().doubleValue();

        //Index must be integer
        // TODO: Adjust when VTLInteger and VTLFloat are done.
        if (index.get().doubleValue() > indexInt) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_WRONG_TYPE, DS, index)
            );
        }

        //Only accept negative number if index is uneven
        if (indexInt % 2 == 0 && value < 0) {
            throw new IllegalArgumentException(
                    format(ARGUMENT_GREATER_THAN_ZERO_EVEN_INDEX, DS, INDEX, value)
            );
        }

        //Compute on absolute value and negate if original value was negative
        double result = (value != 0 ? (value / Math.abs(value)) : 1) * Math.pow(Math.abs(value), 1.0 / indexInt);

        return VTLNumber.of(result);
    }
}
