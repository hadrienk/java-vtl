package no.ssb.vtl.script.expressions;

import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;

public class VariableExpression implements VTLExpression {

    private final Class<?> type;

    public String getIdentifier() {
        return identifier;
    }

    private final String identifier;

    public VariableExpression(Class<?> type, String identifier) {
        this.type = checkNotNull(type);
        this.identifier = checkNotNull(identifier);
    }

    @Override
    public Class<?> getVTLType() {
        return type;
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        return (VTLObject) bindings.get(identifier);
    }
}
