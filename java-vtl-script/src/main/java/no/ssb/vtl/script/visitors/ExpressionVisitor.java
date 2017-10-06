package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

public class ExpressionVisitor extends VTLBaseVisitor<VTLObject> {

    LiteralVisitor literalVisitor = new LiteralVisitor();

    @Override
    public VTLObject visitLitteral(VTLParser.LitteralContext ctx) {
        return literalVisitor.visit(ctx);
    }
}
