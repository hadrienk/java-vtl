package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.hierarchy.HierarchyOperation;

import static com.google.common.base.Preconditions.*;

public class HierarchyVisitor extends VTLBaseVisitor<Dataset> {

    private final ReferenceVisitor referenceVisitor;

    public HierarchyVisitor(ReferenceVisitor referenceVisitor) {
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }

    @Override
    public Dataset visitHierarchyExpression(VTLParser.HierarchyExpressionContext ctx) {

        // Safe.
        Dataset dataset = (Dataset) referenceVisitor.visit(ctx.datasetRef());
        Dataset hierarchyDataset = (Dataset) referenceVisitor.visit(ctx.hierarchyReference().datasetRef());
        Component component = (Component) referenceVisitor.visitComponentRef(ctx.componentRef());

        return new HierarchyOperation(dataset, hierarchyDataset, component);

    }
}
