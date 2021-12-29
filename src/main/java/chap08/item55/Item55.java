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

자바 8 이후에 Optional<T> 선택지가 생겨남 근데 왜 조심해야 하나요??
Optional.of(null)은 NPE 터지니까 Optional.ofNullable(null)로 반환해줘야 하는데
Optional 에 null 반환은 Optional 취지에 맞지 않으니 지양하도록 하자

Optional<T>는 언제 사용해야 할까?
결과가 없을 수 있고, 클라이언트 단에서 처리하고 싶을 때
orElse 어쩌구 등의 메서드로 명시적으로 처리

단 Optional 도 새로 생성해야하는 객체이므로 성능 저하가 올 수 있다
성능 최적화 방법과 마찬가지로 실험적으로 성능을 측정하고 사용하도록 한다
성능이 매우 중요한 임베디드 시스템 같은 경우 Optional 이 해악이 될 수 있다

Optional<Long> 과 같은 녀석들은 박싱된 타입을 Optional 로 또 감싸니까 성능 저하가 심해질 수 있으니
기본 제공되는 OptionalInt, OptionalLong 으로 반환해주자

음 그동안 Optional 자주 쓰고 클라이언트 단에서 orElseThrow 써줬는데
이 방식이 가독성은 좋을지 몰라도 성능 측면에서는 좋지 않을 수도 있구만??!
추후 Jmeter, nGrinder 같은 걸로 성능 측정해보고 너무 구리면 스타일을 바꿔야겠다
 */