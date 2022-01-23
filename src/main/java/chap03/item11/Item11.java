package chap03.item11;

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
equals - hashCode 세트로 묶어서 생각하자
두 메서드 다 객체 간 비교에 사용되는데 hash 함수를 쓰는 Collection 에서는 최적화를 위해
hashCode 비교 후 같지 않다면 false 처리하고 hashCode 같을 때, equals()에 지정된 필드들로 논리적인 값 비교 수행한다

equals 는 제대로 정의했더라도 hashCode 재정의 안 했다면
hashCode 의 일반 규약을 어기게되어 HashMap, HashSet 같은 컬렉션의 원소로 사용할 때 문제를 일으킬 수 있기 때문이다
논리적으로 같은 값을 인스턴스를 꺼내려하는데 hashCode 가 달라버리면 값을 반환하지 못 하거나 엉뚱한 녀석을 꺼내오게 된다

hashCode 일반 규약, 2번 녀석이 중요
1. equals 비교 정보가 달라지지 않았다면 hashCode 는 일관적인 값을 줘야한다
2. 논리적으로 같은 객체는 같은 해시코드를 반환해야 한다

hash 를 쓰는 녀석들은 해시값이 다른 경우엔 비교도 하지 않도록 최적화되어 있기 땜시
hashCode 를 재정의하지 않은 PhoneNumber 클래스를 이용할 때 null 을 반환하게 되었다 이말임둥

hash 함수는 어떻게 만드는가
hashCode 충돌을 피해야 좋은 성능으로 hash Collection 을 사용할 수 있다
최대한 해시 충돌이 나지 않도록 고유한 값으로 만들수록 좋은데, hashCode 로 반환하는 값은 int 이기 때문에 범위가 한정적이다
자바에서는 parameter 에 31을 곱하여 해시를 만드는데 31은 소수이면서 홀수고, 그리 작지 않은 수이기 때문에 사용한다

만약 짝수를 사용하고 overflow 발생 시, 정보를 잃게 된다?! bit shift 때문에 0으로 가득차게 된다
짝수를 사용하면 해시코드를 계산할 때 곱하면서 계산해가므로 비트의 오른쪽이 0으로 가득찬 결과가 나온다
또한 31을 사용함으로써 비트 연산 최적화가 가능하다
a * 31 => (a << 5) - a

결론은 equals, hashCode 재정의에 대하여 알고는 있으되 AutoValue, @EqualsAndHashCode 쓰는게 편하다
 */