package no.ssb.vtl.model;

import java.time.Instant;
import java.time.Year;
import java.util.Objects;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.*;

/**
 * Root of the VTL data type hierarchy.
 */
public abstract class VTLObject<V> implements Supplier<V>, Comparable<Object>{

    private final Component componentReference;
    
    protected VTLObject() {
        componentReference = null; //TODO: Remove any reference to component
    }
    
    /**
     * @deprecated Use {@link DataStructure#wrap(String, Object)} instead
     */
    private VTLObject(Component component) {
        this.componentReference = checkNotNull(component);
    }
    
    @Deprecated
    public static VTLObject of(Component component, Object o) {
        if (o instanceof VTLObject) {
            return (VTLObject) o;
        }
    
        return new VTLObject<Object>(component){
        
            @Override
            public Object get() {
                return o;
            }
        };
    }
    
    public static VTLObject of(Object o) {
        if (o instanceof VTLObject) {
            return (VTLObject) o;
        }
        
        return new VTLObject<Object>(){
    
            @Override
            public Object get() {
                return o;
            }
        };
    }
    
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
     * Returns the componentReference (type and role) of this data point.
     * @deprecated Use {@link DataStructure#asMap(DataPoint)} instead
     */
    public Component getComponent() {
        return componentReference;
    }
    
    /**
     * Note: this class has a natural ordering that is inconsistent with equals. //TODO: Fix that
     * <br/>
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
        if (value instanceof Comparable && other.getClass() == value.getClass()) {
            return ((Comparable) value).compareTo(other);
        }
        if (value instanceof Integer && other instanceof  Integer) {
            return ((Integer) value).compareTo((Integer) other);
        } else if (value instanceof Float && other instanceof Float) {
            return ((Float) value).compareTo((Float) other);
        } else if (value instanceof Double && other instanceof Double) {
            return ((Double) value).compareTo((Double) other);
        } else if (value instanceof Boolean && other instanceof Boolean) {
            return ((Boolean) value).compareTo((Boolean) other);
        } else if (value instanceof String && other instanceof String) {
            return ((String) value).compareTo((String) other);
        } else if (value instanceof Instant && other instanceof Instant) {
            return ((Instant) value).compareTo((Instant) other);
        } else if (value instanceof Year && other instanceof Year) {
            return ((Year) value).compareTo((Year) other);
        }
        throw new IllegalArgumentException(
                String.format("Cannot compare %s of type %s with %s of type %s",
                        value, value==null?"<null>":value.getClass(), other, other==null?"<null>":other.getClass()));
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(componentReference, get());
    }
    
    @Override
    public boolean equals(Object o) { //TODO
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VTLObject<?> value = (VTLObject<?>) o;
        return Objects.equals(get(), value.get());
    }

    @Override
    public String toString() {
        return get() == null ? "[NULL]" : get().toString();
    }

}
