package chap05.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class GenericStack<E> {

    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private E[] elements;
    private int size = 0;

    // 첫번째 해결 방법, Object 배열로 만든 후 타입을 확실히 아니까 @SuppressWarnings 써준다
    // 이놈으로 쓰면 코드가 짧아 가독성이 좋은데 단점으로는 컴파일 타입과 런타임 타입이 다르기 때문에
    // Heap Pollution 이 일어난다
    @SuppressWarnings("unchecked")
    public GenericStack() {
        // 요기서 에러 발생
        elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E e) {
        ensureCapacity();
        elements[size++] = e;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
    }

    // 뺀놈 메모리 해제해주기
    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }

        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();
        for (String arg : args) {
            stack.push(arg);
        }

        while (!stack.isEmpty()) {
            System.out.println("stack.pop().toUpperCase() = " + stack.pop().toUpperCase());
        }
    }
}

/*
E 와 같이 실체화가 불가한 녀석으로는 배열을 만들 수 없당
컴파일 타임에만 체크되고 런타임에는 소거되기 때문이지 움허허

compile & run
> javac GenericStack.java
> java GenericStack.java hello yaho

28과는 상반되지만 자바에서 기본적으로 List 를 제공하지 않는데에는 다~~ 이유가 있는 법
배열을 쓰는게 성능 목적으로 낫고, List, Map 등의 기본적인 구현을 할 때는 Object[] 을 이용한다
일반 개발자에게는 컴파일 시점에 type safe 하게 만들 수 있기 때문에 Generics 추천되는 것이고
자바 도사님들은 타입을 알아서 지지고 볶아 먹기 때문에 Object[] 써도 되는 것
 */