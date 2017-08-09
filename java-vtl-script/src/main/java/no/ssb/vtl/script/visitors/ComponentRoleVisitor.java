package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

public class ComponentRoleVisitor extends VTLBaseVisitor<Component.Role> {

    @Override
    public Component.Role visitComponentRole(VTLParser.ComponentRoleContext ctx) {
        if (ctx == null || ctx.role == null)
            return null;

        switch (ctx.role.getType()) {
            case VTLParser.IDENTIFIER:
                return Component.Role.IDENTIFIER;
            case VTLParser.MEASURE:
                return Component.Role.MEASURE;
            case VTLParser.ATTRIBUTE:
                return Component.Role.ATTRIBUTE;
            default:
                throw new RuntimeException("unknown component type " + ctx.getText());
        }
    }
}
