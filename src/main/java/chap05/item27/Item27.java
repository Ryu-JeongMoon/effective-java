package chap05.item27;

import java.util.HashSet;
import java.util.Set;

/**
 * 비검사 경고를 제거하라
 */
public class Item27 {

  public static void main(String[] args) {
    // 비검사 경고 버전
    // Set<String> stringSet = new HashSet();

    Set<String> stringSet = new HashSet<>();
    stringSet.add("yahoo");

    for (String s : stringSet) {
      System.out.println("s = " + s);
    }
  }
}

/*
Terminal
 javac -Xlint:unchecked src/main/java/chap05/item27/Item27.java 입력하여 컴파일
 java src/main/java/chap05/item27/Item27.java 입력하여 실행

책에서는 -Xlint:uncheck 라고 알려준다 옛날 버전인감?
javac <option> <source> 형태로 입력해야 컴파일된당

decompiled .class file 첨 봤다 신기하다 요놈
Generics 사라지고 for 문이 while, iterator 조합으로 바뀌넹

<> 연산자를 통해 간단하게 비검사 경고 제거!
경고를 제거할 수 없지만 안전하다고 확신할 때
@SuppressWarnings("unchecked") 를 사용할 수 있다고는 하는데 굳이굳이?!
진짜 중요한 에러가 비검사 경고에 의해 가려질 수 있으므로 사용한다고 함
자바 라이브러리에서의 문제가 아니라면 직접 제거해주자

비검사 경고는 런타임 시 ClassCastException 터질 수 있음을 알려준다
@SuppressWarnings 를 남발하여 스스로 타입 안전성을 개박살 내지말자
단 타입 안전성을 보장할 수 있고 @SuppressWarnings 에 의해 더 중요한 에러를 나타낼 수 있을 때만 사용하자
 */