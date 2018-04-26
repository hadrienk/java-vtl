package no.ssb.vtl.script.operations.union;

import com.codepoetics.protonpack.selectors.Selector;

import java.util.Comparator;

class MinimumSelector<T> implements Selector<T> {

    private final Comparator<T> comparator;

    public MinimumSelector(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Integer apply(T[] dataPoints) {
        // Find the lowest value
        T minVal = null;
        int idx = -1;
        for (int i = 0; i < dataPoints.length; i++) {
            T dataPoint = dataPoints[i];
            if (dataPoint == null)
                continue;

            if (minVal == null || comparator.compare(dataPoint, minVal) < 0) {
                minVal = dataPoint;
                idx = i;
            }
        }
        return idx;
    }
}
