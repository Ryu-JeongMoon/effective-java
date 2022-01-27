package chap04.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

/**
 * 추상 클래스보다는 인터페이스를 우선하라
 */
public class Item20 {

  public static void main(String[] args) {
    int[] intArray = {5, 1, 534, 4};
    List<Integer> integerList = intArrayAsList(intArray);
    System.out.println("integerList = " + integerList);

    ConcreteMapEntry<String, Integer> pandaMapEntry = new ConcreteMapEntry<>("panda", 15);
    System.out.println("pandaMapEntry.getKey() = " + pandaMapEntry.getKey());
    System.out.println("pandaMapEntry.getValue() = " + pandaMapEntry.getValue());
  }

  static List<Integer> intArrayAsList(int[] a) {
    Objects.requireNonNull(a);

    return new AbstractList<>() {
      @Override
      public Integer get(int index) {
        return a[index];
      }

      @Override
      public Integer set(int index, Integer element) {
        int oldVal = a[index];
        a[index] = element;
        return oldVal;
      }

      @Override
      public int size() {
        return a.length;
      }
    };
  }
}

/*
Java8부터 인터페이스에 default method 추가가 가능하므로 기존 클래스에도 손 쉽게 인터페이스를 구현해 넣을 수 있고
표준 라이브러리의 많은 클래스들이 Comparable, Iterable, AutoCloseable 등을 구현해서 재등장했당

인터페이스는 mixin 정의에 안성맞춤이다?!
대상 타입의 주된 기능에 선택적 기능을 혼합한다고 해서 믹스인 (mixed in)이라 부른다
Comparable 은 자신을 구현한 클래스의 인스턴스끼리 순서를 정할 수 있다고 선언하는 믹스인 인터페이스다

T 타입의 클래스에 Comparable<T> 구현해놓으면 T 인스턴스끼리 비교 가능하다고 알려주는 것
T 타입의 클래스에 비교 기능을 혼합한다고 해서 믹스인

복잡한 인터페이스라면 다른 이의 수고를 덜어줄 골격 구현을 제공하도록 한다
확장성을 위해서 인터페이스의 default method 를 최대한 활용하여 인터페이스 타입 하위의 여러 곳에서 쓰일 수 있도록 한다
 */