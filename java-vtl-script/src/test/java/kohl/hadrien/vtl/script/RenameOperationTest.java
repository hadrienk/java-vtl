package kohl.hadrien.vtl.script;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableMap;

import org.junit.Test;

import java.util.Collections;
import kohl.hadrien.Attribute;
import kohl.hadrien.Component;
import kohl.hadrien.DataStructure;
import kohl.hadrien.Dataset;
import kohl.hadrien.Identifier;
import kohl.hadrien.Measure;

/**
 * Created by hadrien on 03/10/2016.
 */
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
    when(dataset.getDataStructure()).thenReturn(new DataStructure(
        ImmutableMap.of(
            "notfound", Identifier.class
        )
    ));
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

    when(dataset.getDataStructure()).thenReturn(new DataStructure(
        ImmutableMap.of(
            "Ia", Identifier.class,
            "Ma", Measure.class,
            "Aa", Attribute.class
        )
    ));
    Dataset renamedDataset = rename.apply(dataset);

    assertThat(renamedDataset.getDataStructure()).contains(
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

    when(dataset.getDataStructure()).thenReturn(new DataStructure(
            new ImmutableMap.Builder<String, Class<? extends Component>>()
                .put("Identifier1", Identifier.class)
                .put("Identifier2", Identifier.class)
                .put("Measure1", Measure.class)
                .put("Measure2", Measure.class)
                .put("Attribute1", Attribute.class)
                .put("Attribute2", Attribute.class).build()
        )
    );
    Dataset renamedDataset = rename.apply(dataset);

    assertThat(renamedDataset.getDataStructure()).contains(
        entry("Identifier1Measure", Measure.class),
        entry("Identifier2Attribute", Attribute.class),
        entry("Measure1Identifier", Identifier.class),
        entry("Measure2Attribute", Attribute.class),
        entry("Attribute1Identifier", Identifier.class),
        entry("Attribute2Measure", Measure.class)
    );

  }
}
