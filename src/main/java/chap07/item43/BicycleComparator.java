package chap07.item43;

import java.util.Comparator;

public class BicycleComparator implements Comparator<Bicycle> {

  @Override
  public int compare(Bicycle o1, Bicycle o2) {
    return o1.getFrameSize().compareTo(o2.getFrameSize());
  }
}
