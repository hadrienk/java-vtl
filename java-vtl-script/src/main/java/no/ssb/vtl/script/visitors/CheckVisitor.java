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

import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.model.VTLInteger;
import no.ssb.vtl.model.VTLString;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.check.CheckSingleRuleOperation;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public class CheckVisitor extends VTLBaseVisitor<Dataset> {

    private final DatasetExpressionVisitor datasetExpressionVisitor;
    private final LiteralVisitor literalVisitor = LiteralVisitor.getInstance();

    public CheckVisitor(DatasetExpressionVisitor datasetExpressionVisitor) {
        this.datasetExpressionVisitor = datasetExpressionVisitor;
    }
    
    @Override
    public Dataset visitCheckFunction(VTLParser.CheckFunctionContext ctx) {
        VTLParser.CheckParamContext checkParamContext = ctx.checkParam();

        Dataset dataset = datasetExpressionVisitor.visit(checkParamContext.variableExpression());

        CheckSingleRuleOperation.ComponentsToReturn componentsToReturn = getComponentsToReturn(checkParamContext.checkColumns());
        CheckSingleRuleOperation.RowsToReturn rowsToReturn = getRowsToReturn(checkParamContext.checkRows());

        Optional<String> errorCode = getErrorCode(checkParamContext);
        Optional<Long> errorLevel = getErrorLevel(checkParamContext);

        CheckSingleRuleOperation.Builder builder = new CheckSingleRuleOperation.Builder(dataset);
        builder.rowsToReturn(rowsToReturn);
        builder.componentsToReturn(componentsToReturn);
        if (errorCode.isPresent())
            builder.errorCode(errorCode.get());
        if (errorLevel.isPresent())
            builder.errorLevel(errorLevel.get());

        return builder.build();
    }

    @Override
    public Dataset visitRelationalExpression(VTLParser.RelationalExpressionContext ctx) {
        return datasetExpressionVisitor.visit(ctx);
    }


    private Optional<Long> getErrorLevel(VTLParser.CheckParamContext checkParamContext) {
        VTLParser.ErrorLevelContext errorLevel = checkParamContext.errorLevel();
        if (errorLevel == null)
            return Optional.empty();

        VTLInteger integer = literalVisitor.visitIntegerLiteral(errorLevel.integerLiteral());
        return Optional.of(integer.get());
    }

    private Optional<String> getErrorCode(VTLParser.CheckParamContext checkParamContext) {
        VTLParser.ErrorCodeContext errorCode = checkParamContext.errorCode();
        if (errorCode == null)
            return Optional.empty();

        VTLString string = literalVisitor.visitStringLiteral(errorCode.stringLiteral());
        return Optional.of(string.get());
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
