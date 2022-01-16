package chap12.item88;

import chap08.item50.Period;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class MutablePeriod {

  public final Period period;

  public Date start;

  public Date end;

  public MutablePeriod() {
    try {
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      ObjectOutputStream out = new ObjectOutputStream(bos);

      out.writeObject(new Period(new Date(), new Date()));

      byte[] ref = {0x71, 0, 0x7e, 0, 5};
      bos.write(ref);
      ref[4] = 4;
      bos.write(ref);

      ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
      period = (Period) in.readObject();
      start = (Date) in.readObject();
      end = (Date) in.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new AssertionError(e);
    }
  }

  public static void main(String[] args) {
    MutablePeriod mp = new MutablePeriod();
    Period p = mp.period;
    Date pEnd = mp.end;

    pEnd.setYear(78);
    System.out.println("p = " + p);

    pEnd.setYear(69);
    System.out.println("p = " + p);
  }

  private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
    s.defaultReadObject();
    start = new Date(start.getTime());
    end = new Date(end.getTime());

    if (start.compareTo(end) > 0) {
      throw new InvalidObjectException(start + "가 " + end + "보다 늦다.");
    }
  }
}
