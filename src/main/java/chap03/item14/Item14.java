package chap03.item14;

import chap03.item10.CaseInsensitiveString;
import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Comparable 을 구현할지 고려하라
 */
public class Item14 {

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
A < B -> 음의 정수
A = B -> 0
A > B -> 양의 정수

compareTo 는 타입을 신경쓰지 않아도 된다
Comparable Interface 는 타입을 인수로 받기 땜시 컴파일 단계에서 compareTo 인수의 타입이 정해지기 때문
타입이 다른 경우, ClassCastException 던지고 대부분이 그렇게 한다

순서를 고려해야 하는 값 클래스의 경우엔 Comparable 을 구현해서 Collection 과 어우러지도록 해야한당
필드 값 비교시에 <, > 연산자 쓰지 말고 박싱 타입 기본 클래스의 정적 메서드를 이용해서 비교하자
복잡한 연산의 경우 <, > 를 작성하면서 오류가 날수 있기 때문!
 */