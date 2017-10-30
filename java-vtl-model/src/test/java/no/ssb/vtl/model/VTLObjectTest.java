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

import org.assertj.core.api.AutoCloseableSoftAssertions;
import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class VTLObjectTest {

    @Test
    public void testIntegerCompare() throws Exception {

        VTLObject one = VTLObject.of(new Integer(1));
        VTLObject two = VTLObject.of(new Integer(2));
        VTLObject otherOne = VTLObject.of(new Integer(1));

        assertThat(one.compareTo(two)).isLessThan(0);
        assertThat(two.compareTo(one)).isGreaterThan(0);
        assertThat(one.compareTo(otherOne)).isEqualTo(0);
        assertThat(otherOne.compareTo(one)).isEqualTo(0);

    }

    @Test
    public void testDoubleCompare() throws Exception {

        VTLObject one = VTLObject.of(new Double(1));
        VTLObject two = VTLObject.of(new Double(2));
        VTLObject otherOne = VTLObject.of(new Double(1));

        assertThat(one.compareTo(two)).isLessThan(0);
        assertThat(two.compareTo(one)).isGreaterThan(0);
        assertThat(one.compareTo(otherOne)).isEqualTo(0);
        assertThat(otherOne.compareTo(one)).isEqualTo(0);

    }

    @Test
    public void testDifferentNumberCompare() throws Exception {

        VTLObject one = VTLObject.of(new Double(1));
        VTLObject two = VTLObject.of(new Long(2));
        VTLObject otherOne = VTLObject.of(new Long(1));

        try (AutoCloseableSoftAssertions softly = new AutoCloseableSoftAssertions()) {
            softly.assertThatThrownBy(() -> one.compareTo(two))
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Double")
                    .hasMessageContaining(" 2 ")
                    .hasMessageContaining("Long");

            softly.assertThatThrownBy(() -> two.compareTo(one))
                    .hasMessageContaining(" 2 ")
                    .hasMessageContaining("Double")
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Long");

            softly.assertThatThrownBy(() -> assertThat(one.compareTo(otherOne)))
                    .hasMessageContaining(" 1 ")
                    .hasMessageContaining("Long")
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Double");


            softly.assertThatThrownBy(() -> assertThat(otherOne.compareTo(one)))
                    .hasMessageContaining(" 1 ")
                    .hasMessageContaining("Long")
                    .hasMessageContaining(" 1.0 ")
                    .hasMessageContaining("Double");
        }


    }
}
