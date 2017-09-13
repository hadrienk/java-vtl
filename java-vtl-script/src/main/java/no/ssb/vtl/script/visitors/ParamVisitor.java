package no.ssb.vtl.script.visitors;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

import static java.lang.String.*;

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
            return Double.valueOf(constant);
        } else if (ctx.INTEGER_CONSTANT() != null) {
            return Long.valueOf(constant);
        } else if (ctx.NULL_CONSTANT() != null) {
            return null;
        } else if (ctx.STRING_CONSTANT() != null) {
            return VisitorUtil.stripQuotes(ctx.STRING_CONSTANT());
        } else {
            throw new RuntimeException(format("unsupported constant type %s", constant)
            );
        }
        //TODO: Merge this with JoinCalcClauseVisitor.visitJoinCalcAtom()
    }
    
}
