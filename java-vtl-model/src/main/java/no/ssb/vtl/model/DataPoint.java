package no.ssb.vtl.model;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataPoint extends ArrayList<VTLObject> {

    protected DataPoint(int initialCapacity) {
        super(initialCapacity);
    }

    protected DataPoint() {
        super();
    }

    protected DataPoint(Collection<? extends VTLObject> c) {
        super(c);
    }

    public static DataPoint create(int initialCapacity) {
        return new DataPoint(Collections.nCopies(initialCapacity, VTLObject.NULL));
    }

    public static DataPoint create(Collection<? extends VTLObject> components) {
        return new DataPoint(components);
    }

    public static DataPoint create(VTLObject... values) {
        return new DataPoint(Arrays.asList(values));
    }

    public static DataPoint create(Object... values) {
        return new DataPoint(Stream.of(values).map(VTLObject::of).collect(Collectors.toList()));
    }

    @Override
    public String toString() {
        return this.stream()
                .map((vtlObject) -> vtlObject == null ? "<null>" : vtlObject.toString())
                .collect(Collectors.joining(", ", "[", "]"));
    }
}
