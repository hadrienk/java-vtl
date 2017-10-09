package no.ssb.vtl.parser;

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

import org.junit.Test;

public class JoinParserTest extends GrammarTest {

    @Test
    public void testJoin() throws Exception {
        String expression = "" +
                "[varID1, varID2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testJoinWithOn() throws Exception {
        String expression = "" +
                "[varID1, varID2 on dimensionExpr1, dimensionExpr2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testOuterJoin() throws Exception {
        String expression = "" +
                "[outer varID1, varID2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testOuterJoinWithOn() throws Exception {
        String expression = "" +
                "[outer varID1, varID2 on dimensionExpr1, dimensionExpr2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testInnerJoin() throws Exception {
        String expression = "" +
                "[inner varID1, varID2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testInnerJoinWithOn() throws Exception {
        String expression = "" +
                "[inner varID1, varID2 on dimensionExpr1, dimensionExpr2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testCrossrJoin() throws Exception {
        String expression = "" +
                "[inner varID1, varID2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

    @Test
    public void testCrossJoinWithOn() throws Exception {
        String expression = "" +
                "[inner varID1, varID2 on dimensionExpr1, dimensionExpr2]{\n" +
                "  varID2 := varID2.varID2 + 1 * 2 / varID2.varID2 - 2,\n" +
                "  varID2 := varID2.varID2 + 3 * 4 / varID2.varID2 - 5\n" +
                "}";
        parse(expression, "joinExpression");
    }

}
