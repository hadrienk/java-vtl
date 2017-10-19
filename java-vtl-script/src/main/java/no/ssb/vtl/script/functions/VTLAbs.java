package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Arild Johan Takvam-Borge
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

import no.ssb.vtl.model.VTLNumber;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.error.VTLRuntimeException;
import org.antlr.v4.runtime.ParserRuleContext;

import static java.lang.String.format;

public class VTLAbs extends AbstractVTLFunction<VTLNumber> {

    private static final Argument<VTLNumber> DS = new Argument<>("ds", VTLNumber.class);
    private static final String ILLEGAL_NUMBER_TYPE = "illegal number type [%s]";
    private static VTLAbs instance;

    private VTLAbs() {
        super("abs", VTLNumber.class, DS);
    }

    public static VTLAbs getInstance() {
        if (instance == null) {
            instance = new VTLAbs();
        }
        return instance;
    }

    @Override
    protected VTLNumber safeInvoke(TypeSafeArguments arguments) {

        VTLNumber ds = arguments.get(DS);

        Number value = ds.get();

        if (value == null) {
            return VTLObject.of((Double) null);
        }

        if (value instanceof Long) {
            return VTLNumber.of(Math.abs(value.intValue()));
        } else if (value instanceof Double) {
            return VTLNumber.of(Math.abs(value.doubleValue()));
        } else {
            throw new VTLRuntimeException(
                    format(ILLEGAL_NUMBER_TYPE, value.getClass().getSimpleName()),
                    "VTL-1000",
                    (ParserRuleContext) null
            );
        }
    }
}
