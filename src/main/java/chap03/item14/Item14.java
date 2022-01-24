package chap03.item14;

import chap03.item10.CaseInsensitiveString;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Comparable 을 구현할지 고려하라
 */
public class Item14 {

  // 해시코드 값의 차를 기준으로 하는 비교자, 추이성을 위배한다?!?!
  // TODO, 오버플로우, 언더플로우 났을 때 다른 값 나와서 그런감? 추후 알아보자
  // 이 방식은 overflow 나타날 수 있고 부동소수점 계산 방식에 따른 오류도 나타날 수 있다, 심지어 빠르지도 않다
  static Comparator<Object> wrongHashCodeOrder = new Comparator<Object>() {
    @Override
    public int compare(Object o1, Object o2) {
      return o1.hashCode() - o2.hashCode();
    }
  };

  // hashCodeOrder 3단 변신, 인텔리센스가 친절히 안내해주는구만
  static Comparator<Object> hashCodeOrder1 = new Comparator<Object>() {
    @Override
    public int compare(Object o1, Object o2) {
      return Integer.compare(o1.hashCode(), o2.hashCode());
    }
  };

  static Comparator<Object> hashCodeOrder2 = (o1, o2) -> Integer.compare(o1.hashCode(), o2.hashCode());

  static Comparator<Object> hashCodeOrder3 = Comparator.comparingInt(Object::hashCode);

  public static void main(String[] args) {
    BigDecimal b1 = new BigDecimal("1.0");
    BigDecimal b2 = new BigDecimal("1.00");

    // equals 에서는 false 반환, compareTo 에서는 0 반환?!
    // BigDecimal 의 equals, compareTo 가 일관적이지 않게 작성되어 있기 때문
    System.out.println("b1.equals(b2) = " + b1.equals(b2));
    System.out.println("b1.equals(b2) = " + b1.compareTo(b2));

    CaseInsensitiveString yahoo = new CaseInsensitiveString("Yahoo");
    System.out.println("yahoo = " + yahoo);

    System.out.println(hashCodeOrder1.compare(5353, 43434));
    System.out.println(hashCodeOrder2.compare(5353, 44));
    System.out.println(hashCodeOrder3.compare(55, 55));
  }
}

/*
Comparable 을 구현했다는 것은 자연적인 순서 (natural order) 가 있다는 것을 의미한다
알파벳, 숫자, 연대 같이 순서가 명확한 값 클래스를 작성할 땐 반드시 Comparable 구현하도록 한다

compareTo 일반 규약 - 반사성, 대칭성, 추이성을 충족해야 한다
A, B 를 비교하여 값 return
A > B -> 양의 정수
A = B -> 0
A < B -> 음의 정수

반사성, 대칭성이란
A, B 비교 시에 (A,B) 비교와 (B,A) 비교가 다른 값이 나온다?!
그것은 말도 안 되는 것, 앞놈 뒷놈 순서 바꿔 비교해도 같은 값이 나오도록 하자
예외도 마찬가지로 앞놈 뒷놈 바꿔도 같은 예외 터져야 한다

추이성이란
A, B 가 같을 때 B, C 같다면 A, C 도 같아야 한다
흐름 따라 가자

equals 와 비슷한 면이 많다, 따라서 주의사항도 비슷하다
특정 클래스를 상속하여 확장해야할 때 그냥 equals, compareTo 조지면 큰일난다
내부 정적 클래스 / 외부 클래스 로 기존의 인스턴스와 같은 필드를 가지도록 만들어두고 요놈을 활용하자

compareTo-equals 는 가능하다면 동일한 값이 나오도록 하자
예를 들어 HashSet 을 쓸 때 BigDecimal("1.0"), BigDecimal("1.00") 은 다른 값이 나오는데
TreeSet 을 쓴다면 compareTo 로 비교하기 땜시 같은 값으로 처리한다
이런 미묘한 차이를 모두 외우고 있을게 아니라면 개념적으로 일관성 있게 만들어주어야 한다

compareTo 는 타입을 신경쓰지 않아도 된다
Comparable Interface 는 타입을 인수로 받기 땜시 컴파일 단계에서 compareTo 인수의 타입이 정해지기 때문
타입이 다른 경우, ClassCastException 던지고 대부분이 그렇게 한다

순서를 고려해야 하는 값 클래스의 경우엔 Comparable 을 구현해서 Collection 과 어우러지도록 해야한당
필드 값 비교시에 <, > 연산자 쓰지 말고 박싱 타입 기본 클래스의 정적 메서드를 이용해서 비교하자
복잡한 연산의 경우 <, > 를 작성하면서 오류가 날수 있기 때문!
내부적으로 <, >, = 연산을 내부로 감춰둔 compare 를 써서 오류를 줄이자
 */