package no.ssb.vtl.script.operations;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.StaticDataset;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLObject;
import org.assertj.core.api.JUnitSoftAssertions;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import javax.script.Bindings;
import java.util.List;

public class JoinAssignmentTest {
    private VTLExpression2 expression;
    private Dataset dataset;

    @Rule
    public final JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Before
    public void setUp() throws Exception {
        expression = new VTLExpression2() {
            @Override
            public VTLObject resolve(Bindings bindings) {
                return VTLObject.of("changed");
            }

            @Override
            public Class<?> getType() {
                return String.class;
            }

        };

        DataStructure structure = DataStructure.builder()
                .put("id", Component.Role.IDENTIFIER, String.class)
                .put("measure", Component.Role.MEASURE, String.class)
                .put("attr", Component.Role.ATTRIBUTE, String.class).build();

        DataPoint dataPoint = DataPoint.create(3);
        dataPoint.set(0, VTLObject.of("idValue"));
        dataPoint.set(0, VTLObject.of("measureValue"));
        dataPoint.set(0, VTLObject.of("attrValue"));

        dataset = StaticDataset.create(structure)
                .addPoints(dataPoint)
                .build();
    }

    // Replacing identifier is not allowed.
    @Test
    public void testIdentifierFails() throws Exception {

        List<JoinAssignment> operations = Lists.newArrayList(
                new JoinAssignment(dataset, expression, "id", Component.Role.IDENTIFIER, true),
                new JoinAssignment(dataset, expression, "id", Component.Role.ATTRIBUTE, true),
                new JoinAssignment(dataset, expression, "id", Component.Role.MEASURE, true),
                new JoinAssignment(dataset, expression, "id", Component.Role.IDENTIFIER, false),
                new JoinAssignment(dataset, expression, "id", Component.Role.ATTRIBUTE, false),
                new JoinAssignment(dataset, expression, "id", Component.Role.MEASURE, false)
        );

        for (JoinAssignment operation : operations) {
            softly.assertThatThrownBy(operation::getDataStructure)
                    .hasMessageContaining("id");
        }
    }

    // When using implicit the role must be the same if the component is already present.
    @Test
    public void testImplicitFails() throws Exception {

        List<JoinAssignment> operations = Lists.newArrayList(
                new JoinAssignment(dataset, expression, "measure", Component.Role.MEASURE, true),
                new JoinAssignment(dataset, expression, "attr", Component.Role.ATTRIBUTE, true),
                new JoinAssignment(dataset, expression, "newMeasure", Component.Role.MEASURE, true),
                new JoinAssignment(dataset, expression, "newAttr", Component.Role.ATTRIBUTE, true)
        );

        List<JoinAssignment> operationsFailing = Lists.newArrayList(
                new JoinAssignment(dataset, expression, "measure", Component.Role.ATTRIBUTE, true),
                new JoinAssignment(dataset, expression, "attr", Component.Role.MEASURE, true)
        );

        for (JoinAssignment operation : operations) {
            operation.getDataStructure();
        }

        for (JoinAssignment operation : operationsFailing) {
            softly.assertThatThrownBy(operation::getDataStructure)
                    .hasMessageStartingWith("the role of the component");
        }
    }

    // Measure and attribute roles can be changed.
    @Test
    public void testChangeRoles() throws Exception {

        JoinAssignment operation;

        operation = new JoinAssignment(dataset, expression, "measure", Component.Role.ATTRIBUTE, false);

        softly.assertThat(operation.getDataStructure()).containsKey("measure");
        softly.assertThat(operation.getDataStructure().get("measure").getRole()).isEqualTo(Component.Role.ATTRIBUTE);

        operation = new JoinAssignment(dataset, expression, "attr", Component.Role.MEASURE, false);

        softly.assertThat(operation.getDataStructure()).containsKey("attr");
        softly.assertThat(operation.getDataStructure().get("attr").getRole()).isEqualTo(Component.Role.MEASURE);

    }

    @Test
    public void testNewRoles() throws Exception {

        JoinAssignment operation;

        operation = new JoinAssignment(dataset, expression, "newMeasure", Component.Role.MEASURE, false);
        softly.assertThat(operation.getDataStructure()).containsKey("newMeasure");
        softly.assertThat(operation.getDataStructure().get("newMeasure").getRole()).isEqualTo(Component.Role.MEASURE);

        operation = new JoinAssignment(dataset, expression, "newAttr", Component.Role.ATTRIBUTE, false);
        softly.assertThat(operation.getDataStructure()).containsKey("newAttr");
        softly.assertThat(operation.getDataStructure().get("newAttr").getRole()).isEqualTo(Component.Role.ATTRIBUTE);

        operation = new JoinAssignment(dataset, expression, "newId", Component.Role.IDENTIFIER, false);
        softly.assertThat(operation.getDataStructure()).containsKey("newId");
        softly.assertThat(operation.getDataStructure().get("newId").getRole()).isEqualTo(Component.Role.IDENTIFIER);

    }

}
