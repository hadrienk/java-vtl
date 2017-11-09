package no.ssb.vtl.script.functions.string;

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

import no.ssb.vtl.model.VTLString;
import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class VTLLeftTrimTest {

    @Test
    public void testNull() throws Exception {
        VTLLeftTrim instance = VTLLeftTrim.getInstance();

        VTLString result = instance.invoke(Arrays.asList(
                VTLString.of((String) null)
        ));
        assertThat(result.get()).isNull();
    }

    @Test
    public void testEmptyIsNull() throws Exception {
        VTLLeftTrim instance = VTLLeftTrim.getInstance();

        VTLString result = instance.invoke(Arrays.asList(
                VTLString.of("")
        ));
        assertThat(result.get()).isNull();
    }

}
