package no.ssb.vtl.script.operations.join;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;

import javax.script.Bindings;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A special kind of scope that exposes a flattened view of the component
 * of the datasets that is used to create join scopes.
 */
final class JoinScopeBindings implements Bindings {

    private final ImmutableSet<String> datasetNames;
    private final Map<String, Object> scope = Maps.newHashMap();

    JoinScopeBindings(Map<String, Dataset> datasets) {
        checkNotNull(datasets);
        this.datasetNames = ImmutableSet.copyOf(datasets.keySet());
        this.scope.putAll(datasets);
        for (Entry<String, Dataset> datasetEntry : datasets.entrySet()) {
            Dataset dataset = datasetEntry.getValue();
            for (Entry<String, Component> componentEntry : dataset.getDataStructure().entrySet()) {
                this.scope.putIfAbsent(componentEntry.getKey(), componentEntry.getValue());
            }
        }
    }

    @Override
    public void clear() {
        scope.clear();
    }

    @Override
    public Set<String> keySet() {
        return scope.keySet();
    }

    @Override
    public Collection<Object> values() {
        return scope.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return scope.entrySet();
    }

    @Override
    public int size() {
        return scope.size();
    }

    @Override
    public boolean isEmpty() {
        return scope.isEmpty();
    }


    @Override
    public Object put(String name, Object value) {
        checkArgument(!datasetNames.contains(name), "could not add [%s] to the scope", name);
        return scope.put(name, value);
    }

    @Override
    public void putAll(Map<? extends String, ?> toMerge) {
        Set<? extends String> clashingNames = Sets.intersection(toMerge.keySet(), datasetNames);
        checkArgument(
                clashingNames.isEmpty(),
                "could not add [%s] to the scope", clashingNames
        );
        scope.putAll(toMerge);
    }

    @Override
    public boolean containsKey(Object key) {
        return scope.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return scope.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return scope.get(key);
    }

    @Override
    public Object remove(Object key) {
        checkArgument(!datasetNames.contains(key), "could not remove [%s] from the scope", key);
        return scope.remove(key);
    }
}
