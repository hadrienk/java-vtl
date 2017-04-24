package no.ssb.vtl.connectors.util;

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
