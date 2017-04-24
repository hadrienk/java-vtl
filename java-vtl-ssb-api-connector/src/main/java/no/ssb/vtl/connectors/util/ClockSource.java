package no.ssb.vtl.connectors.util;

import java.time.Instant;

public interface ClockSource {
    Instant currentTime();
}