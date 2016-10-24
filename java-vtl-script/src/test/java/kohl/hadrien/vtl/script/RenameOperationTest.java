package kohl.hadrien.vtl.script;

/*-
 * #%L
 * java-vtl-script
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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import kohl.hadrien.*;
import org.junit.Test;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RenameOperationTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNotDuplicates() throws Exception {
        ImmutableMap<String, String> names = ImmutableMap.of("a1", "a2", "b1", "a2");
        try {
            new RenameOperation(names, Collections.emptyMap());
        } catch (Throwable t) {
            assertThat(t).hasMessageContaining("a2");
            throw t;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConsistentArguments() throws Exception {
        ImmutableMap<String, String> names = ImmutableMap.of("a1", "a2", "b1", "b2");
        ImmutableMap<String, Class<? extends Component>> roles;
        roles = ImmutableMap.of("nothere", Identifier.class);
        try {
            new RenameOperation(names, roles);
        } catch (Throwable t) {
            assertThat(t).hasMessageContaining("nothere");
            throw t;
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testKeyNotFound() throws Exception {
        RenameOperation rename;
        rename = new RenameOperation(ImmutableMap.of("1a", "1b"), Collections.emptyMap());

        Dataset dataset = mock(Dataset.class);
        when(dataset.getDataStructure()).thenReturn(
                DataStructure.of((s, o) -> null, "notfound", Identifier.class, String.class)
        );
        try {
            rename.apply(dataset);
        } catch (Throwable t) {
            assertThat(t).hasMessageContaining("1a");
            throw t;
        }

    }

    @Test
    public void testRename() throws Exception {
        RenameOperation rename;
        rename = new RenameOperation(
                ImmutableMap.of(
                        "Ia", "Ib",
                        "Ma", "Mb",
                        "Aa", "Ab"
                ), Collections.emptyMap()
        );

        Dataset dataset = mock(Dataset.class);

        when(dataset.getDataStructure()).thenReturn(
                DataStructure.of((s, o) -> null,
                        "Ia", Identifier.class, String.class,
                        "Ma", Measure.class, String.class,
                        "Aa", Attribute.class, String.class
                )
        );
        Dataset renamedDataset = rename.apply(dataset);

        assertThat(renamedDataset.getDataStructure().roles()).contains(
                entry("Ib", Identifier.class),
                entry("Mb", Measure.class),
                entry("Ab", Attribute.class)
        );

    }

    @Test
    public void testRenameAndCast() throws Exception {
        RenameOperation rename;
        rename = new RenameOperation(
                new ImmutableMap.Builder<String, String>()
                        .put("Identifier1", "Identifier1Measure")
                        .put("Identifier2", "Identifier2Attribute")
                        .put("Measure1", "Measure1Identifier")
                        .put("Measure2", "Measure2Attribute")
                        .put("Attribute1", "Attribute1Identifier")
                        .put("Attribute2", "Attribute2Measure")
                        .build()
                , new ImmutableMap.Builder<String, Class<? extends Component>>()
                .put("Identifier1", Measure.class)
                .put("Identifier2", Attribute.class)
                .put("Measure1", Identifier.class)
                .put("Measure2", Attribute.class)
                .put("Attribute1", Identifier.class)
                .put("Attribute2", Measure.class).build()
        );

        Dataset dataset = mock(Dataset.class);

        when(dataset.getDataStructure()).thenReturn(new DataStructure() {
            @Override
            public BiFunction<Object, Class<?>, ?> converter() {
                return null;
            }

            @Override
            public Map<String, Class<? extends Component>> roles() {
                return new ImmutableMap.Builder<String, Class<? extends Component>>()
                        .put("Identifier1", Identifier.class)
                        .put("Identifier2", Identifier.class)
                        .put("Measure1", Measure.class)
                        .put("Measure2", Measure.class)
                        .put("Attribute1", Attribute.class)
                        .put("Attribute2", Attribute.class).build();
            }

            @Override
            public Map<String, Class<?>> types() {
                return new ImmutableMap.Builder<String, Class<?>>()
                        .put("Identifier1", String.class)
                        .put("Identifier2", String.class)
                        .put("Measure1", String.class)
                        .put("Measure2", String.class)
                        .put("Attribute1", String.class)
                        .put("Attribute2", String.class).build();
            }

            @Override
            public Set<String> names() {
                return ImmutableSet.of(
                        "Identifier1", "Identifier2",
                        "Measure1", "Measure2",
                        "Attribute1", "Attribute2"
                );
            }
        });
        Dataset renamedDataset = rename.apply(dataset);

        assertThat(renamedDataset.getDataStructure().roles()).contains(
                entry("Identifier1Measure", Measure.class),
                entry("Identifier2Attribute", Attribute.class),
                entry("Measure1Identifier", Identifier.class),
                entry("Measure2Attribute", Attribute.class),
                entry("Attribute1Identifier", Identifier.class),
                entry("Attribute2Measure", Measure.class)
        );

    }
}
