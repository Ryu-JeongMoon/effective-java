package chap12.item89;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;

public class ElvisStealer implements Serializable {

  @Serial
  private static final long serialVersionUID = 0;

  static Elvis impersonator;
  private Elvis payload;

  static Object deserialize(byte[] sf) {
    try {
      return new ObjectInputStream(new ByteArrayInputStream(sf)).readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new IllegalArgumentException(e);
    }
  }

  @Serial
  private Object readResolve() {
    impersonator = payload;
    return new String[]{"A Fool Such as I"};
  }
}
