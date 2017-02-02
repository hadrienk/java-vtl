package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.VTLScriptContext;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.WorkingDataset;

import javax.script.Bindings;
import javax.script.ScriptContext;
import java.util.function.Function;

public class JoinExpressionVisitor extends VTLBaseVisitor<Dataset> {
    
    private final JoinDefinitionVisitor joinDefVisitor;
    private final ScriptContext context;
    
    public JoinExpressionVisitor(ScriptContext context) {
        this.context = context;
        joinDefVisitor = new JoinDefinitionVisitor(this.context);
    }
    
    @Override
    public Dataset visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        AbstractJoinOperation joinOperation = joinDefVisitor.visit(ctx.joinDefinition());
        Bindings joinScope = joinOperation.getJoinScope();
        VTLScriptContext joinContext = createJoinContext(joinScope, context);
        JoinBodyVisitor joinBodyVisitor = new JoinBodyVisitor();
        
        Function<WorkingDataset, WorkingDataset> joinClause = joinBodyVisitor.visitJoinBody(ctx.joinBody());
        WorkingDataset workingDataset = joinOperation.workDataset();
    
        return joinClause.apply(workingDataset);
    }
    
    VTLScriptContext createJoinContext(Bindings joinScope, ScriptContext scriptContext) {
        VTLScriptContext joinContext = new VTLScriptContext();
        joinContext.setBindings(joinScope, 50);
        return joinContext;
    }
}
