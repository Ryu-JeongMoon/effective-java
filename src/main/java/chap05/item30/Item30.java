package chap05.item30;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 이왕이면 제네릭 메서드로 만들라
 */
public class Item30 {

  public static void main(String[] args) {
    Set<String> guys = Set.of("Tom", "Jerry", "Harry");
    Set<String> stooges = Set.of("Facebook", "Google", "Curly");
    Set<String> aflCio = union(guys, stooges);
    System.out.println("aflCio = " + aflCio);

    // 오오 신기방기
    Set<Integer> integers = Set.of(1, 3, 5);
    Set<Double> doubles = Set.of(2.0, 4.0, 6.0);
    Set<Number> numbers = union(integers, doubles);
    System.out.println("numbers = " + numbers);

    // 갓땜
    Set<String> strings = Set.of("1", "2", "3");
    Set<Integer> integers1 = Set.of(1, 2, 3);
    Set<? extends Serializable> union = union(strings, integers1);
    System.out.println("union = " + union);
  }

  // 문제 있는 자식
  public static Set unionRawType(Set s1, Set s2) {
    Set result = new HashSet(s1);
    result.addAll(s2);
    return result;
  }

  // 문제 없는 자식
  public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
    HashSet<E> result = new HashSet<>(s1);
    result.addAll(s2);
    return result;
  }
}

/*
 javac -Xlint:unchecked Item30.java
Item30.java:16: warning: [unchecked] unchecked call to HashSet(Collection<? extends E>) as a member of the raw type HashSet
        Set result = new HashSet(s1);
                     ^
  where E is a type-variable:
    E extends Object declared in class HashSet
Item30.java:17: warning: [unchecked] unchecked call to addAll(Collection<? extends E>) as a member of the raw type Set
        result.addAll(s2);
                     ^
  where E is a type-variable:
    E extends Object declared in interface Set

raw type 쓰면 에러 뿜뿜
unionRawType 주석 처리하면 에러 발생 X

public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
요놈에서의 타입 추론은 자바 8부터 잘 작동한다
자바 7 이전에서는 타입 추론 능력이 충분하지 않아 추론 못 함, 바보자식
 */