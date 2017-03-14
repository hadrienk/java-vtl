package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import no.ssb.vtl.model.VTLDate;
import no.ssb.vtl.model.VTLExpression;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.VTLScriptEngine;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.*;

public class StringExpressionVisitor  extends VTLBaseVisitor<VTLExpression> {

    private final ReferenceVisitor referenceVisitor;
    private final DataStructure dataStructure;

    public StringExpressionVisitor(ReferenceVisitor referenceVisitor, DataStructure dataStructure) {
        this.referenceVisitor = referenceVisitor;
        this.dataStructure = dataStructure;
    }

    @Override
    public VTLExpression visitDateFromStringExpression(VTLParser.DateFromStringExpressionContext ctx) {
        ParamVisitor paramVisitor = new ParamVisitor(referenceVisitor);
        Component input = (Component) paramVisitor.visit(ctx.componentRef());
        String dateFormatQuoted = ctx.STRING_CONSTANT().getText();

        if (!dateFormatQuoted.contains("\"")) {
            throw new ParseCancellationException("The format parameter must be quoted");
        }

        String dateFormat = dateFormatQuoted.substring(1, dateFormatQuoted.length() - 1);

        if (!VTLDate.canParse(dateFormat)) {
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
                return VTLDate.of(dateAsString, dateFormat, VTLScriptEngine.getDefaultTimeZone());
            }
        }).description(format("date_from_string(%s, %s)", input, dateFormat)).build();
    }

}
