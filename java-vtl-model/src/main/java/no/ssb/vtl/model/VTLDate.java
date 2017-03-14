package no.ssb.vtl.model;

import java.time.Instant;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static java.lang.String.*;

import java.lang.String;

public abstract class VTLDate extends VTLObject<Instant> {

    public static VTLDate of(String input, String dateFormat, TimeZone timeZone) {

        return new VTLDate() {
            @Override
            public Instant get() {
                if (!"YYYY".equals(dateFormat)) {
                    throw new RuntimeException(
                            format("Date format %s unsupported", dateFormat));
                }

                DateTimeFormatter formatter =
                        DateTimeFormatter.ofPattern("yyyy");
                Year year = Year.parse(input, formatter);
                return year.atDay(1).atStartOfDay(timeZone.toZoneId()).toInstant();
            }
        };

    }

    public static boolean canParse(String dateFormat) {
        if (!"YYYY".equals(dateFormat)) {
            return false;
        }

        return true;
    }

}
