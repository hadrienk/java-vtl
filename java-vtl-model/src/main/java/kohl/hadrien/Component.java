package kohl.hadrien;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;

import java.util.function.Supplier;

/**
 * Component represent values of a dataset.
 */
public abstract class Component<T> implements Supplier<T> {

    private final Class<T> clazz;

    public Component(Class<T> clazz) {
        this.clazz = checkNotNull(clazz);
    }

    protected abstract String name();

    @Override
    public int hashCode() {
        return get().hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        return this.get().equals(((Component) other).get());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .addValue(get())
                .toString();
    }
}
