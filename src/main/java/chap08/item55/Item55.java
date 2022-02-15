package chap08.item55;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

/**
 * 옵셔널 반환은 신중히 하라
 */
public class Item55 {

  public static void main(String[] args) {

  }

  static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
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
자바 8 이전에는 반환해야 하는 값이 없을 때 or 잘못된 값일때 셋중 하나 선택
1. null 반환하거나
2. 빈 리스트 / 배열 등의 객체 반환
3. 예외 터트리기

1번 문제 -> 널 참조하는 경우 NPE
2번 문제 -> 빈 객체인지 체크해야 함
3번 문제 -> 예외 잡거나, 예외 로그 찍어야 하는데 이에 대한 비용도 존재

null, 빈 객체 반환은 체크 로직을 직접 작성해야 하는 것이 문제이고 예외 터트리는게 그나마 낫다
단 예외도 신중해야 하는 것이 회원 조회 시 회원 아이디가 없거나 잘못된 값의 경우는 터트리는게 맞는데
회원의 속성 중 없어도 되는 반환 값에 예외 터트리면 비지니스 로직이 무너지므로 이런 경우엔 사용해선 안 된다

자바 8 이후에 Optional<T> 선택지가 생겨남 근데 왜 조심해야 하나요??
Optional.of(null)은 NPE 터지니까 Optional.ofNullable(null)로 반환해줘야 하는데
Optional 에 null 반환은 Optional 취지에 맞지 않으니 지양하도록 하자
근디 생각해보면 null 일지도 모르기 때문에 Optional 사용하는 건데 null 인지 우리가 어케 암;;

Optional<T>는 언제 사용해야 할까?
Checked Exception 사용하는 취지와 비슷하다고 한다
클라이언트에 사용하려는 API 의 반환값이 없을 수도 있음을 명시적으로 전달하고 처리도 위임한다
결과가 없을 수 있고, 클라이언트 단에서 처리하고 싶을 때 orElse 어쩌구 등의 메서드로 명시적으로 처리

단 Optional 도 새로 생성해야하는 객체이므로 성능 저하가 올 수 있다
성능 최적화 방법과 마찬가지로 실험적으로 성능을 측정하고 사용하도록 한다
성능이 매우 중요한 임베디드 시스템 같은 경우 Optional 이 해악이 될 수 있다

Optional<Long> 과 같은 녀석들은 박싱된 타입을 Optional 로 또 감싸니까 성능 저하가 심해질 수 있으니
기본 제공되는 OptionalInt, OptionalLong 으로 반환해주자

음 그동안 Optional 자주 쓰고 클라이언트 단에서 orElseThrow 써줬는데
이 방식이 가독성은 좋을지 몰라도 성능 측면에서는 좋지 않을 수도 있구만??!
추후 Jmeter, nGrinder 같은 걸로 성능 측정해보고 너무 구리면 스타일을 바꿔야겠다


## Optional 은 왜 비싼가? ##
그냥 T 타입의 객체가 있는데 Optional<T> 로 옷을 하나 입힌다, 이는 layer of indirection 하나 덧씌워지는 것이다
layer of indirection 뭐여?
-> orElse, orElseGet, orElseThrow 와 같이 Optional 벗기고 값을 꺼내려면 메서드 호출이 하나 더 들어가야 한다

특히 박싱된 타입의 경우에 박싱 타입의 인스턴스를 생성하고 값을 넣으니까 단건에는 큰 성능 저하가 없을지 몰라도
사이즈가 매우 큰 연산을 수행한다면 성능이 나락 간다
위에서 언급했듯 기본형 Optional 을 사용하거나 사용하지 말아야 한다
단 이러한 경우에는 null & 빈값 체크를 엄격히 해줘야하고 가독성이 조금 떨어질 수 있다

Optional 을 Collection 키로 사용하면 API 자체에 혼란을 줄 수 있어 사용을 금한다
Optional 사용하면 키 값이 없는 경우가 2가지가 된다
1. 진짜 없는 경우
2. Optional.empty() 로 되어 있는 경우
 */