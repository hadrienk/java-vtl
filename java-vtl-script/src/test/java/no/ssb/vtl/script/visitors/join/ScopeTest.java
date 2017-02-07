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
import javax.script.ScriptContext;
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
    
    //@Test
    public void testJoinScope() throws Exception {

        JoinDefinitionVisitor visitor = new JoinDefinitionVisitor(ctx);
        VTLParser.DatasetRefContext varIdDs1= mock(VTLParser.DatasetRefContext.class);
        when(varIdDs1.getText()).thenReturn("ds1");
        when(varIdDs1.accept(any())).thenCallRealMethod();
        VTLParser.DatasetRefContext varIdDs2= mock(VTLParser.DatasetRefContext.class);
        when(varIdDs2.getText()).thenReturn("ds2");
        when(varIdDs2.accept(any())).thenCallRealMethod();
        VTLParser.JoinParamContext joinParamCtx = mock(VTLParser.JoinParamContext.class);
        when(joinParamCtx.datasetRef()).thenReturn(Arrays.asList(varIdDs1, varIdDs2));
    
    
        Map<String, Dataset> datasetMap = visitor.getDatasetParameters(joinParamCtx);
        InnerJoinOperation joinOperation = new InnerJoinOperation(datasetMap);
        Bindings joinScope = joinOperation.getJoinScope();
    
        JoinExpressionVisitor joinExpressionVisitor = new JoinExpressionVisitor(ctx);
        ScriptContext joinContext = joinExpressionVisitor.createJoinContext(joinScope, ctx);
    
        assertThat(joinContext.getAttribute("ds3")).isNull();
        assertThat(joinContext.getAttribute("ds1.id1")).isNotNull();
        assertThat(joinContext.getAttribute("id1")).isEqualTo(joinContext.getAttribute("ds2.id1"));
    }
}
