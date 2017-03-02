package no.ssb.vtl.model;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.*;
import static no.ssb.vtl.model.Order.Direction.*;

/**
 * Represent the ordering the {@link DataPoint}s in a Dataset.
 */
public final class Order extends ForwardingMap<String, Order.Direction> implements Comparator<DataPoint>, Map<String, Order.Direction> {

    public static final Comparator<Comparable> NULLS_FIRST = Comparator.<Comparable>nullsFirst(Comparator.naturalOrder());
    static final Comparator<Entry<String, Component>> BY_ROLE = Comparator.comparing(
            entry -> entry.getValue().getRole(),
            Ordering.explicit(
                    Component.Role.IDENTIFIER,
                    Component.Role.MEASURE,
                    Component.Role.ATTRIBUTE
            )
    );
    static final Comparator<Entry<String, Component>> BY_NAME = Comparator.comparing(Entry::getKey);

    private final ImmutableMap<String, Direction> delegate;
    private final DataStructure structure;

    Order(Map<String, Direction> orders, DataStructure structure) {
        this.delegate = ImmutableMap.copyOf(orders);
        this.structure = checkNotNull(structure);
    }

    public static Order create(Map<String, Component> orders, DataStructure dataStructure) {
        ImmutableMap.Builder<String, Direction> order = ImmutableMap.builder();
        for (Entry<String, Component> entry : orders.entrySet()) {
            order.put(entry.getKey(), ASC);
        }
        return new Order(order.build(), dataStructure);
    }

    static Order from(Iterable<Entry<String, Direction>> orders) {
        return new Order(ImmutableMap.copyOf(orders));
    }
    /**
     * Return a default Order for the given datastructure.
     *
     * @param orders
     * @return
     */
    public static Order getDefault(Map<String, Component> orders, DataStructure dataStructure) {
        Set<Entry<String, Component>> sortedEntrySet = Sets.newTreeSet(BY_ROLE.thenComparing(BY_NAME));
        sortedEntrySet.addAll(orders.entrySet());

        ImmutableMap.Builder<String, Direction> order = ImmutableMap.builder();
        for (Entry<String, Component> entry : sortedEntrySet) {
            order.put(entry.getKey(), ASC);
        }
        return new Order(order.build(), dataStructure);
    }

    /**
     * Return a default Order for the given datastructure.
     */
    public static Order getDefault(DataStructure structure) {
        return getDefault(structure, structure);
    }

    @Override
    protected Map<String, Direction> delegate() {
        return delegate;
    }

    @Override
    public int compare(DataPoint o1, DataPoint o2) {
        int result;

        // TODO migrate to Map<Component, Direction> and remove the DataStructure dependency.
        Map<Component, ? extends Comparable> m1 = structure.asMap(o1), m2 = structure.asMap(o2);
        for (Entry<String, Direction> order : delegate.entrySet()) {
            String key = order.getKey();
            result = NULLS_FIRST.compare(m1.get(structure.get(key)), m2.get(structure.get(key)));
            if (result != 0) {
                return order.getValue() == ASC ? result : -result;
            }
        }

        // TODO build an index?
        Comparable[] c1 = new Comparable[1], c2 = new Comparable[1];
        ImmutableMap<Integer, Direction> index = ImmutableMap.copyOf(Collections.emptyMap());
        for (Entry<Integer, Direction> order : index.entrySet()) {
            Integer i = order.getKey();
            result = NULLS_FIRST.compare(c1[i], c2[i]);
            if (result != 0) {
                return order.getValue() == ASC ? result : -result;
            }
        }
        return 0;
    }

    enum Direction {
        ASC, DESC
    }
}
