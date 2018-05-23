package no.ssb.vtl.script.operations.join;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2018 Hadrien Kohl
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

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;

import java.util.function.Consumer;

/**
 * A consumer that expands the size of the {@link DataPoint} it receives.
 */
public class DataPointCapacityExpander implements Consumer<DataPoint> {

    private final int newSize;

    public DataPointCapacityExpander(int newCapacity) {
        this.newSize = newCapacity;
    }

    @Override
    public void accept(DataPoint dataPoint) {
        dataPoint.ensureCapacity(newSize);
        while (dataPoint.size() < newSize) {
            dataPoint.add(VTLObject.NULL);
        }
    }
}
