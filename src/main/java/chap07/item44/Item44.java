package chap07.item44;

import java.util.Arrays;
import java.util.function.LongFunction;
import java.util.function.LongToIntFunction;

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
  }
}

/*
필요한게 없을 때, 기본 함수형 인터페이스에 박싱된 타입을 넣어 사용하면 성능 처참해진다

구조적으로 같더라도 새로이 작성해야 하는 경우가 있다
Comparator<T> 가 그것인데 아래와 같은 경우에는 직접 작성을 고민하도록 한다
1. 이름 자체가 용도를 명확히 설명하고
2. 반드시 따라야 하는 규약이 있으며
3. 유용한 디폴트 메서드를 제공할 때

자바가 람다를 지원하면서 입력값과 반환값에 함수형 인터페이스 타입을 활용할 수 있게 됐다
웬만하면 표준 함수형 인터페이스를 사용하고 Comparator<T> 와 비교하여 새로 작성할 이유가 충분할 때
직접 작성하여 사용하자
 */