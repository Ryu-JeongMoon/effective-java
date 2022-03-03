package chap12.item88;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

public class Period implements Serializable {

  private final Date start;
  private final Date end;

  public Period(Date start, Date end) {
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());

    if (this.start.compareTo(this.end) > 0)
      throw new IllegalArgumentException();
  }

  public Date start() {
    return new Date(start.getTime());
  }

  public Date end() {
    return new Date(end.getTime());
  }

  @Override
  public String toString() {
    return "Period{" +
      "start=" + start +
      ", end=" + end +
      '}';
  }

  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    s.defaultReadObject();
  }

  private void writeObject(ObjectOutputStream s) throws IOException {
    s.defaultWriteObject();
  }
}
