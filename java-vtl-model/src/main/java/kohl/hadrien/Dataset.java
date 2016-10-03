package kohl.hadrien;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.MoreObjects;
import com.google.common.collect.ForwardingList;

import com.codepoetics.protonpack.Streamable;

import java.util.AbstractList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Set;

/**
 * A data structure that allows relational operations.
 */
public interface Dataset extends Streamable<Dataset.Tuple> {

    Set<List<Identifier>> cartesian();


    /**
     * Returns the data structure of the DataSet.
     */
    DataStructure getDataStructure();

    interface Tuple extends List<Component>, Comparable<Tuple> {

        List<Identifier> ids();

        List<Component> values();

        Tuple combine(Tuple tuple);

    }

    abstract class AbstractTuple extends ForwardingList<Component> implements Tuple {

        @Override
        protected List<Component> delegate() {
            return new CombinedList<>(ids(), values());
        }

        @Override
        public int compareTo(Tuple o) {
            Comparator comparator = Comparator.naturalOrder();
            Iterator<Identifier> li = this.ids().iterator();
            Iterator<Identifier> ri = o.ids().iterator();
            int i = 0;
            while (li.hasNext() && ri.hasNext()) {
                i = comparator.compare(li.next().get(), ri.next().get());
                if (i != 0)
                    return i;
            }
            return i;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(AbstractTuple.class)
                    .add("id", ids())
                    .add("values", values())
                    .toString();
        }

        @Override
        public int hashCode() {
            return ids().hashCode();
        }

        @Override
        public boolean equals(Object other) {
            if (this == other)
                return true;
            if (other == null || getClass() != other.getClass())
                return false;
            return this.ids().equals(((Tuple) other).ids());
        }

        @Override
        public Tuple combine(Tuple tuple) {
            return new AbstractTuple() {
                @Override
                public List<Identifier> ids() {
                    return AbstractTuple.this.ids();
                }

                @Override
                public List<Component> values() {
                    return new CombinedList<>(AbstractTuple.this.values(), tuple.values());
                }
            };
        }
    }

    class CombinedList<T> extends AbstractList<T> implements RandomAccess {

        final List<? extends T> a;
        final List<? extends T> b;

        public CombinedList(List<? extends T> a, List<? extends T> b) {
            this.a = checkNotNull(a);
            this.b = checkNotNull(b);
        }

        @Override
        public int size() {
            return a.size() + b.size();
        }

        @Override
        public T get(int index) {
            if (index < a.size())
                return a.get(index);
            return b.get(index - a.size());
        }
    }

}
