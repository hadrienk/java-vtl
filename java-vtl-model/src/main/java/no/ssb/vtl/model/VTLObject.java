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

import java.time.Instant;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Root of the VTL data type hierarchy.
 */
public abstract class VTLObject<V> implements Supplier<V>, Comparable<Object> {

    /**
     * This method is marked as deprecated (Hadrien, 02-05-2017).
     *
     * The reason is that we should enforce the type of the object we are creating using parameter type selection
     * if we decide it is safe to do so (can lead to error since return type is generic) :
     * <ul>
     *     <li>VTLObject of(Boolean l) // Returns VTLBoolean</li>
     *     <li>VTLObject of(Long l) // Returns VTLLong</li>
     *     <li>VTLObject of(Instant i) // Returns VTLInstant</li>
     *     <li>VTLObject of(Double d) // Returns VTLDouble</li>
     *     <li>...</li>
     * </ul>
     *
     * Subclasses' method should be used as "delegate":
     * <code><pre>
     * public static VTLBoolean of(Boolean b) {
     *     return VTLBoolean.of(b)
     * }
     * </pre></code>
     */
    public static VTLObject of(Object o) {
        if (o == null)
            return VTLObject.NULL;
        if (o instanceof VTLObject)
            return (VTLObject) o;
        if (o instanceof String)
            return VTLObject.of((String) o);
        if (o instanceof Double)
            return VTLObject.of((Double) o);
        if (o instanceof Float)
            return VTLObject.of((Float) o);
        if (o instanceof Long)
            return VTLObject.of((Long) o);
        if (o instanceof Integer)
            return VTLObject.of((Integer)o);
        if (o instanceof Instant)
            return VTLObject.of((Instant) o);
        if (o instanceof Boolean)
            return VTLObject.of((Boolean) o);

        throw new IllegalArgumentException("could not create a VTLObject from " + o + " (" + o.getClass() + ")");
    }

    /**
     * Create a new VTLString instance.
     */
    public static VTLString of(String str) {
        return VTLString.of(str);
    }

    /**
     * Create a new VTLBoolean instance.
     */
    public static VTLBoolean of(Boolean bool) {
        return VTLBoolean.of(bool);
    }

    /**
     * Create a new VTLDate instance.
     */
    public static VTLDate of(Instant instant) {
        return VTLDate.of(instant);
    }

    /**
     * Create a new VTLNumber instance.
     */
    public static VTLInteger of(Long num) {
        return VTLInteger.of(num);
    }

    /**
     * Create a new VTLNumber instance.
     */
    public static VTLInteger of(Integer num) {
        return VTLInteger.of(num);
    }

    /**
     * Create a new VTLNumber instance.
     */
    public static VTLFloat of(Float num) {
        return VTLFloat.of(num);
    }

    /**
     * Create a new VTLNumber instance.
     */
    public static VTLFloat of(Double num) {
        return VTLFloat.of(num);
    }


    @Deprecated
    public static final VTLObject NULL = new VTLObject() {
        @Override
        public Object get() {
            return null;
        }

        @Override
        public String toString() {
            return "[NULL]";
        }
    };

    /**
     * Returns the value of the data point.
     */
    @Override
    public abstract V get();

    /**
     * Note: this class has a natural ordering that is inconsistent with equals.
     * <br/>
     * TODO: Fix ordering?
     * TODO: Make comparable to only VTLObject
     */
    @Override
    public int compareTo(Object o) {
        Object value = this.get();
        Object other;
        if (o instanceof VTLObject) {
            other = ((VTLObject) o).get();
        } else {
            other = o;
        }

        if (value == null) {
            return (other == null) ? 0 : -1;
        } else if (other == null) {
            return 1;
        }

        // TODO: Should we allow this?
        // Compare numbers
        //if (Number.class.isAssignableFrom(other.getClass()) && Number.class.isAssignableFrom(value.getClass()))
        //    return Double.compare(((Number) value).doubleValue(), ((Number) other).doubleValue());

        // Compare comparable.
        if (other.getClass() == value.getClass() && value instanceof Comparable)
            return ((Comparable) value).compareTo(other);

        throw new IllegalArgumentException(
                String.format("Cannot compare %s of type %s with %s of type %s",
                        value, value.getClass(), other, other.getClass()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(get());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof VTLObject)) return false;
        VTLObject<?> value = (VTLObject<?>) o;
        return Objects.equals(get(), value.get());
    }

    @Override
    public String toString() {
        return get() == null ? "[NULL]" : get().toString();
    }

}
