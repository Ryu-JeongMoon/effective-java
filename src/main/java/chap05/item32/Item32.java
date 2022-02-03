package chap05.item32;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 제네릭과 가변인수를 함께 쓸 때는 신중하라
 */
public class Item32 {

  public static void main(String[] args) {
    List<String> panda = List.of("panda", "bear");
    dangerous(panda);

    List<String> strings = pickTwo("Good", "Fast", "Cool");
    strings.forEach(System.out::println);
  }

  // Heap Pollution 발생
  // List<String>[] 에다가 List<Integer> 담게 되므로
  // Possible heap pollution from parameterized vararg type 뜨는데 얘를 숨겨주려면
  // 자바7 이전 @SuppressWarnings("unchecked") 또는 자바7 이후 @SafeVarargs 붙여주도록 한다
  static void dangerous(List<String>... stringLists) {
    List<Integer> integers = List.of(42);
    Object[] objects = stringLists;
    objects[0] = integers;
    // stringLists[0] 에서 Integer 가 꺼내지는데 얘를 String 으로 형변환하기 땜시 ClassCastException
    String s = stringLists[0].get(0);
  }

  static <T> List<T> pickTwo(T a, T b, T c) {
    return switch (ThreadLocalRandom.current().nextInt(3)) {
      case 0 -> List.of(a, b);
      case 1 -> List.of(a, c);
      case 2 -> List.of(b, c);
      default -> List.of();
    };
  }
}

/*
varargs, 가변인수
매개변수 몇개 넘길지 그 책임을 클라이언트에게 넘긴다
가변인수를 담기 위한 배열이 자동으로 하나 만들어지는데 이 배열이 클라이언트에게 노출된다?

Generics 가 이전 코드와 호환성을 지키기 위해 소거 방식으로 작동하기 때문에
실체화 불가 타입 (Parameterized Type 또는 Generics) 은 런타임에 컴파일타임 보다 더 적은 양의 정보를 가질 수 밖에 없다

varargs 에다가 Generics or Parameterized Type 포함시키면 실체화 가능한 배열에다가
실체화 불가 타입을 선언했기 때문에 오묘한 컴파일 에러를 뿜어낸다 런타임 시에 배열의 타입을 모르기 때문이다
Item32.java:13: warning: [unchecked] unchecked generic array creation for varargs parameter of type List<String>[]
    dangerous(panda);
             ^
Item32.java:20: warning: [unchecked] Possible heap pollution from parameterized vararg type List<String>
  static void dangerous(List<String>... stringLists) {


직접 형변환을 하지 않았는데 ClassCastException 왜 터진 것인가?
컴파일러가 나 몰래 형변환 해버렸다
Exception in thread "main" java.lang.ClassCastException:
class java.lang.Integer cannot be cast to class java.lang.String
(java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
	at chap05.item32.Item32.dangerous(Item32.java:19)
	at chap05.item32.Item32.main(Item32.java:12)

Generic Array 직접 만들 수는 없지만 Generic Array 를 매개변수로 받는 메서드를 선언할 수 있는 이유가 무엇인가?
실무에서 유용하기 땜시

자바 7부터 @SafeVarargs 가 추가.
@SuppressWarnings("unchecked") 노가다를 하지 않아도 된다
게다가 unchecked 로 인해 진짜 문제마저 숨겨지는 경우도 있었다

@SafeVarargs 는 어느 상황에 사용할 수 있는가
타입 안전이 보장될 때는 언제일까? 제네릭 배열이 만들어지고 배열에 무언가 추가, 변경하지 않을 때
클라이언트에 노출되지 않아 변경이 불가할 때 타입 안전성이 보장되고 적용할 수 있다

아무데나 붙일 수는 없고 type safe 함이 확실히 보장될 때만 붙여라
override 할 수 있는 메서드에는 붙일 수 없고,
static, final 메서드만 가능했으나 java 9부터 private 메서드까지 가능
 */