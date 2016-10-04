package kohl.hadrien;

/*-
 * #%L
 * java-vtl-model
 * %%
 * Copyright (C) 2016 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

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
