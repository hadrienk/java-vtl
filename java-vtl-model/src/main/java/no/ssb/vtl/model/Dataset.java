package no.ssb.vtl.model;

/*-
 * #%L
 * java-vtl-model
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.codepoetics.protonpack.Streamable;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.*;

/**
 * A data structure that allows relational operations.
 */
public interface Dataset extends Streamable<Dataset.DataPoint> {

    static Comparator<DataPoint> comparatorFor(Component.Role... roles) {
        ImmutableSet<Component.Role> roleSet = Sets.immutableEnumSet(Arrays.asList(roles));
        return new Comparator<DataPoint>() {
            @Override
            public int compare(DataPoint li, DataPoint ri) {
                Comparator comparator = Comparator.naturalOrder();

                Map<String, Object> lm = li.stream().filter(dataPoint -> roleSet.contains(dataPoint.getRole()))
                        .collect(Collectors.toMap(
                                VTLObject::getName,
                                VTLObject::get
                        ));

                Map<String, Object> rm = ri.stream().filter(dataPoint -> roleSet.contains(dataPoint.getRole()))
                        .collect(Collectors.toMap(
                                VTLObject::getName,
                                VTLObject::get
                        ));

                checkArgument(lm.keySet().equals(rm.keySet()));
                int i = 0;
                for (String key : lm.keySet()) {
                    i = comparator.compare(lm.get(key), rm.get(key));
                    if (i != 0)
                        return i;
                }
                return i;

            }
        };
    }

    /**
     * Returns the data structure of the DataSet.
     */
    DataStructure getDataStructure();

    interface DataPoint extends List<VTLObject> {

        static DataPoint create(List<VTLObject> components) {
            return new AbstractDataPoint() {
                @Override
                protected List<VTLObject> delegate() {
                    return components;
                }
            };
        }

    }

    abstract class AbstractDataPoint extends ForwardingList<VTLObject> implements DataPoint {

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(DataPoint.class)
                    .add("values", delegate())
                    .toString();
        }

    }

}
