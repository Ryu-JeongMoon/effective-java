package chap08.item50;

import java.util.Date;

public record RecordDatePeriod(Date start, Date end) {

  public RecordDatePeriod {
    if (start.compareTo(end) > 0) {
      throw new IllegalArgumentException(start + " should be earlier than " + end);
    }
  }
}
