package no.ssb.vtl.script.support;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLObject;

import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Support class that reorder the values in a DataPoint.
 */
public class DatapointNormalizer implements UnaryOperator<DataPoint> {

    private final int[] swaps;

    public DatapointNormalizer(DataStructure from, DataStructure to) {
        checkNotNull(from);
        checkNotNull(to);

        checkArgument(from.keySet().containsAll(to.keySet()));
        checkArgument(!from.isEmpty());

        ImmutableList<String> fromList = ImmutableSet.copyOf(from.keySet()).asList();
        ImmutableList<String> toList = ImmutableSet.copyOf(to.keySet()).asList();

        // build indices swaps
        LinkedList<Integer> swaps = Lists.newLinkedList();
        String current;
        for (String fromName : fromList) {
            current = fromName;
            int fromIdx = fromList.indexOf(current);
            int toIdx = toList.indexOf(current);
            while (fromIdx != toIdx) {
                if (swaps.isEmpty() || swaps.getLast() != fromIdx)
                    swaps.add(fromIdx);
                toIdx = toList.indexOf(current);
                swaps.add(toIdx);
                current = fromList.get(toIdx);
                fromIdx = fromList.indexOf(current);
            }
        }
        this.swaps = Ints.toArray(swaps);
    }

    @Override
    public DataPoint apply(DataPoint datapoint) {
        if (swaps.length == 0)
            return datapoint;

        VTLObject last = datapoint.get(swaps[0]);
        for (int i = 1; i < swaps.length; i++) {
            VTLObject tmp = datapoint.get(swaps[i]);
            datapoint.set(swaps[i], last);
            last = tmp;
        }

        return datapoint;
    }
}
