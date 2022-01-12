package chap05.item33;

/**
 * 타입 안전 이종 컨테이너를 고려하라
 */
public class Item33 {

  public static void main(String[] args) {
    System.out.println(String.class);
  }
}

/*
class 리터럴의 타입은 Class 가 아닌 Class<T> 다.
String.class 의 타입이 Class<String> ?

33 어렵당
 */