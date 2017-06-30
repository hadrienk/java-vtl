package no.ssb.vtl.connectors.testutil;

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

import no.ssb.vtl.connectors.util.ClockSource;

import java.time.Instant;


/**
 * Clock source to be used for test that always returns the same time.
 * 
 */
public class ConstantClockSource implements ClockSource {
    private final Instant constantInstant;

    public ConstantClockSource(Instant instant) {
        this.constantInstant = instant;
    }

    @Override
    public Instant currentTime() {
        return constantInstant;
    }
}
