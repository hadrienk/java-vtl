package no.ssb.vtl.script.visitors;

import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

public class ParamVisitor extends VTLBaseVisitor<Object> {
    
    private final ReferenceVisitor referenceVisitor;
    
    public ParamVisitor(ReferenceVisitor referenceVisitor) {
        this.referenceVisitor = referenceVisitor;
    }
    
    @Override
    public Object visitComponentRef(VTLParser.ComponentRefContext ctx) {
        return referenceVisitor.visit(ctx);
    }
    
    @Override
    public Object visitConstant(VTLParser.ConstantContext ctx) {
        String constant = ctx.getText();
        if (ctx.BOOLEAN_CONSTANT() != null) {
            return Boolean.valueOf(constant);
        } else if (ctx.FLOAT_CONSTANT() != null) {
            return Float.valueOf(constant);
        } else if (ctx.INTEGER_CONSTANT() != null) {
            return Integer.valueOf(constant);
        } else if (ctx.NULL_CONSTANT() != null) {
            return null;
        } else { //String
            return constant.replace("\"", "");
        }
    }
    
}
