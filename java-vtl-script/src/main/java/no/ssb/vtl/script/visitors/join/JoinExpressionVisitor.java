package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.AbstractJoinOperation;
import no.ssb.vtl.script.operations.join.WorkingDataset;

import javax.script.ScriptContext;
import java.util.function.Function;

public class JoinExpressionVisitor extends VTLBaseVisitor<Dataset> {
    
    private final JoinDefinitionVisitor joinDefVisitor;
    
    public JoinExpressionVisitor(ScriptContext context) {
        joinDefVisitor = new JoinDefinitionVisitor(context);
    }
    
    @Override
    public Dataset visitJoinExpression(VTLParser.JoinExpressionContext ctx) {
        AbstractJoinOperation joinOperation = joinDefVisitor.visit(ctx.joinDefinition());
        JoinBodyVisitor joinBodyVisitor = new JoinBodyVisitor();
        
        Function<WorkingDataset, WorkingDataset> joinClause = joinBodyVisitor.visitJoinBody(ctx.joinBody());
        WorkingDataset workingDataset = joinOperation.workDataset();
    
        return joinClause.apply(workingDataset);
    }
}
