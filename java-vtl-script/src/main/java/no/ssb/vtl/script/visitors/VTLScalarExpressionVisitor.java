package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.DataPoint;
import no.ssb.vtl.model.VTLObject;
import no.ssb.vtl.parser.VTLBaseVisitor;

import java.util.function.Function;

public class VTLScalarExpressionVisitor<T extends Function<DataPoint, VTLObject>> extends VTLBaseVisitor<T> {
}
