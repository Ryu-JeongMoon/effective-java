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
    static void dangerous(List<String>... stringLists) {
        List<Integer> integers = List.of(42);
        Object[] objects = stringLists;
        objects[0] = integers;
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
varargs ??
매개변수 몇개 넘길지 그 책임을 클라이언트에게 넘긴다
가변인수를 담기 위한 배열이 자동으로 하나 만들어지는데 이 배열이 클라이언트에게 노출된다?

실체화 불가 타입 (Parameterized Type 또는 Generics) 은
런타임에 컴파일타임 보다 더 적은 양의 정보를 가질 수 밖에 없다
Generics 가 이전 코드와 호환성을 지키기 위해 소거 방식으로 작동하므로.

Exception in thread "main" java.lang.ClassCastException:
class java.lang.Integer cannot be cast to class java.lang.String
(java.lang.Integer and java.lang.String are in module java.base of loader 'bootstrap')
	at chap05.item32.Item32.dangerous(Item32.java:19)
	at chap05.item32.Item32.main(Item32.java:12)
직접 형변환을 하지 않았는데 ClassCastException 왜 터진 것인가?
컴파일러가 나 몰래 형변환 해버렸다

Generic Array 직접 만들 수는 없지만 Generic Array 를 매개변수로 받는 메서드를 선언할 수 있는 이유가 무엇인가?
실무에서 유용하기 땜시

자바 7부터 @SafeVarargs 가 추가.
@SuppressWarnings("unchecked") 노가다를 하지 않아도 된다
게다가 unchecked 로 인해 진짜 문제마저 숨겨지는 경우도 있었다

아무데나 붙일 수는 없고 type safe 함이 확실히 보장될 때만 붙여라
override 할 수 있는 메서드에는 붙일 수 없고,
static, final 메서드만 가능했으나 java 9부터 private 메서드까지 가능
 */