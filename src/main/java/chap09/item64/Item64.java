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

- 좋은 예
Set<String> stringSet = new LinkedHashSet<>();

- 나쁜 예
LinkedHashSet<String> stringLinkedHashSet = new LinkedHashSet<>();

단 예외적으로 구현 클래스에서만 제공하는 API 가 있을 수 있다
이런 경우에는 당연하게도 인터페이스를 사용할 수 없고 구체 클래스를 직접 박아넣어 줘야한다
이 상황에서도 다형성은 여전히 유효하며 특별한 API 를 제공하는 클래스 중 가장 상위 클래스로 캐스팅해주도록 하자
 */