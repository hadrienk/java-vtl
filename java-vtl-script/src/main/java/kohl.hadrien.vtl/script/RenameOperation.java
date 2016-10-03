package kohl.hadrien.vtl.script;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;
import kohl.hadrien.Component;
import kohl.hadrien.DataStructure;
import kohl.hadrien.Dataset;
import kohl.hadrien.Identifier;

/**
 * Rename operation.
 *
 *
 */
public class RenameOperation implements Function<Dataset, Dataset> {

  private final ImmutableMap<String, String> names;
  private final ImmutableMap<String, Class<? extends Component>> roles;

  public RenameOperation(Map<String, String> names, Map<String, Class<? extends Component>> roles) {
    HashBiMap.create(names);
    checkArgument(names.keySet().containsAll(roles.keySet()), "keys %s not present in %s",
        Sets.difference(roles.keySet(), names.keySet()), names.keySet());
    this.names = ImmutableMap.copyOf(names);
    this.roles = ImmutableMap.copyOf(roles);
  }

  @Override
  public Dataset apply(final Dataset dataset) {

    // Copy.
    Map<String, Class<? extends Component>> oldDataStructure, newDataStructure;
    oldDataStructure = Maps.newHashMap(dataset.getDataStructure());
    newDataStructure = Maps.newHashMapWithExpectedSize(oldDataStructure.size());

    checkArgument(oldDataStructure.keySet().containsAll(names.keySet()),
        "the dataset %s did not contain components with names %s", dataset,
        Sets.difference(names.keySet(), oldDataStructure.keySet())
    );

    for (String key : names.keySet()) {
      String newName = names.get(key);
      Class<? extends Component> newComponent;
      newComponent = roles.getOrDefault(key, oldDataStructure.remove(key));

      newDataStructure.put(newName, newComponent);
    }

    newDataStructure.putAll(oldDataStructure);

    return new Dataset() {
      @Override
      public Set<List<Identifier>> cartesian() {
        return null;
      }

      @Override
      public DataStructure getDataStructure() {
        return new DataStructure(newDataStructure);
      }

      @Override
      public Stream<Tuple> get() {
        return null;
      }
    };
  }
}
