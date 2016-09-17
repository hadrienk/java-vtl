package kohl.hadrien;

import com.google.common.base.MoreObjects;

import java.util.function.Supplier;

/**
 * Created by hadrien on 07/09/16.
 */
public abstract class Component<T> implements Supplier<T> {

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
