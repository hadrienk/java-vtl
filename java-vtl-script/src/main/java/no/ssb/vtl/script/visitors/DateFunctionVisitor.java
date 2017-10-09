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

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLDate;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.VTLScriptEngine;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.*;

public class DateFunctionVisitor extends VTLBaseVisitor<VTLExpression> {

    private final ReferenceVisitor referenceVisitor;
    private final DataStructure dataStructure;

    public DateFunctionVisitor(ReferenceVisitor referenceVisitor, DataStructure dataStructure) {
        this.referenceVisitor = referenceVisitor;
        this.dataStructure = dataStructure;
    }

    @Override
    public VTLExpression visitDateFromStringFunction(VTLParser.DateFromStringFunctionContext ctx) {
        ParamVisitor paramVisitor = new ParamVisitor(referenceVisitor);
        Component input = (Component) paramVisitor.visit(ctx.componentRef());
        TerminalNode dateFormat = ctx.STRING_CONSTANT();

        String dateFormatStripped = VisitorUtil.stripQuotes(dateFormat);

        if (!VTLDate.canParse(dateFormatStripped)) {
            throw new ParseCancellationException(
                    format("Date format %s unsupported", dateFormat));
        }

        if (input.getType() != String.class) {
            throw new ParseCancellationException(
                    format("Input must be String type, was %s", input.getType()));
        }

        return new VTLExpression.Builder(Instant.class, dataPoint -> {
            Map<Component, VTLObject> map = dataStructure.asMap(dataPoint);
            Optional<VTLObject> vtlObject = Optional.ofNullable(map.get(input));
            if (!vtlObject.isPresent()) {
                throw new RuntimeException(
                        format("Component %s not found in data structure", input));
            }
            if (vtlObject.get().get() == null) {
                return VTLObject.NULL;
            } else {
                String dateAsString = (String) vtlObject.get().get();
                return VTLDate.of(dateAsString, dateFormatStripped, VTLScriptEngine.getTimeZone());
            }
        }).description(format("date_from_string(%s, %s)", input, dateFormat)).build();
    }

}
