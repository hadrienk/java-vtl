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

import no.ssb.vtl.model.VTLObject;

/**
 * The nvl function has dynamic return type.
 */
public class VTLNvl extends AbstractVTLFunction<VTLObject> {

    private static final Argument<VTLObject> NULLABLE = new Argument<>("nullable", VTLObject.class);
    private static final Argument<VTLObject> REPLACEMENT = new Argument<>("replacement", VTLObject.class);

    public VTLNvl() {
        super("nvl", VTLObject.class, NULLABLE, REPLACEMENT);
    }

    @Override
    protected VTLObject safeInvoke(TypeSafeArguments arguments) {
        VTLObject replacement = arguments.get(REPLACEMENT);
        return arguments.getNullable(NULLABLE, replacement);
    }
}
