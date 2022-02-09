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
    map.merge(key, 10, (count, increment) -> count + increment);

    // 메서드 참조 전달 시 더 간단해지는 코드
    map.merge(key, 10, Integer::sum);

    for (Integer value : map.values()) {
      System.out.println("value = " + value);
    }
  }
}

/*
Java8 Map merge api -> merge(key, new value, BiFunction) merge 구현 주기적으로 구경해볼 것!
key 없으면 그냥 저장
key 있으면 현재 value 에 new value 를 BiFunction 연산 수행 후 덮어쓰기

메서드 참조를 사용하는 경우 장단점이 있을 것 같다
자바 라이브러리 혹은 자주 사용되는 라이브러리의 메서드는 메서드 참조로 넘겨도 알아보기 쉬운데
개발자가 직접 만든 메서드의 경우에는 작동 방식을 알기 위해 찾아보거나 해야하니
트레이드오프를 생각해서 람다 / 메서드 참조 사용할 것

메서드 참조를 사용하는 궁극적인 이유는 간결함 때문이다
메서드 매개변수가 많아질수록 메서드 참조로 줄일 수 있는 코드 양이 많아지니 이득
단 Class 에 속한 메서드를 사용하는 경우 or 서술적인 이름을 사용해 표현한 인스턴스의 경우 오히려 람다가 간결할 수 있다
같은 예시로 항등 함수 Function.identity() 보다 x -> x 를 직접 쓰는 편이 낫다
메참 : BlahBlahBlahBlahBlahBlahBlahBlah::method
람다 : () -> method()

오마이갓
함수형 인터페이스 메서드 참조 구현 모르겄다
 */