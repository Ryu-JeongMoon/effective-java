package chap03.item11;

import chap03.item10.PhoneNumber;
import java.util.HashMap;
import java.util.Map;

/**
 * equals 재정의하려거든 hashCode 도 재정의하라
 */
public class Item11 {

  public static void main(String[] args) {
    Map<PhoneNumber, String> m = new HashMap<>();
    m.put(new PhoneNumber(707, 867, 5309), "Jenny");

    // null 반환 ?!
    // hashCode 재정의해주면 "Jenny" 가 나온당
    String s = m.get(new PhoneNumber(707, 867, 5309));
    System.out.println("s = " + s);
  }
}
/*
왜 해줘야 하나용?
hashCode 의 일반 규약을 어기게되어 HashMap, HashSet 같은 컬렉션의 원소로 사용할 때 문제를 일으킬 수 있기 때문이다
일반 규약 중 2번 녀석이 중요

hashCode 일반 규약
1. equals 비교 정보가 달라지지 않았다면 hashCode 는 일관적인 값을 줘야한다
2. 논리적으로 같은 객체는 같은 해시코드를 반환해야 한다

hash 를 쓰는 녀석들은 해시값이 다른 경우엔 비교도 하지 않도록 최적화되어 있기 땜시
hashCode 를 재정의하지 않은 PhoneNumber 클래스를 이용할 때 null 을 반환하게 되었다 이말임둥

결론은 equals, hashCode 재정의에 대하여 알고는 있으되 AutoValue, @EqualsAndHashCode 쓰는게 편하다
 */