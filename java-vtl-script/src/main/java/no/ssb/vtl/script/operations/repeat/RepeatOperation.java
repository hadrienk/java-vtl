package no.ssb.vtl.script.operations.repeat;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.script.VTLDataset;

import javax.script.Bindings;
import javax.script.SimpleBindings;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Operation that repeats a set of operations
 * <p>
 * This operation repeats a set of operation for every distinct values
 * of a set of identifiers in a set of datasets.
 * <p>
 * for example:
 *
 * <pre>
 *     data := get("...")
 *     hierarchy := get("...")
 *
 *     result := foreach periode, region in (data, hierarchy) {
 *         subresult := hierarchy(data, data.measure, hierarchy, false)
 *     }
 * </pre>
 * <p>
 * The set of identifiers much be a subset of the common identifiers of the
 * set of datasets.
 */
public class RepeatOperation implements Dataset {

    private final ImmutableMap<String, Dataset> sourceDatasets;
    private final ImmutableSet<String> identifiers;

    private Bindings scope = new SimpleBindings(new LinkedHashMap<>());
    private Function<Bindings, VTLDataset> block;

    private Dataset result;

    public RepeatOperation(Map<String, Dataset> sourceDatasets, Set<String> identifiers) {
        this.sourceDatasets = ImmutableMap.copyOf(sourceDatasets);
        this.identifiers = ImmutableSet.copyOf(identifiers);
    }

    private Dataset evaluate() {
        if (result == null) {

            // Create the inner scope.
            for (String name : sourceDatasets.keySet()) {
                Dataset source = sourceDatasets.get(name);
                RepeatDataset = new RepeatDataset(source, identifiers);
                scope.put(name, VTLObject.of(source));
            }

            result = block.apply(scope).get();
        }
        return result;
    }

    @Override
    public Stream<DataPoint> getData() {
        return evaluate().getData();
    }

    @Override
    public Optional<Map<String, Integer>> getDistinctValuesCount() {
        return evaluate().getDistinctValuesCount();
    }

    @Override
    public Optional<Long> getSize() {
        return evaluate().getSize();
    }

    @Override
    public DataStructure getDataStructure() {
        return evaluate().getDataStructure();
    }
}
