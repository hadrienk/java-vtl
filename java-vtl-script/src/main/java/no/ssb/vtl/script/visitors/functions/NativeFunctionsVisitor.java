package no.ssb.vtl.script.visitors.functions;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.functions.VTLAbs;

import java.util.List;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

public class NativeFunctionsVisitor extends VTLBaseVisitor<VTLObject> {

    static ImmutableMap<String, VTLFunction> functions;

    static {
        functions = ImmutableMap.<String, VTLFunction>builder()
                .put("abs", new VTLAbs())
                .build();
    }

    private final VTLBaseVisitor<VTLObject> expressionVisitor;

    public NativeFunctionsVisitor(VTLBaseVisitor<VTLObject> expressionVisitor) {
        this.expressionVisitor = checkNotNull(expressionVisitor);
    }

    @Override
    public VTLObject visitNativeCall(VTLParser.NativeCallContext ctx) {
        if (functions.containsKey(ctx.functionName.getText())) {
            VTLFunction functionInstance = functions.get(ctx.functionName.getText());
            VTLParser.FunctionParametersContext parameters = ctx.functionParameters();
            return functionInstance.invoke(
                    evaluateParamerers(parameters.expression()),
                    evaluateNamedParameters(parameters.namedExpression())
            );
        } else {
            throw new UnsupportedOperationException("NOT IMPLEMENTED");
        }
    }

    private Map<String, VTLObject> evaluateNamedParameters(List<VTLParser.NamedExpressionContext> namedExpressionContexts) {
        ImmutableMap.Builder<String, VTLObject> builder = ImmutableMap.builder();
        for (VTLParser.NamedExpressionContext expressionContext : namedExpressionContexts) {
            String name = expressionContext.name.getText();
            VTLObject value = expressionVisitor.visit(expressionContext);
            builder.put(name, value);
        }
        return builder.build();
    }

    private List<VTLObject> evaluateParamerers(List<VTLParser.ExpressionContext> expression) {
        ImmutableList.Builder<VTLObject> builder = ImmutableList.builder();
        for (VTLParser.ExpressionContext expressionContext : expression) {
            VTLObject value = expressionVisitor.visit(expressionContext);
            builder.add(value);
        }
        return builder.build();
    }


}
