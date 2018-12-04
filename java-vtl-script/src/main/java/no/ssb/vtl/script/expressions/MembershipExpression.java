package no.ssb.vtl.script.expressions;

import no.ssb.vtl.model.VTLObject;

import javax.script.Bindings;

public class MembershipExpression extends VariableExpression {

    private String dataset;

    public MembershipExpression(Class<?> type, String dataset, String right) {
        super(type, right);
    }

    public String getDatasetIdentifier() {
        return dataset;
    }

    @Override
    public VTLObject resolve(Bindings bindings) {
        return super.resolve((Bindings) bindings.get(dataset));
    }
}
