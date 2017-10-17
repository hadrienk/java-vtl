package no.ssb.vtl.script.visitors.join;

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
import no.ssb.vtl.model.VTLExpression2;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;
import no.ssb.vtl.script.operations.JoinAssignment;
import no.ssb.vtl.script.operations.join.WorkingDataset;
import no.ssb.vtl.script.visitors.ComponentRoleVisitor;
import no.ssb.vtl.script.visitors.ExpressionVisitor;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.util.Optional.ofNullable;

/**
 *
 */
@Deprecated
public class JoinAssignmentVisitor extends VTLBaseVisitor<JoinAssignment> {

    private final ExpressionVisitor expressionVisitor;
    private final ComponentRoleVisitor componentRoleVisitor = ComponentRoleVisitor.getInstance();
    private final WorkingDataset workingDataset;

    public JoinAssignmentVisitor(ExpressionVisitor expressionVisitor, WorkingDataset workingDataset) {
        this.expressionVisitor = checkNotNull(expressionVisitor);
        this.workingDataset = workingDataset;
    }

    @Override
    public JoinAssignment visitMembershipExpression(VTLParser.MembershipExpressionContext ctx) {
        return super.visitMembershipExpression(ctx);
    }

    @Override
    public JoinAssignment visitJoinAssignment(VTLParser.JoinAssignmentContext ctx) {

        VTLExpression2 expression2 = expressionVisitor.visit(ctx.expression());

        Optional<Component.Role> componentRole = ofNullable(componentRoleVisitor.visitComponentRole(ctx.role));
        Boolean implicit = ctx.implicit != null;

        // TODO: Support 'variable' ?
        String componentName = ctx.variable().getText();

        return new JoinAssignment(
                workingDataset,
                expression2,
                componentName,
                componentRole.orElse(Component.Role.MEASURE),
                implicit, null
        );
    }
}
