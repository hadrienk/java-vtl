package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.CheckSingleRuleOperation;

import javax.script.ScriptContext;

import static com.google.common.base.Preconditions.*;

public class CheckVisitor extends VTLBaseVisitor<Dataset> {

    private final ScriptContext context;

    public CheckVisitor(ScriptContext context) {
        this.context = checkNotNull(context, "the context was null");
    }

    @Override
    public Dataset visitCheckExpression(VTLParser.CheckExpressionContext ctx) {
        VTLParser.CheckParamContext checkParamContext = ctx.checkParam();
        Dataset dataset = visit(checkParamContext.datasetExpression());
        return new CheckSingleRuleOperation(dataset, null, null, null, null);
    }

    @Override
    public Dataset visitVariableRef(VTLParser.VariableRefContext ctx) {
        return (Dataset) context.getAttribute(ctx.getText());
    }
}
