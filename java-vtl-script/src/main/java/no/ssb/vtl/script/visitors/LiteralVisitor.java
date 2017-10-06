package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

import java.time.Instant;

public class LiteralVisitor extends VTLBaseVisitor<VTLObject> {

    @Override
    public VTLObject visitStringLiteral(VTLParser.StringLiteralContext ctx) {
        return VTLObject.of(VisitorUtil.stripQuotes(ctx.getText()));
    }

    @Override
    public VTLObject visitBooleanLiteral(VTLParser.BooleanLiteralContext ctx) {
        return VTLObject.of(Boolean.valueOf(ctx.getText()));
    }

    @Override
    public VTLObject visitIntegerLiteral(VTLParser.IntegerLiteralContext ctx) {
        return VTLObject.of(Long.valueOf(ctx.getText()));
    }

    @Override
    public VTLObject visitDateLiteral(VTLParser.DateLiteralContext ctx) {
        return VTLObject.of(Instant.parse(ctx.getText()));
    }

    @Override
    public VTLObject visitFloatLiteral(VTLParser.FloatLiteralContext ctx) {
        return VTLObject.of(Double.valueOf(ctx.getText()));
    }

    @Override
    public VTLObject visitNullLiteral(VTLParser.NullLiteralContext ctx) {
        return VTLObject.of((Object) null);
    }
}
