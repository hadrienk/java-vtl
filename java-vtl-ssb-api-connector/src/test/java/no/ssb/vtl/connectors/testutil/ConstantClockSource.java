package no.ssb.vtl.connectors.testutil;

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
