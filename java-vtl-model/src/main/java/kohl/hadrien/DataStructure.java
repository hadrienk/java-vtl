package kohl.hadrien;

import com.google.common.collect.ForwardingMap;
import com.google.common.collect.ImmutableSet;

import java.util.Map;

/**
 * Created by hadrien on 07/09/16.
 */
public class DataStructure extends ForwardingMap<String, Class<? extends Component>> {


  ImmutableSet<String> names;
  Map<String, Class<? extends Component>> roles;
  Map<String, Class<? extends Object>> types;

  public DataStructure(Map<String, Class<? extends Component>> components) {
    this.names = ImmutableSet.copyOf(components.keySet());
    this.roles = components;
    this.types = convertToTypes(this.roles);
  }

  private Map<String, Class<? extends Object>> convertToTypes(
      Map<String, Class<? extends Component>> roles) {
    return null;
  }

  public Object wrap(String key, Object value) {
    return null;
  }

  @Override
  protected Map<String, Class<? extends Component>> delegate() {
    return roles;
  }
}
