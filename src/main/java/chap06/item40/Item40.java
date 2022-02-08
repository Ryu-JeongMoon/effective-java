package chap06.item40;

import java.util.HashSet;
import java.util.Set;

/**
 * @Override 애노테이션을 일관되게 사용하라
 */
public class Item40 {

  public static void main(String[] args) {
    Set<Bigram> s = new HashSet<>();
    for (int i = 0; i < 10; i++) {
      for (char ch = 'a'; ch <= 'z'; ch++) {
        s.add(new Bigram(ch, ch));
      }
    }
    System.out.println("s.size() = " + s.size());
  }
}

/*
흔하게 보고 지나간 @Override 는 사실 중요한 놈이다
상위 타입의 메서드를 재정의했음을 알려주고 메서드 시그니처에 문제가 있다면 컴파일 시점에 알려주기 때문
 */