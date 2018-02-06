package no.ssb.vtl.parser;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Pawel Buczek
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

public class AggregationFunctionParserTest extends GrammarTest {

    @Test
    public void testSum() throws Exception {
        parse("sum(t1) along id1", "aggregationFunction");
        parse("sum(t1.m1) group by id1", "aggregationFunction");
    }

    @Test
    public void testAvg() throws Exception {
        parse("avg(t1) along id1", "aggregationFunction");
        parse("avg(t1.m1) group by id1", "aggregationFunction");
    }

}
