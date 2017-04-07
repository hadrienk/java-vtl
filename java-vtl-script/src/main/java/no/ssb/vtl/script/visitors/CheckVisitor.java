package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.check.CheckSingleRuleOperation;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.Optional;

import static java.util.Optional.*;

public class CheckVisitor extends VTLBaseVisitor<Dataset> {

    private final RelationalVisitor relationalVisitor;
    private final ReferenceVisitor referenceVisitor;

    public CheckVisitor(RelationalVisitor relationalVisitor, ReferenceVisitor referenceVisitor) {
        this.relationalVisitor = relationalVisitor;
        this.referenceVisitor = referenceVisitor;
    }

    @Override
    public Dataset visitCheckExpression(VTLParser.CheckExpressionContext ctx) {
        VTLParser.CheckParamContext checkParamContext = ctx.checkParam();
        Dataset dataset = visit(checkParamContext.datasetExpression());

        CheckSingleRuleOperation.ComponentsToReturn componentsToReturn = getComponentsToReturn(checkParamContext.checkColumns());
        CheckSingleRuleOperation.RowsToReturn rowsToReturn = getRowsToReturn(checkParamContext.checkRows());

        String errorCode = getErrorCode(checkParamContext);
        Long errorLevel = getErrorLevel(checkParamContext);

        return new CheckSingleRuleOperation.Builder(dataset)
                .rowsToReturn(rowsToReturn)
                .componentsToReturn(componentsToReturn)
                .errorCode(errorCode)
                .errorLevel(errorLevel)
                .build();
    }

    @Override
    public Dataset visitVariableRef(VTLParser.VariableRefContext ctx) {
        return (Dataset) referenceVisitor.visit(ctx);
    }

    @Override
    public Dataset visitRelationalExpression(VTLParser.RelationalExpressionContext ctx) {
        return relationalVisitor.visit(ctx);
    }


    private Long getErrorLevel(VTLParser.CheckParamContext checkParamContext) {
        if (checkParamContext.errorLevel() != null) {
            return Long.valueOf(checkParamContext.errorLevel().getText());
        }
        return null;
    }

    private String getErrorCode(VTLParser.CheckParamContext checkParamContext) {
        if (checkParamContext.errorCode() != null) {
            return VisitorUtil.stripQuotes(checkParamContext.errorCode().STRING_CONSTANT());
        }
        return null;
    }

    private CheckSingleRuleOperation.ComponentsToReturn getComponentsToReturn(VTLParser.CheckColumnsContext checkColumnsContext) {
        Optional<String> componentsToReturn = ofNullable(checkColumnsContext).map(VTLParser.CheckColumnsContext::getText);

        if (componentsToReturn.isPresent() && !componentsToReturn.get().isEmpty()) {
            return CheckSingleRuleOperation.ComponentsToReturn.valueOf(componentsToReturn.get().toUpperCase());
        }

        return null;
    }

    private CheckSingleRuleOperation.RowsToReturn getRowsToReturn(VTLParser.CheckRowsContext checkColumnsContext) {
        Optional<String> rowsToReturn = ofNullable(checkColumnsContext).map(VTLParser.CheckRowsContext::getText);

        if (rowsToReturn.isPresent() && !rowsToReturn.get().isEmpty()) {
            return CheckSingleRuleOperation.RowsToReturn.valueOf(rowsToReturn.get().toUpperCase());
        }

        return null;
    }
}
