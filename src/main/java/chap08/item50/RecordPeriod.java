package chap08.item50;

import java.time.Instant;

public record RecordPeriod(Instant start, Instant end) {

    public RecordPeriod {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException(start + " should be earlier than " + end);
        }
    }
}
