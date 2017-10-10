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

public class VTLNroot extends AbstractVTLFunction<Number> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final Argument<VTLNumber> INDEX = new Argument<>("index", VTLNumber.class);

    @VisibleForTesting
    VTLNroot() {
        super("nroot", Number.class, DS, INDEX);
    }

    @Override
    protected VTLObject<Number> safeInvoke(TypeSafeArguments arguments) {
        VTLNumber ds = arguments.get(DS);
        VTLNumber index = arguments.get(INDEX);

        if (ds.get() == null) {
            return VTLObject.of((Number)null);
        }
        if (index.get() == null) {
            throw new IllegalArgumentException("The index cannot be null");
        }

        int indexInt = index.get().intValue();
        double value = ds.get().doubleValue();

        //index cannot be 0
        if (indexInt == 0) {
            throw new IllegalArgumentException("Index cannot be zero");
        }

        //Index must be integer
        if (index.get().doubleValue() > indexInt) {
            throw new IllegalArgumentException("Index must be of type Integer");
        }

        //Only accept negative number if index is uneven
        if (indexInt % 2 == 0 && value < 0) {
            throw new IllegalArgumentException("The number must be greater than zero when index is even");
        }

        //Compute on absolute value and negate if original value was negative
        double result = (value != 0 ? (value / Math.abs(value)) : 1) * Math.pow(Math.abs(value), 1.0 / indexInt);

        return VTLNumber.of(result);
    }
}
