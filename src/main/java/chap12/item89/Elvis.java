package chap12.item89;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

public class Elvis implements Serializable {

  public static final Elvis INSTANCE = new Elvis();

  private String[] favoriteSongs = {"Hound Dog", "Heartbreak Hotel"};

  private Elvis() {
  }

  public void printFavorites() {
    System.out.println(Arrays.toString(favoriteSongs));
  }

  @Serial
  public Object readResolve() {
    return INSTANCE;
  }
}
