package no.ssb.vtl.model;

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
import no.ssb.vtl.model.Component.Role;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static no.ssb.vtl.model.Order.Direction.ASC;
import static no.ssb.vtl.model.Order.Direction.DESC;
import static org.assertj.core.api.Assertions.assertThat;

public class NewOrderTest {

    private DataStructure structure;
    private ArrayList<Object> data;

    @Before
    public void setUp() {
        DataStructure.Builder structureBuilder = DataStructure.builder();
        structureBuilder.put("A", Role.IDENTIFIER, String.class);
        structureBuilder.put("B", Role.IDENTIFIER, String.class);
        structureBuilder.put("C", Role.IDENTIFIER, String.class);
        structureBuilder.put("D", Role.IDENTIFIER, String.class);
        structureBuilder.put("E", Role.IDENTIFIER, String.class);
        structure = structureBuilder.build();

        data = Lists.newArrayList(
                DataPoint.create("a", "a", "a", "a", "a"),
                DataPoint.create("b", "b", "b", "b", "b"),
                DataPoint.create("c", "c", "c", "c", "c"),
                DataPoint.create("d", "d", "d", "d", "d"),
                DataPoint.create("e", "e", "e", "e", "e"),
                DataPoint.create("f", "f", "f", "f", "f")
        );

    }

    @Test
    public void testEquals() {
        NewOrder order = NewOrder.builder(structure).put("A", ASC).put("B", ASC).put("C", ASC).build();
        assertThat(order)
                .isEqualTo(NewOrder.builder(structure).put("A", ASC).put("B", ASC).put("C", ASC).build())
                .describedAs("is equal to an order with the same columns and orders");

        assertThat(order)
                .isEqualTo(NewOrder.builder(structure).put("A", ASC).put("B", ASC).put("C", ASC).put("D", ASC).build())
                .describedAs("is equal to an order with more columns but same order");

        assertThat(order)
                .isEqualTo(NewOrder.builder(structure).put("A", ASC).put("B", ASC).put("C", ASC).put("D", DESC).build())
                .describedAs("is equal to an order with extra columns with different order");

        assertThat(order)
                .isNotEqualTo(NewOrder.builder(structure).put("A", DESC).put("B", ASC).put("C", ASC).build())
                .describedAs("is NOT equal to an order with the same columns but different orders");

        assertThat(order)
                .isNotEqualTo(NewOrder.builder(structure).put("A", ASC).put("B", ASC).build())
                .describedAs("is NOT equal to an order with less columns and same order");

        assertThat(order)
                .isNotEqualTo(NewOrder.builder(structure).put("A", DESC).put("B", ASC).put("C", ASC).put("D", ASC).build())
                .describedAs("is NOT equal to an order with more columns but different orders");

        assertThat(order).isNotEqualTo(null);

        assertThat(order).isEqualTo(order);

    }
}