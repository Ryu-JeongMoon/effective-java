package chap02.item07;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {

  private static final int DEFAULT_INITIAL_CAPACITY = 16;
  private Object[] elements;
  private int size = 0;

  public Stack() {
    elements = new Object[DEFAULT_INITIAL_CAPACITY];
  }

  public void push(Object e) {
    ensureCapacity();
    elements[size++] = e;
  }

  public Object pop() {
    if (size == 0) {
      throw new EmptyStackException();
    }
    Object result = elements[--size];

    // 다시 안 쓸 요놈 메모리 할당을 해제해줘야 한다
    elements[size] = null;
    return result;
  }

  private void ensureCapacity() {
    if (elements.length == size) {
      elements = Arrays.copyOf(elements, 2 * size + 1);
    }
  }
}

/*
책에서는 elements[--size] 로 되어있는디
일반적으로 pop 메서드는 마지막 요소를 삭제 후 반환해줘야 하는데 그럼 elements[size--] 로 해야하지 않나?
오잉 아니네, 인덱스로 반환하고 size 줄어야 하니까 -- 먼저 때려야 하는구만

참조 해제를 안 하는 경우 최악의 상황에서는 해제되어야 할 객체가 참조하는 많은 객체들이 회수되지 않을 수 있다
프로그램이 거대해질 수록 메모리 누수를 정확히 파악하기 어렵기 때문에
메모리를 직접 다루는 클래스를 작성할 경우 테스트에 더 주의해야 하고 성능 테스트도 수행해야 한다
 */