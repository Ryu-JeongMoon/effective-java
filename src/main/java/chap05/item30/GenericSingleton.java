package chap05.item30;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.UnaryOperator;

public class GenericSingleton {

  private static final UnaryOperator<Object> IDENTITY_FN = t -> t;

  public static void main(String[] args) {
    String[] strings = {"Nylon", "Cycle", "Pick"};
    UnaryOperator<String> sameString = GenericSingleton.identityFunction();
    for (String string : strings) {
      System.out.println(sameString.apply(string));
    }

    Number[] numbers = {1, 2.0, 3L};
    UnaryOperator<Number> sameNumber = GenericSingleton.identityFunction();
    for (Number number : numbers) {
      System.out.println(sameNumber.apply(number));
    }
  }

  @SuppressWarnings("unchecked")
  public static <T> UnaryOperator<T> identityFunction() {
    return (UnaryOperator<T>) IDENTITY_FN;
  }

  // ? super E, ? extends E 아리까리하네, PECS 외워라
  // 입력 매개변수로부터 E 인스턴스 생성하므로 PE
  // 매개변수화 타입은 한정적 와일드카드 타입으로 변경하는 것이 유연하므로 CS
  public static <E extends Comparable<? super E>> Optional<E> max(List<? extends E> c) {
    if (c.isEmpty()) {
      return Optional.empty();
    }

    E result = null;
    for (E e : c) {
      if (result == null || e.compareTo(result) > 0) {
        result = Objects.requireNonNull(e);
      }
    }

    return Optional.of(result);
  }
}

/*
상대적으로 드문 경우로 재귀적 타입 한정을 사용한다
Comparable<T> 가 대표적인데 Comparable 을 구현한 타입이 비교할 수 있는 타입을 정의한다

<E extends Comparable<E>> 모든 타입 E 는 자신과 비교할 수 있다는 의미
제네릭을 사용하지 않으면 일일이 형변환을 해줘야 한다!?

선생님, max 같은 개어려운 메서드를 만들 필요가 있나요
네
List<ScheduledFuture<?>> scheduledFutures = ...; 와 같은 녀석을 처리할 때 필요합니다
ScheduledFuture 는 Comparable<ScheduledFuture> 를 구현하지 않았음

관계도
ScheduledFuture extends Delayed
Delayed extends Comparable<Delayed>

위와 같으므로 ScheduledFuture 는 Delayed 인스턴스와 비교가 불가능하다
ScheduledFuture 는 Delayed 와 비교가 가능하므로 max 를 개어렵게 수정하기 전에는 에러 뿜뿜
Comparable<? super ScheduledFuture> 를 통해서 Comparable<Delayed> 이상으로 가기 위함인 것
Comparable<Delayed> 이상으로 가서 비교 수행 후 값 반환해준다

요기 좀 어렵네? ㅋ
 */