package no.ssb.vtl.script.support;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.UnaryOperator;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Support class that reorder the values in a DataPoint.
 */
public class DatapointNormalizer implements UnaryOperator<DataPoint> {

    private final int[] fromIndices;
    private final int[] toIndices;

    public DatapointNormalizer(DataStructure from, DataStructure to) {
        checkNotNull(from);
        checkNotNull(to);

        checkArgument(from.keySet().containsAll(to.keySet()));
        checkArgument(!from.isEmpty());

        ImmutableList<String> fromList = ImmutableSet.copyOf(from.keySet()).asList();
        ImmutableList<String> toList = ImmutableSet.copyOf(to.keySet()).asList();

        // build indices
        ArrayList<Integer> fromIndices = Lists.newArrayList();
        ArrayList<Integer> toIndices = Lists.newArrayList();
        for (String fromName : fromList) {
            int fromIndex = fromList.indexOf(fromName);
            int toIndex = toList.indexOf(fromName);
            if (fromIndex == toIndex)
                continue;
            fromIndices.add(fromIndex);
            toIndices.add(toIndex);
        }
        this.fromIndices = Ints.toArray(fromIndices);
        this.toIndices = Ints.toArray(toIndices);
    }

    @Override
    public DataPoint apply(DataPoint datapoint) {
        if (fromIndices.length == 0)
            return datapoint;

        DataPoint copy = (DataPoint) datapoint.clone();
        for (int i = 0; i < fromIndices.length; i++)
            datapoint.set(toIndices[i], copy.get(fromIndices[i]));

        return datapoint;
    }
}
