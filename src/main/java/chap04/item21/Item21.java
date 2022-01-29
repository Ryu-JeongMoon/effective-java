package chap04.item21;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * 인터페이스는 구현하는 쪽을 생각해 설계하라
 */
public class Item21 {

  public static void main(String[] args) {
    RemoveIfExampleList<String> exampleList = new RemoveIfExampleList<>();
    exampleList.add("panda");
    exampleList.add("bear");

    exampleList.removeIf(s -> s.startsWith("p"));

    System.out.println("exampleList = " + exampleList);
  }

  // removeIf 구현 예시
  static class RemoveIfExampleList<E> extends ArrayList<E> {

    public boolean removeIf(Predicate<? super E> filter) {
      Objects.requireNonNull(filter);
      boolean result = false;
      for (Iterator<E> it = iterator(); it.hasNext(); ) {
        if (filter.test(it.next())) {
          it.remove();
          result = true;
        }
      }
      return result;
    }
  }
}

/*
자바7 이전 세상에서는 인터페이스는 완성된 시점을 기준으로 절대 바뀌지 않을 거라는 가정 하에 만들어졌다
자바8 이후부터 default 메서드를 넣을 수 있는데 이 메서드가 인터페이스를 구현한 기존의 클래스에서
문제 하나 없이 매끄럽게 작동하리라는 보장은 없다
혹시라도 메서드 이름이 같다면 @Override 없이 메서드를 재정의한 꼴이 되고
인터페이스의 기본 구현 대신 재정의한 메서드를 사용하기 때문

SynchronizedCollection, 클라이언트가 제공한 객체로 락을 거는 기능 추가 제공하는 컬렉션
새롭게 추가된 removeIf 를 구현하지 않아 기본 구현을 물려 받게 되었고, 이를 사용하면 ConcurrentModificationException 발생
SynchronizedCollection 가 원래 제공하려던 목적에 맞지 않게 된다

인터페이스 설계는 신중히 ..
default method 는 만능이 아니다!
 */