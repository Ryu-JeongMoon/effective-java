package chap05.item31;

import java.util.List;

/**
 * 한정적 와일드카드를 사용해 API 유연성을 높이라
 */
public class Item31 {

  public static void main(String[] args) {

  }

  // 뭐가 더 나을까요~?
  // 2가 낫습니다
  // 비교를 위해 신경 써야 할 타입 매개변수가 없기 때문
  // 1. public static <E> void swap(List<E>list, int i, int j);
  // 2. public static void swap(List<?> list, int i, int j);

  public static void swap(List<?> list, int i, int j) {
    swapHelper(list, i, j);
  }

  // List<?> 로 아래 로직 수행하면 타입 에러 발생함
  // List<?> 에는 null 만 넣을 수 있기 때문, 따라서 구현 시에는 정확한 타입을 알아야 한다
  // private 메서드를 이용해 타입을 정확히 받아서 처리
  private static <E> void swapHelper(List<E> list, int i, int j) {
    list.set(i, list.set(j, list.get(i)));
  }
}

/*
Parameterized Type 은 invariant
Type1, Type2 가 있을 때 List<Type1>, List<Type2> 는 하위 타입도 상위 타입도 아니다!
List<String> 은 List<Object> 의 하위 타입이 아니다
요넘은 String 만 넣어야 되고 후자는 Object 타입 들어갈 수 있으므로 예시를 들어보면 당연한 얘기구만

알고가자 PECS 원칙
PECS : producer-extends, consumer-super

parameter 와 argument 의 차이가 무엇인가
method signature 에 받는 값을 parameter (매개변수) 라고 하고
method 를 이용해 다른 객체로 메세지 전송 시 실제로 넘기는 값을 argument (인자) 라고 한다

왜 구분하는가?
자바 명세에서 구분하고 있다
뇌피셜 추가
parameter 는 더 넓은 범위를 칭할 수 있고, argument 는 세부 타입으로 넣을 수 있다
용어 정의 부분이므로 몰라도 개발 못 하는 건 아니지만 원활한 소통을 위해 이 자식도 알고 갑시다
*/