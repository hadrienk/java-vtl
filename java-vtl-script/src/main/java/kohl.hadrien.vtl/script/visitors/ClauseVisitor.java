package kohl.hadrien.vtl.script.visitors;

/*-
 * #%L
 * java-vtl-script
 * %%
 * Copyright (C) 2016 Hadrien Kohl
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
 * #L%
 */

import com.google.common.collect.ImmutableMap;
import kohl.hadrien.*;
import kohl.hadrien.vtl.script.RenameOperation;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import static java.util.Optional.ofNullable;

/**
 * A visitor that handles the clauses.
 */
public class ClauseVisitor extends VTLBaseVisitor<Function<Dataset, Dataset>> {

    @Override
    protected Function<Dataset, Dataset> defaultResult() {
        return Function.identity();
    }

    @Override
    protected Function<Dataset, Dataset> aggregateResult(Function<Dataset, Dataset> aggregate,
                                                         Function<Dataset, Dataset> nextResult) {
        return aggregate.andThen(nextResult);
    }

    @Override
    public Function<Dataset, Dataset> visitRenameClause(VTLParser.RenameClauseContext ctx) {
        List<VTLParser.RenameParamContext> parameters = ctx.renameParam();

        ImmutableMap.Builder<String, String> names = ImmutableMap.builder();
        ImmutableMap.Builder<String, Class<? extends Component>> roles = ImmutableMap.builder();

        for (VTLParser.RenameParamContext parameter : parameters) {
            String from = parameter.from.getText();
            String to = parameter.to.getText();
            names.put(from, to);

            Optional<String> role = ofNullable(parameter.role()).map(VTLParser.RoleContext::getText);
            if (role.isPresent()) {
                Class<? extends Component> roleClass;
                switch (role.get()) {
                    case "IDENTIFIER":
                        roleClass = Identifier.class;
                        break;
                    case "MEASURE":
                        roleClass = Measure.class;
                        break;
                    case "ATTRIBUTE":
                        roleClass = Attribute.class;
                        break;
                    default:
                        throw new RuntimeException("unknown component type " + role.get());
                }
                roles.put(from, roleClass);
            }
        }

        return new RenameOperation(names.build(), roles.build());
    }
}
