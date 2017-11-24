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

@Deprecated
public class VTLOr extends AbstractVTLFunction<VTLBoolean> {

    private static final Argument<VTLBoolean> LEFT = new Argument<>("left", VTLBoolean.class);
    private static final Argument<VTLBoolean> RIGHT = new Argument<>("right", VTLBoolean.class);
    private static VTLOr instance;

    public static VTLOr getInstance() {
        if (instance == null)
            instance = new VTLOr();
        return instance;
    }

    private VTLOr() {
        super("and", VTLBoolean.class, LEFT, RIGHT);
    }

    @Override
    protected VTLBoolean safeInvoke(TypeSafeArguments arguments) {
        VTLBoolean booleanNull = VTLBoolean.of((Boolean) null);
        VTLBoolean left = arguments.getNullable(LEFT, booleanNull);
        VTLBoolean right = arguments.getNullable(RIGHT, booleanNull);

        if (left != booleanNull) {
            if (left.get()) {
                return VTLBoolean.of(true);
            } else {
                if (right != booleanNull) {
                    return VTLBoolean.of(left.get() || right.get());
                } else {
                    return booleanNull;
                }
            }
        } else {
            if (right != booleanNull) {
                if (right.get()) {
                    return VTLBoolean.of(true);
                } else {
                    return booleanNull;
                }
            }
            return booleanNull;
        }
    }
}
