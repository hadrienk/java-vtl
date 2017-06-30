package no.ssb.vtl.connectors.util;

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

import java.time.Instant;

/**
 * Supports mocking of clock source. Hence possible to control time returned for tests.
 * <p>
 * Use TimeUtil.now() instead of new Instant() in source code in order to be able to mock the time.
 *
 */
public final class TimeUtil {
    private static final ClockSource DEFAULT_CLOCKSOURCE = () -> Instant.now();
    private static ClockSource clockSource = DEFAULT_CLOCKSOURCE;

    private TimeUtil() {
        // Utility class
    }

    /**
     * Gets the time now. Same as new Instant(), but with this time can be mocked in tests.
     */
    public static Instant now() {
        return clockSource.currentTime();
    }

    /**
     * Set clock source. Use in test when control of time is required
     */
    public static void setClockSource(ClockSource clockSource) {
        TimeUtil.clockSource = clockSource;
    }

    /**
     * Reverts clock source to original value. Call this when test is done
     */
    public static void revertClockSource() {
        clockSource = DEFAULT_CLOCKSOURCE;
    }
}
