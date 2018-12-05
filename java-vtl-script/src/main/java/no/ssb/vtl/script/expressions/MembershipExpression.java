package no.ssb.vtl.script.expressions;

import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;

public class MembershipExpression extends VariableExpression {

    private String dataset;

    public MembershipExpression(Class<?> type, String dataset, String right) {
        super(type, right);
        this.dataset = checkNotNull(dataset);
    }

    public String getDatasetIdentifier() {
        return dataset;
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        return super.resolve((Bindings) bindings.get(dataset));
    }
}
