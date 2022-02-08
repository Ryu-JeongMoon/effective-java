package chap07.item42;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 익명 클래스보다는 람다를 사용하라
 */
public class Item42 {

  public static void main(String[] args) {
    // Collections.sort 에서 mutable object 를 받아야 하는데 List.of 로 생성하면
    // immutable object 생성되므로 아래 방식으로 List<String> 생성 시 UnsupportedOperationException 발생
    // List<String> words = List.of("google", "yahoo", "naver");

    // ArrayList 로 감싸는 방식으로 생성하면 오께이
    List<String> words = new ArrayList<>(List.of("google", "yahoo", "naver"));

    // JDK 1.8 이전 방식
    Collections.sort(words, new Comparator<String>() {
      @Override
      public int compare(String o1, String o2) {
        return Integer.compare(o1.length(), o2.length());
      }
    });

    // 1차 개선
    Collections.sort(words, (o1, o2) -> Integer.compare(o1.length(), o2.length()));

    // 2차 개선
    Collections.sort(words, Comparator.comparingInt(String::length));

    // 3차 개선
    words.sort(Comparator.comparingInt(String::length));

    for (String word : words) {
      System.out.println("word = " + word);
    }

    System.out.println(AdvancedOperation.PLUS.apply(5, 3));
    System.out.println(AdvancedOperation.MINUS.apply(5, 3));
    System.out.println(AdvancedOperation.TIMES.apply(5, 3));
    System.out.println(AdvancedOperation.DIVIDE.apply(5, 3));
  }
}

/*
예전 자바에서 함수 타입을 표현할 때, 인터페이스 혹은 추상 클래스를 사용했다
JDK 1.1에서 함수 객체를 만들 때 익명 클래스로 구현했다
과거 객체 지향 디자인 패턴에는 익명 클래스면 됐다

자바의 고질적 문제인 boiler plate, 넘 길고 쓸데 없는 코드
이걸 개선하기 위해 JDK 1.8에서 lambda 도입
추상 메서드 하나짜리 인터페이스는 함수형 인터페이스라는 이름이 지어짐

람다는 이름이 없고 문서화할 수 없기에 가급적 짧은 코드로 사용 가능할 때만 유지한다 (명확성이 중요)
엄청나게 복잡한 경우가 아니라면 컴파일러가 타입 추론하기 때문에 타입 명시도 안 해줘도 된다!?
컴파일러는 제네릭을 통해 타입을 추론한다

람다가 항상 좋은 것은 아니며 결정적으로 이름이 없기 때문에 문서화하기 어렵다
또한 가독성 향상이 주된 목적인데 코드 수가 늘어날 수록 가독성이 구데기가 되기 때문에
3줄 이상인 경우에는 람다를 쓰지 않는게 나을지도

람다는 this 를 사용하는 자기 참조를 할 수 없으므로
자기 참조가 필요한 경우에는 반드시 익명 클래스를 사용하도록 한다
또한 람다를 직렬화하는 일은 삼가고 필요한 경우 중첩 클래스를 이용한다
 */