package kohl.hadrien;

/**
 * Created by hadrien on 07/09/16.
 */
public abstract class Identifier<T extends Comparable> extends Component<T> {

    public Identifier(T clazz) {
        super(clazz, null);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
