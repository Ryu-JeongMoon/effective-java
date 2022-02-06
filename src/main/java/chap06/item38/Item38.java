package chap06.item38;

/**
 * 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
 */
public class Item38 {

  public static void main(String[] args) {
    double x = 3.3, y = 4.4;
    test(BasicOperation.class, x, y);
    test(ExtendedOperation.class, x, y);
  }

  private static <T extends Enum<T> & Operation> void test(Class<T> enumType, double x, double y) {
    System.out.printf("%s%n", enumType);
    for (T operation : enumType.getEnumConstants()) {
      System.out.printf("%f %s %f = %f%n", x, operation, y, operation.apply(x, y));
    }
  }
}

/*
일반적으로 열거 타입을 확장한다는 아이디어는 좋지 않다
기본으로 제공할 연산을 interface 에 선언하고 이를 구현한 형태로 클래스를 만들어 field 를 만들어 사용한다
이렇게 하면 열거 타입 여러 개를 하나의 타입으로 묶어 사용할 수 있다

Enum 을 사용할 때 한가지 불편한 점은 상속할 수 없다는 것인데
같은 인터페이스로 묶고 default 구현을 주거나 helper class 를 만들어 사용해 해결하면 오께이

Enum 순회하기 위한 메서드에서는 타입을 Enum 과 직접 정의한 Interface 의 하위 타입으로 한정시켜야 한다
Enum 타입이어야 순회할 수 있고, 직접 정의한 타입이어야 operation 의 메서드가 사용될 수 있기 때문
 */