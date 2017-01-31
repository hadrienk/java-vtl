package no.ssb.vtl.script.operations;

import com.google.common.base.MoreObjects;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class FilterOperator implements Dataset{
    
    // The dataset we are applying the FilterOperator on.
    private final Dataset dataset;
    private final Predicate<Tuple> predicate;
    
    private DataStructure cache;
    
    public FilterOperator(Dataset dataset, Predicate<Tuple> predicate) {
        this.dataset = checkNotNull(dataset, "the dataset was null");
        this.predicate = checkNotNull(predicate, "the predicate was null");
    }
    
    @Override
    public DataStructure getDataStructure() {
        return cache = (cache == null ? dataset.getDataStructure() : cache);
    }
    
    @Override
    public Stream<Tuple> get() {
        return this.dataset.filter(predicate).stream();
    }
    
    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        Map<Boolean, List<Tuple>> predicateResultMap = dataset.stream().collect(Collectors.partitioningBy(predicate));
        helper.addValue(predicateResultMap);
        return helper.toString();
    }
}
