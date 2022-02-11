package chap02.item07;

import java.util.Stack;

/**
 * 다 쓴 객체 참조를 해제하라
 */
public class Item07 {

  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);

    System.out.println(stack.pop());

    chap02.item07.Stack customStack = new chap02.item07.Stack();
    customStack.push(1);
    customStack.push(2);

    System.out.println(customStack.pop());
  }
}

/*
JVM 이 해주는 GC 만 믿고 까불면 안 된다
자바를 사용하더라도 메모리 관리를 직접 하는 클래스에서는 메모리 누수에 신경써야 한다
 */