package chap06.item37;

import chap06.item37.Plant.LifeCycle;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ordinal 인덱싱 대신 EnumMap 을 사용하라
 */
public class Item37 {

  private static Plant[] garden = {
    new Plant("Basil", LifeCycle.ANNUAL),
    new Plant("Carroway", LifeCycle.BIENNIAL),
    new Plant("Dill", LifeCycle.ANNUAL),
    new Plant("Lavendar", LifeCycle.PERENNIAL),
    new Plant("Parsley", LifeCycle.BIENNIAL),
    new Plant("Rosemary", LifeCycle.PERENNIAL)
  };


  public static void main(String[] args) {
    // 가독성 구린 버전
    EnumMap<LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(LifeCycle.class);
    for (LifeCycle lc : LifeCycle.values()) {
      plantsByLifeCycle.put(lc, new HashSet<>());
    }
    for (Plant plant : garden) {
      plantsByLifeCycle.get(plant.lifeCycle()).add(plant);
    }
    System.out.println(plantsByLifeCycle);

    // 스트림 버전, 스트림을 사용해서 가독성은 좋아지지만 그냥 Map 을 사용해서 성능이 저하된다
    System.out.println(Arrays.stream(garden)
      .collect(Collectors.groupingBy(Plant::lifeCycle)));

    // 스트림 & EnumMap 사용 버전
    // groupingBy 의 인자로 생성될 map 구현체를 명시한다
    System.out.println(Arrays.stream(garden)
      .collect(Collectors.groupingBy(Plant::lifeCycle,
        () -> new EnumMap<>(LifeCycle.class), Collectors.toSet())));
  }
}

/*
배열의 인덱스를 얻기 위해 ordinal 을 사용하는 것은 좋지 않다
EnumMap 은 내부적으로 ordinal 을 사용해 성능 저하도 없으니 요놈을 쓰자
 */