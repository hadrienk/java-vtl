package no.ssb.vtl.model;

import com.google.common.base.MoreObjects;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.*;

/**
 * A data point is a simple reference holder for values.
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
     * Convenience method giving the type of the data point.
     * <p>
     * It is strictly equivalent to getComponent().getType();
     * @deprecated Use {@link DataStructure#asMap(DataPoint)} instead
     */
    public Class<?> getType() {
        return getComponent() == null ? null : getComponent().getType();
    }
    
    /**
     * Convenience method returning the {@link Component.Role} of the data point.
     * <p>
     * It is strictly equivalent to getComponent().getRole();
     * @deprecated Use {@link DataStructure#asMap(DataPoint)} instead
     */
    public Component.Role getRole() {
        return getComponent() == null ? null : getComponent().getRole();
    }
    
    /**
     * Convenience method returning the name of the data point.
     * <p>
     * It is strictly equivalent to getComponent().getName();
     * @deprecated Use {@link DataStructure#asMap(DataPoint)} instead
     */
    public String getName() {
        return getComponent() == null ? null : getComponent().getName();
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
        }
        throw new IllegalArgumentException(
                String.format("Cannot compare %s of type %s with %s of type %s", value, value.getClass(), other,
                        other.getClass()));
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
        String value = get() == null ? "[NULL]" : get().toString();

        return MoreObjects.toStringHelper(super.toString())
        //return MoreObjects.toStringHelper(this)
                .addValue(Optional.ofNullable(getType()).map(Class::getSimpleName))
                .addValue(getRole())
                .addValue(getName())
                .toString().concat(" = ").concat(value);
    }

}
