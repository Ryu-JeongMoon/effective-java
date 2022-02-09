package chap07.item44;

import java.util.Arrays;
import java.util.function.IntUnaryOperator;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * 표준 함수형 인터페이스를 사용하라
 */
public class Item44 {

  public static void main(String[] args) {
    LongFunction<int[]> longFunction = (l1) -> {
      int[] ints = new int[1];
      long l3 = l1 + 55;
      ints[0] = (int) l3;
      return ints;
    };
    System.out.println(Arrays.toString(longFunction.apply(55L)));

    LongToIntFunction longToIntFunction = l1 -> (int) (l1 + 1);
    System.out.println(longToIntFunction.applyAsInt(1L));

    Supplier<String> stringSupplier = () -> "yaho";
    System.out.println("stringSupplier.get() = " + stringSupplier.get());

    // 기본형 존재 한다면 박싱 타입 노노
    UnaryOperator<Integer> integerUnaryOperator = a -> a + 5;
    System.out.println("integerUnaryOperator.apply(5) = " + integerUnaryOperator.apply(5));

    IntUnaryOperator intUnaryOperator = a -> a + 5;
    System.out.println("integerUnaryOperator.apply(5) = " + intUnaryOperator.applyAsInt(5));
  }
}

/*
람다를 지원하게 되면서 상위 클래스의 메서드를 오버라이딩해 동작을 변경하는 template method pattern 위상이 줄었다
메서드 인자 받을 때 람다를 이용해 동작 자체를 받아버리면 되기 때문
필요한게 없을 때, 기본 함수형 인터페이스에 박싱된 타입을 넣어 사용하면 성능 처참해진다

구조적으로 같더라도 새로이 작성해야 하는 경우가 있다
Comparator<T> 가 그것인데 아래와 같은 경우에는 직접 작성을 고민하도록 한다
1. 이름 자체가 용도를 명확히 설명하고
2. 반드시 따라야 하는 규약이 있으며
3. 유용한 디폴트 메서드를 제공할 때

ToIntBiFunction<T, U> 와 구조적으로 같은데 왜 따로 작성해야 했을까?
1. 다양한 API 에서 사용되는데 Comparator 라는 이름이 직관적으로 무엇을 하는지 잘 알려준다
2. 반드시 따라야 하는 규약이 있다
3. 비교자들을 변환하고 조합해주는 유용한 메서드들이 많다

자바가 람다를 지원하면서 입력값과 반환값에 함수형 인터페이스 타입을 활용할 수 있게 됐다
웬만하면 표준 함수형 인터페이스를 사용하고 Comparator<T> 와 비교하여 새로 작성할 이유가 충분할 때 직접 작성하여 사용하자

직접 작성할 때는 관례를 야무지게 따르자
@FunctionalInterface, @Override 는 그냥 붙이는 것이 아니다
어떤 의미를 갖는지 명시하고 컴파일 타임에 제약을 준수하도록 강제해준다
 */