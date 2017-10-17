package no.ssb.vtl.script.visitors;

import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.error.VTLRuntimeException;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

/**
 * Abstract visitor that fails if variables are not found.
 */
public abstract class AbstractVariableVisitor<T> extends VTLBaseVisitor<T> {

    private final Bindings bindings;

    public AbstractVariableVisitor(Bindings bindings) {
        this.bindings = checkNotNull(bindings);
    }

    private static String checkVariableExist(Bindings bindings, VTLParser.VariableContext ctx) {
        String identifier = ctx.getText();
        identifier = unEscape(identifier);

        if (bindings.containsKey(identifier))
            return identifier;

        throw new VTLRuntimeException(
                format("undefined variable [%s] (scope [%s])", identifier, bindings),
                "VTL-101", ctx
        );
    }

    private static String unEscape(String identifier) {
        // Unescape.
        if (identifier.startsWith("\'") && identifier.endsWith("\'")) {
            identifier = identifier.substring(1, identifier.length() - 1);
        }
        return identifier;
    }

    protected abstract T convert(Object o);

    @Override
    public T visitMembershipExpression(VTLParser.MembershipExpressionContext ctx) {
        String leftIdentifier = checkVariableExist(bindings, ctx.leff);
        Object object = bindings.get(leftIdentifier);
        if (object instanceof Bindings) {
            Bindings bindings = (Bindings) object;
            String rightIdentifier = checkVariableExist(bindings, ctx.right);
            return convert(bindings.get(rightIdentifier));
        } else {
            return convert(object);
        }
    }

    @Override
    public T visitVariable(VTLParser.VariableContext ctx) {
        String identifier = checkVariableExist(bindings, ctx);
        return convert(bindings.get(identifier));
    }
}
