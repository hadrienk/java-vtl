package no.ssb.vtl.script.operations.join;

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

import com.google.common.collect.Iterators;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A wrapper around Datapoint that exposes it as a {@link javax.script.Bindings}.
 */
public class DataPointBindings implements Bindings {

    private final DataStructure dataStructure;
    private Map<Component, VTLObject> map;
    private DataPoint dataPoint;

    public DataPointBindings(DataStructure dataStructure) {
        this.dataStructure = checkNotNull(dataStructure);
    }

    public DataPointBindings setDataPoint(DataPoint dataPoint) {
        this.dataPoint = dataPoint;
        this.map = dataStructure.asMap(this.dataPoint);
        return this;
    }

    public DataPoint getDataPoint() {
        return dataPoint;
    }

    @Override
    public Object put(String name, Object value) {
        checkArgument(value instanceof VTLObject);
        return map.put(dataStructure.get(name), (VTLObject) value);
    }

    @Override
    public void putAll(Map<? extends String, ?> toMerge) {

    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return dataStructure.keySet();
    }

    @Override
    public Collection<Object> values() {
        return (Collection)map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return new AbstractSet<Entry<String, Object>>() {
            @Override
            public Iterator<Entry<String, Object>> iterator() {
                Iterator<Entry<Component, VTLObject>> iterator = map.entrySet().iterator();
                return Iterators.transform(iterator, input -> {
                    return new AbstractMap.SimpleImmutableEntry<>(
                            dataStructure.getName(input.getKey()),
                            (Object) input.getValue());
                });
            }

            @Override
            public int size() {
                return map.size();
            }
        };
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return dataStructure.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return map.get(dataStructure.get(key));
    }

    @Override
    public Object remove(Object key) {
        return map.remove(dataStructure.get(key));
    }
}
