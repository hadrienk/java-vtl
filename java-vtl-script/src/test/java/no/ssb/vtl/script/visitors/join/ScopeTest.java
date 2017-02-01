package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.VTLScriptContext;
import no.ssb.vtl.script.operations.join.InnerJoinOperation;
import org.junit.Before;
import org.junit.Test;

import javax.script.Bindings;
import java.util.Arrays;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ScopeTest {
    
    private final Dataset ds1 = mock(Dataset.class);
    private final Dataset ds2 = mock(Dataset.class);
    private final Dataset ds3 = mock(Dataset.class);
    private final VTLScriptContext ctx = new VTLScriptContext();
    
    @Before
    public void setUp() throws Exception {
        when(ds1.getDataStructure()).thenReturn(DataStructure.of(
                (o, aClass) -> o,
                "id1", Component.Role.IDENTIFIER, String.class,
                "x", Component.Role.MEASURE, Integer.class,
                "y", Component.Role.MEASURE, Double.class
        ));
        when(ds2.getDataStructure()).thenReturn(DataStructure.of(
                (o, aClass) -> o,
                "id1", Component.Role.IDENTIFIER, String.class,
                "x", Component.Role.MEASURE, Integer.class,
                "y", Component.Role.MEASURE, Double.class,
                "z", Component.Role.MEASURE, String.class
        ));
        
        ctx.setAttribute("ds1", ds1);
        ctx.setAttribute("ds2", ds2);
        ctx.setAttribute("ds3", ds3);
        
    }
    
    @Test
    public void testSimpleScope() throws Exception {
        ctx.setAttribute("ds1", ds1);
    
        assertThat(ctx.getAttribute("ds1")).isEqualTo(ds1);
        assertThat(ctx.getAttribute("ds1")).isNotEqualTo(ds2);
    
        assertThat(ctx.getAttribute("ds0")).isNull();
    }
    
    @Test
    public void testJoinScope() throws Exception {
        JoinDefinitionVisitor visitor = new JoinDefinitionVisitor(ctx);
        VTLParser.VarIDContext varIdDs1= mock(VTLParser.VarIDContext.class);
        when(varIdDs1.getText()).thenReturn("ds1");
        VTLParser.VarIDContext varIdDs2= mock(VTLParser.VarIDContext.class);
        when(varIdDs2.getText()).thenReturn("ds2");
    
        
        Map<String, Dataset> datasetMap = visitor.createJoinScope(Arrays.asList(varIdDs1, varIdDs2));
        InnerJoinOperation joinOperation = new InnerJoinOperation(datasetMap);
        Bindings joinScope = joinOperation.getJoinScope();
        joinScope.putAll(datasetMap);
        int JOIN_SCOPE = 50;
        ctx.setBindings(joinScope, JOIN_SCOPE);
    
        assertThat(ctx.getAttribute("ds3", JOIN_SCOPE)).isNull();
        assertThat(ctx.getAttribute("ds1.id1", JOIN_SCOPE)).isNotNull();
        assertThat(ctx.getAttribute("id1", JOIN_SCOPE)).isEqualTo(ctx.getAttribute("ds2.id1"));
    }
}
