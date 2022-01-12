package chap08.item50;

import java.time.Instant;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class InstantPeriod {

  private final Instant start;
  private final Instant end;

  public InstantPeriod(Instant start, Instant end) {
    if (start.isAfter(end)) {
      throw new IllegalArgumentException(start + " should be earlier than " + end);
    }
    this.start = start;
    this.end = end;
  }
}
