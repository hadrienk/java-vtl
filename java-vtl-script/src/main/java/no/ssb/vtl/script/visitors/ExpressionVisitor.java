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

import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.error.VTLRuntimeException;

import javax.script.Bindings;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.String.format;

public class ExpressionVisitor extends VTLBaseVisitor<VTLObject> {

    private final LiteralVisitor literalVisitor = LiteralVisitor.getInstance();
    private final Bindings scope;

    public ExpressionVisitor(Bindings scope) {
        this.scope = checkNotNull(scope);
    }

    @Override
    public VTLObject visitLiteral(VTLParser.LiteralContext ctx) {
        return literalVisitor.visit(ctx);
    }

    @Override
    public VTLObject visitVariable(VTLParser.VariableContext ctx) {
        String identifier = ctx.getText();

        // Unescape.
        if (identifier.startsWith("\'") && identifier.endsWith("\'")) {
            identifier = identifier.substring(1, identifier.length() - 1);
        }

        if (scope.containsKey(identifier)) {
            Object object = scope.get(identifier);
            if (object instanceof VTLObject)
                return (VTLObject) object;
            else
                throw new VTLRuntimeException(
                        format("unknown object [%s]", object), "VTL-101", ctx
                );
        }
        throw new VTLRuntimeException(
                format("undefined variable [%s] (scope [%s]", identifier, scope),
                "VTL-101", ctx
        );

    }
}
