package no.ssb.vtl.script.functions;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.model.VTLFunction;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FunctionExpression<T extends VTLObject> implements VTLExpression2 {

    private final VTLFunction<T> wrappedFunction;
    private final List<VTLExpression2> arguments;
    private final Map<String, VTLExpression2> namedArguments;

    public FunctionExpression(VTLFunction<T> wrappedFunction, List<VTLExpression2> arguments, Map<String, VTLExpression2> namedArguments) {
        this.wrappedFunction = wrappedFunction;
        this.arguments = arguments;
        this.namedArguments = namedArguments;
    }

    public FunctionExpression(VTLFunction<T> wrappedFunction, List<VTLExpression2> arguments) {
        this(wrappedFunction, arguments, Collections.emptyMap());
    }

    public FunctionExpression(VTLFunction<T> wrappedFunction, Map<String, VTLExpression2> namedArguments) {
        this(wrappedFunction, Collections.emptyList(), namedArguments);
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        // Resolve the parameters.
        List<VTLObject> resolvedParameters = Lists.newArrayList();
        for (VTLExpression2 expression2 : arguments) {
            resolvedParameters.add(expression2.resolve(bindings));
        }
        Map<String, VTLObject> resolvedNamedParameters = Maps.newLinkedHashMap();
        for (Map.Entry<String, VTLExpression2> entry : namedArguments.entrySet()) {
            resolvedNamedParameters.put(
                    entry.getKey(),
                    entry.getValue().resolve(bindings)
            );
        }

        return wrappedFunction.invoke(
                resolvedParameters,
                resolvedNamedParameters
        );
    }

    @Override
    public Class getType() {
        return wrappedFunction.getVTLType();
    }
}
