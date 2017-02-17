package no.ssb.vtl.script.visitors;

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.CheckSingleRuleOperation;

import java.util.Optional;
import java.util.function.Supplier;

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

        Optional<CheckSingleRuleOperation.ComponentsToReturn> componentsToReturnEnum = getComponentsToReturn(checkParamContext.checkColumns());
        Optional<CheckSingleRuleOperation.RowsToReturn> rowsToReturn = getRowsToReturn(checkParamContext.checkRows());

        Optional<String> errorCode = getErrorCode(checkParamContext);
        Optional<Integer> errorLevel = getErrorLevel(checkParamContext);

        return new CheckSingleRuleOperation(dataset,
                rowsToReturn,
                componentsToReturnEnum,
                errorCode,
                errorLevel);
    }

    @Override
    public Dataset visitVariableRef(VTLParser.VariableRefContext ctx) {
        return (Dataset) referenceVisitor.visit(ctx);
    }

    @Override
    public Dataset visitRelationalExpression(VTLParser.RelationalExpressionContext ctx) {
        Supplier<Dataset> datasetSupplier = relationalVisitor.visit(ctx);
        return datasetSupplier.get();
    }


    private Optional<Integer> getErrorLevel(VTLParser.CheckParamContext checkParamContext) {
        Integer errorLevel = null;
        if (checkParamContext.errorLevel() != null) {
            errorLevel = Integer.valueOf(checkParamContext.errorLevel().getText());
        }
        return Optional.ofNullable(errorLevel);
    }

    private Optional<String> getErrorCode(VTLParser.CheckParamContext checkParamContext) {
        String errorCode = null;
        if (checkParamContext.errorCode() != null) {
             errorCode = checkParamContext.errorCode().getText();
        }
        return Optional.ofNullable(errorCode);
    }


    private Optional<CheckSingleRuleOperation.ComponentsToReturn> getComponentsToReturn(VTLParser.CheckColumnsContext checkColumnsContext) {
        Optional<String> componentsToReturn = ofNullable(checkColumnsContext).map(VTLParser.CheckColumnsContext::getText);
        CheckSingleRuleOperation.ComponentsToReturn componentsToReturnEnum = null;
        if (componentsToReturn.isPresent()) {
            switch (componentsToReturn.get()) {
                case "measures":
                    componentsToReturnEnum = CheckSingleRuleOperation.ComponentsToReturn.MEASURES;
                    break;
                case "condition":
                    componentsToReturnEnum = CheckSingleRuleOperation.ComponentsToReturn.CONDITION;
                    break;
                default:
                    throw new RuntimeException("unknown parameter value 'checkColumns' " + componentsToReturn.get());
            }
        }
        return Optional.ofNullable(componentsToReturnEnum);
    }

    private Optional<CheckSingleRuleOperation.RowsToReturn> getRowsToReturn(VTLParser.CheckRowsContext checkColumnsContext) {
        Optional<String> rowsToReturn = ofNullable(checkColumnsContext).map(VTLParser.CheckRowsContext::getText);
        CheckSingleRuleOperation.RowsToReturn rowsToReturnEnum = null;
        if (rowsToReturn.isPresent()) {
            switch (rowsToReturn.get()) {
                case "not_valid":
                    rowsToReturnEnum = CheckSingleRuleOperation.RowsToReturn.NOT_VALID;
                    break;
                case "valid":
                    rowsToReturnEnum = CheckSingleRuleOperation.RowsToReturn.VALID;
                    break;
                case "all":
                    rowsToReturnEnum = CheckSingleRuleOperation.RowsToReturn.ALL;
                    break;
                default:
                    throw new RuntimeException("unknown parameter value 'checkColumns' " + rowsToReturn.get());
            }
        }
        return Optional.ofNullable(rowsToReturnEnum);
    }
}
