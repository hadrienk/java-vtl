package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

import javax.script.Bindings;

public class ReferenceVisitor extends VTLBaseVisitor<VTLObject>{
    
    private Bindings scope;
    
    public ReferenceVisitor(Bindings scope) {
        this.scope = scope;
    }
    
    
    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @param ctx
     */
    @Override
    public VTLObject visitComponentRef(VTLParser.ComponentRefContext ctx) {
        Object o;
        if (!ctx.datasetRef().isEmpty()) {
            Dataset ds = visit(ctx.datasetRef()).asDataset();
            o = ds.getDataStructure().get(ctx.componentID().getText());
        } else {
            o = scope.get(ctx.componentID().getText());
        }
        return VTLObject.wrap(o);
    }
    
    
    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     * @param ctx
     */
    @Override
    public VTLObject visitDatasetRef(VTLParser.DatasetRefContext ctx) {
        return VTLObject.wrap(scope.get(ctx.getText()));
    }
}
