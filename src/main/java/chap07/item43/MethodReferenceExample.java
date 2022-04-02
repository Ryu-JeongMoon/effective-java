package chap07.item43;

import java.util.List;

public class MethodReferenceExample {

  public static void main(String[] args) {
    Bicycle bicycle1 = new Bicycle("panda", 55);
    Bicycle bicycle2 = new Bicycle("bear", 66);

    List<Bicycle> bicycles = List.of(bicycle1, bicycle2);

    BicycleComparator bicycleComparator = new BicycleComparator();
    List<Bicycle> sortedBicycles = bicycles.stream()
      .sorted(bicycleComparator)
      .toList();

    NonBicycle nonBicycle = new NonBicycle();

    Bicycle bicycle3 = new Bicycle("yahoo", 77);

    List<String> strings = bicycles.stream()
      .map(nonBicycle::getYaho)
      .toList();

    System.out.println("strings = " + strings);

    System.out.println("sortedBicycles = " + sortedBicycles);
  }

//  @Data
//  @NoArgsConstructor
//  @AllArgsConstructor
//  public class Bicycle {
//
//    private String brand;
//    private Integer frameSize;
//  }
//
//  public class BicycleComparator implements Comparator<Bicycle> {
//
//    @Override
//    public int compare(Bicycle o1, Bicycle o2) {
//      return o1.getFrameSize().compareTo(o2.getFrameSize());
//    }
//  }
}
