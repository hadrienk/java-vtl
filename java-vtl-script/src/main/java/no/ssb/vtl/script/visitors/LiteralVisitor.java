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

import java.time.Instant;

public class LiteralVisitor extends VTLBaseVisitor<VTLObject> {

    private static final LiteralVisitor instance = new LiteralVisitor();

    public static LiteralVisitor getInstance() {
        return instance;
    }

    private LiteralVisitor() {
        // private
    }

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
