package kohl.hadrien.vtl.script.operations;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Iterables;
import kohl.hadrien.vtl.model.DataStructure;
import kohl.hadrien.vtl.model.Dataset;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.*;

public class FilterOperator implements Dataset{
    
    // The dataset we are applying the FilterOperator on.
    private final Dataset dataset;
    private final Set<String> components;
    
    private DataStructure cache;
    
    public FilterOperator(Dataset dataset, Set<String> names) {
        this.dataset = checkNotNull(dataset, "the dataset was null");
        this.components = checkNotNull(names, "the component list was null");
        
        checkArgument(!names.isEmpty(), "the list of component to filter on was null");
    }
    
    @Override
    public DataStructure getDataStructure() {
        return cache = (cache == null ? dataset.getDataStructure() : cache);
    }
    
    @Override
    public Stream<Tuple> get() {
        return this.dataset.filter(tuple -> tuple.ids().stream()
                .filter(dataPoint -> components.contains(dataPoint.getComponent().getName()))
                .anyMatch(dataPoint -> dataPoint.get().equals("1"))).stream();  //TODO do not hardcode filter criteria
    }
    
    @Override
    public String toString() {
        MoreObjects.ToStringHelper helper = MoreObjects.toStringHelper(this);
        Integer limit = 5;
        Iterables.limit(Iterables.concat(
                components,
                Collections.singletonList("and " + (components.size() - limit) + " more")
        ), Math.min(limit, components.size())).forEach(
                helper::addValue
        );
        return helper.toString();
    }
}
