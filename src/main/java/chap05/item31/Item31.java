package chap05.item31;

import java.util.Collection;
import java.util.List;
import java.util.Stack;

/**
 * 한정적 와일드카드를 사용해 API 유연성을 높이라
 */
public class Item31 {

  public static void main(String[] args) {
    MyStack<String> myStack = new MyStack<>();
    myStack.add("Hello");
    myStack.add("World");

    myStack.popAll(myStack);
  }

  // List<?> 로 아래 로직 수행하면 타입 에러 발생함
  // List<?> 에는 null 만 넣을 수 있기 때문, 따라서 구현 시에는 정확한 타입을 알아야 한다
  public static void swap(List<?> list, int i, int j) {
    swapHelper(list, i, j);
  }

  // private 메서드를 이용해 타입을 정확히 받아서 처리
  private static <E> void swapHelper(List<E> list, int i, int j) {
    list.set(i, list.set(j, list.get(i)));
  }

  static class MyStack<E> extends Stack<E> {

    private void popAll(Collection<? super E> dst) {
      dst.addAll(this);

      for (Object o : dst) {
        System.out.println("o = " + o);
      }
    }
  }
}

/*
뭐가 더 나을까요~?
2가 낫습니다 비교를 위해 신경 써야 할 타입 매개변수가 없기 때문
1. public static <E> void swap(List<E> list, int i, int j);
2. public static void swap(List<?> list, int i, int j);

2번에서 문제가 발생하는 이유가 무엇일까?
비한정적 와일드카드 <?> 는 어떤 타입이 들어올지 모르게 된다
Generics 는 소거 방식으로 운용되기 때문에 <?> 는 아무 타입도 의미하지 않게 되고
런타임 시 null 값을 넣거나 아예 넣을 수 없다

Parameterized Type 은 invariant
Type1, Type2 가 있을 때 List<Type1>, List<Type2> 는 하위 타입도 상위 타입도 아니다!
List<String> 은 List<Object> 의 하위 타입이 아니다
요넘은 String 만 넣어야 되고 후자는 Object 타입 들어갈 수 있으므로 예시를 들어보면 당연한 얘기구만

단 요런 경우가 있다 Stack<Number> 에다가 원소로 Integer 타입을 넣고 싶을 때
원칙을 따져보면 불공변이라 Stack<Number>, Stack<Integer> 는 다른 타입이라 넣을 수 없다
개념적으로는 Integer extends Number 이기도 하고 같은 숫자니까 넣고 싶어잉
이 때 API 에 유연함을 추가해주는 것이 한정적 와일드카드다 이말이야

알고가자 PECS 원칙
PECS : producer-extends, consumer-super

생산자와 소비자란 무엇인가
parameter 받아서 어떤 처리를 하는가에 따라 나눠진다
받아서 처리 후 반환하면 생산자 (ex. 생성자, static factory method 등)
받아서 처리 후 다른 값을 반환하면 소비자 (ex. Comparable, Comparator 등)

소비자에서 super 를 사용하는 이유가 무엇인가
Comparable<? super E> 을 가장 흔하게 사용할텐데 이 것이 의미하는 바는 E 로 주어진 타입과 그 상위 타입 간 비교가 가능하다는 것
ScheduledFuture 와 같이 자체 Comparable 을 구현하지 않은 경우
상위 인터페이스 Delayed 의 Comparable<Delayed> 를 이용하기 위해 Comparable<? super E> 를 사용한다
이 때 <? extends E>를 사용할 수 없는 이유는 Comparable 의 비교 시 하위 타입과 비교하게 되는데
equals / hashCode 오버라이드하여 비교 요소를 추가하는 경우가 생기면 이 때는 Comparable 의 올바른 비교가 불가능해지기 때문이다

parameter 와 argument 의 차이가 무엇인가
method signature 에 받는 값을 parameter (매개변수) 라고 하고
method 를 이용해 다른 객체로 메세지 전송 시 실제로 넘기는 값을 argument (인자) 라고 한다

왜 구분하는가?
자바 명세에서 구분하고 있다
parameter 는 더 넓은 범위를 칭할 수 있고, argument 는 세부 타입으로 넣을 수 있다
용어 정의 부분이므로 몰라도 개발 못 하는 건 아니지만 원활한 소통을 위해 이 자식도 알고 갑시다
*/