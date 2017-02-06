package no.ssb.vtl.script.visitors.join;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.join.WorkingDataset;
import no.ssb.vtl.script.visitors.ReferenceVisitor;

import javax.script.SimpleBindings;
import java.util.Map;
import java.util.stream.Collectors;

public class JoinReferenceVisitor extends ReferenceVisitor {
    
    public JoinReferenceVisitor(WorkingDataset workingDataset) {
        Map<String, Object> wdsMap = workingDataset.getDataStructure().entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
        scope = new SimpleBindings(wdsMap);
    }
    
    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @param ctx
     */
    @Override
    public Object visitComponentRef(VTLParser.ComponentRefContext ctx) {
        Component component = findObject(scope, ctx.getText(), Component.class);
        if (component != null) {
            return component;
        } else {
            return super.visitComponentRef(ctx);
        }
    }
}
