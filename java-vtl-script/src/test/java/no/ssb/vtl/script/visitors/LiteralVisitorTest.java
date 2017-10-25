package no.ssb.vtl.script.visitors;

/*
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2017 Hadrien Kohl
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * =========================LICENSE_END==================================
 */

import org.assertj.core.api.JUnitSoftAssertions;
import org.junit.Rule;
import org.junit.Test;

public class LiteralVisitorTest {

    @Rule
    public JUnitSoftAssertions softly = new JUnitSoftAssertions();

    @Test
    public void testStripQuotes() throws Exception {

        //VTL 1.1 Part 1, line 2907
        softly.assertThat(LiteralVisitor.stripQuotes("")).isEqualTo("");

        softly.assertThat(LiteralVisitor.stripQuotes("test")).isEqualTo("test");

        softly.assertThat(LiteralVisitor.stripQuotes("\"test\"")).isEqualTo("test");

        softly.assertThat(LiteralVisitor.stripQuotes("\"'test'\"")).isEqualTo("'test'");

        //escaped quote
        softly.assertThat(LiteralVisitor.stripQuotes("\"a\"\"b\"")).isEqualTo("a\"b");
    }

}
