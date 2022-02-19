package chap09.item64;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 객체는 인터페이스를 사용해 참조하라
 */
public class Item64 {

  public static void main(String[] args) {
    Set<String> stringSet = new LinkedHashSet<>();
    stringSet.add("yaho");

    stringSet = new HashSet<>();
    stringSet.add("ohay");

    System.out.println("stringSet = " + stringSet);
  }
}

/*
가능하다면 모든 곳에서 구체 클래스 대신 인터페이스를 사용해 캐스팅하자
다형성의 기초가 되는 부분으로써 프로그램 전체에서 유연성이 높아진다
구현 클래스를 써야하는 상황은 생성하는 부분에서만, 구현체를 바꾸고 싶을 때 생성하는 쪽에서만 바꿔주면 된다
객체를 플러그인화하여 사용할 수 있다

요구사항이 바뀌거나 더 나은 구현체로 바꾸기 위해서는 생성 시에 클래스를 바꿔줘야 한다
이런 경우, 기존에 DI 받을 때 인터페이스로 받아뒀으면 클라이언트 코드는 하나도 바꾸지 않고
@Primary 를 사용해 우선순위를 바꿔 DI 시킬 구현체를 바꿔줄 수 있으며,
또한 직접 생성할 때 다른 구현체로 바꿔 끼워 줄 수 있다

Liskov Substitution Policy
단 기존 코드에 문제가 하나도 없으려면 기존 구현체와 같은 방식으로 동작함을 보장해야 한다
같은 인터페이스를 구현했다고 끝이 아니라 LinkedHashSet 처럼 순서가 보장된 녀석을 쓰다가
성능은 더 좋지만 순서를 보장하지 않는 녀석으로 바꾸면 문제가 생길 수 있다는 것

- 좋은 예
Set<String> stringSet = new LinkedHashSet<>();

- 나쁜 예
LinkedHashSet<String> stringLinkedHashSet = new LinkedHashSet<>();

인터페이스를 사용할 필요가 없는 경우도 있다
Integer, String 과 같은 값 클래스로써 기본적으로 final 로 선언되어 있는 애들은 마땅한 인터페이스도 없다
Number, CharSequence 있긴 하지만 String 에 기대하는 메서드가 없다
값을 나타내기 위한 클래스는 확장성을 고려할 필요가 없기 때문에 굳이 인터페이스를 사용할 이유가 없다

단 예외적으로 구현 클래스에서만 제공하는 API 가 있을 수 있다
이런 경우에는 당연하게도 인터페이스를 사용할 수 없고 구체 클래스를 직접 박아넣어 줘야한다
이 상황에서도 다형성은 여전히 유효하며 특별한 API 를 제공하는 클래스 중 가장 상위 클래스로 캐스팅해주도록 하자
 */