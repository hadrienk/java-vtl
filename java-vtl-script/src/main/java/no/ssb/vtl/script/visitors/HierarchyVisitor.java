package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;

import static com.google.common.base.Preconditions.*;

public class HierarchyVisitor extends VTLBaseVisitor<Dataset> {

    private final ReferenceVisitor referenceVisitor;

    public HierarchyVisitor(ReferenceVisitor referenceVisitor) {
        this.referenceVisitor = checkNotNull(referenceVisitor);
    }
}
