package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.script.operations.join.ComponentBindings;
import no.ssb.vtl.script.visitors.AbstractVariableVisitor;

import static no.ssb.vtl.script.operations.join.ComponentBindings.*;

/**
 * Find component in a Com and
 */
public class ComponentVisitor extends AbstractVariableVisitor<Component> {

    public ComponentVisitor(ComponentBindings bindings) {
        super(bindings);
    }

    @Override
    protected Component convert(Object o) {

        if (o instanceof Component)
            return (Component) o;

        if (o instanceof ComponentReference)
            return ((ComponentReference)o).getComponent();

        throw new ClassCastException("could not cast " + o + "to Component");
    }

}
