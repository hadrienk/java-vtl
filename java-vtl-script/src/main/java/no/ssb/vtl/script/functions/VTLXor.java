package no.ssb.vtl.script.functions;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
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

import no.ssb.vtl.model.VTLBoolean;

import java.util.Arrays;
import java.util.Collections;

public class VTLXor extends AbstractVTLFunction<VTLBoolean> {
    private static final Argument<VTLBoolean> LEFT = new Argument<>("left", VTLBoolean.class);
    private static final Argument<VTLBoolean> RIGHT = new Argument<>("right", VTLBoolean.class);
    private static VTLXor instance;

    private VTLXor() {
        super("and", VTLBoolean.class, LEFT, RIGHT);
    }

    public static VTLXor getInstance() {
        if (instance == null)
            instance = new VTLXor();
        return instance;
    }

    @Override
    protected VTLBoolean safeInvoke(TypeSafeArguments arguments) {
        VTLBoolean booleanNull = VTLBoolean.of((Boolean) null);
        VTLBoolean left = arguments.getNullable(LEFT, booleanNull);
        VTLBoolean right = arguments.getNullable(RIGHT, booleanNull);

        VTLOr or = VTLOr.getInstance();
        VTLAnd and = VTLAnd.getInstance();
        VTLNot not = VTLNot.getInstance();
        return and.invoke(Arrays.asList(
                    or.invoke(Arrays.asList(
                            left,
                            right)
                    ),
                    not.invoke(Collections.singletonList(
                            and.invoke(Arrays.asList(
                                    left,
                                    right)
                            )))
        ));

    }
}
