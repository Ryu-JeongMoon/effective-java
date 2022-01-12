package chap07.item43;

import java.util.HashMap;

/**
 * 람다보다는 메서드 참조를 사용하라
 */
public class Item43 {

  public static void main(String[] args) {
    HashMap<String, Integer> map = new HashMap<>();
    map.put("panda", 5);
    map.put("bear", 9);

    String key = "panda";
    // 람다를 이용할 시 간단한 연산에도 parameter 넘겨주어야 한다
    map.merge(key, 10, (s, i) -> s + i);

    // 메서드 참조 전달 시 더 간단해지는 코드
    map.merge(key, 10, Integer::sum);

    for (Integer value : map.values()) {
      System.out.println("value = " + value);
    }
  }
}

/*
Map merge api
(key, new value, BiFunction)
key 있으면 현재 value 에 new value 를 BiFunction 연산 수행 후 덮어쓰기

메서드 참조를 사용하는 경우 장단점이 있을 것 같다
자바 라이브러리 혹은 자주 사용되는 라이브러리의 메서드는 메서드 참조로 넘겨도 알아보기 쉬운데
개발자가 직접 만든 메서드의 경우에는 작동 방식을 알기 위해 찾아보거나 해야하니
트레이드오프를 생각해서 람다 / 메서드 참조 사용할 것
 */