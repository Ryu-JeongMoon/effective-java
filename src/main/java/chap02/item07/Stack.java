package chap02.item07;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * 다 쓴 객체 참조를 해제하라
 */
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
메모리 관리를 직접 하는 클래스에서는 메모리 누수에 신경써야 한다
 */