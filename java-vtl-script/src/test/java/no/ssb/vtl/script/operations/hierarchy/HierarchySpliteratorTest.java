package no.ssb.vtl.script.operations.hierarchy;

import com.google.common.collect.Lists;
import com.google.common.graph.Graphs;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;
import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;
import org.junit.Test;

import java.util.List;

public class HierarchySpliteratorTest {

    @Test
    public void test() {
        MutableValueGraph<VTLObject, Composition> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        graph.putEdgeValue(VTLObject.of("Austria"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Italy"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Holland"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Belgium"), VTLObject.of("European Union"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Luxembourg"), VTLObject.of("European Union"), Composition.UNION);

        graph.putEdgeValue(VTLObject.of("European Union"), VTLObject.of("Benelux"), Composition.UNION);
        graph.putEdgeValue(VTLObject.of("Austria"), VTLObject.of("Benelux"), Composition.COMPLEMENT);
        graph.putEdgeValue(VTLObject.of("Italy"), VTLObject.of("Benelux"), Composition.COMPLEMENT);
        graph.putEdgeValue(VTLObject.of("Mars"), VTLObject.of("Benelux"), Composition.UNION);

        System.out.println(Graphs.hasCycle(graph));

        List<DataPoint> data = Lists.newArrayList(
                DataPoint.create("Austria", 1),
                DataPoint.create("Italy", 2),
                DataPoint.create("Holland", 4),
                DataPoint.create("Belgium", 8),
                DataPoint.create("Luxembourg", 16),
                DataPoint.create("Austria", 32),
                DataPoint.create("Italy", 64)
        );
        HierarchySpliterator spliterator = new HierarchySpliterator(data.spliterator(), (o1, o2) -> 0, graph);
        spliterator.tryAdvance(System.out::println);

    }


}