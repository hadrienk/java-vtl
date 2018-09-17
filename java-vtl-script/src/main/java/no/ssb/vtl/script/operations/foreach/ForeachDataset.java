package no.ssb.vtl.script.operations.foreach;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Order;

import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A dataset that avoid sorting on data that is already sorted.
 */
public class ForeachDataset extends IteratorDataset {

    private final Order order;

    public ForeachDataset(
            DataStructure structure,
            Iterator<DataPoint> source,
            Order order
    ) {
        super(structure, source);
        this.order = checkNotNull(order);
    }

    @Override
    public Optional<Stream<DataPoint>> getData(Order orders, Filtering filtering, Set<String> components) {
        if (orders.equals(order)) {

        }
        return Optional.empty();
    }
}
