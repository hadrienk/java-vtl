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

import com.google.common.collect.Lists;
import no.ssb.vtl.model.Dataset;
import no.ssb.vtl.parser.VTLBaseVisitor;
import no.ssb.vtl.parser.VTLParser;

import java.util.LinkedList;

import static com.google.common.base.Preconditions.checkNotNull;

public class StartVisitor extends VTLBaseVisitor<LinkedList<Dataset>> {

    private final AssignmentVisitor assignmentVisitor;

    public StartVisitor(AssignmentVisitor assignmentVisitor) {
        this.assignmentVisitor = checkNotNull(assignmentVisitor);
    }

    @Override
    protected LinkedList<Dataset> defaultResult() {
        return Lists.newLinkedList();
    }

    @Override
    public LinkedList<Dataset> visitAssignment(VTLParser.AssignmentContext ctx) {
        Dataset assigned = (Dataset) assignmentVisitor.visit(ctx);
        defaultResult().add(assigned);
        return defaultResult();
    }
}
