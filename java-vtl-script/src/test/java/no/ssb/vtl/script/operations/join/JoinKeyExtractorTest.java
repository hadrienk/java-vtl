package no.ssb.vtl.script.operations.join;

/*-
 * ========================LICENSE_START=================================
 * Java VTL
 * %%
 * Copyright (C) 2016 - 2018 Hadrien Kohl
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

import com.carrotsearch.randomizedtesting.annotations.Repeat;
import no.ssb.vtl.model.Component;
import no.ssb.vtl.model.DataStructure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class JoinKeyExtractorTest {

    DataStructure dataStructure;

    @Before
    public void setUp() throws Exception {
        dataStructure = DataStructure.builder()
                .put("A", Component.Role.IDENTIFIER, String.class)
                .put("B", Component.Role.IDENTIFIER, String.class)
                .put("C", Component.Role.IDENTIFIER, String.class)
                .put("D", Component.Role.IDENTIFIER, String.class)
                .put("E", Component.Role.IDENTIFIER, String.class)
                .put("F", Component.Role.IDENTIFIER, String.class)
                .build();
    }

    @Test
    @Repeat(iterations = 100)
    public void testRandom() {


    }
}
