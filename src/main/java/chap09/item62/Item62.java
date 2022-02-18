package chap09.item62;

/**
 * 다른 타입이 적절하다면 문자열 사용을 피하라
 */
public class Item62 {

  public static void main(String[] args) {
    java.lang.ThreadLocal<String> threadLocal = new java.lang.ThreadLocal<>();
    threadLocal.set("panda");
    String s = threadLocal.get();
    System.out.println("s = " + s);

    java.lang.ThreadLocal<Integer> integerThreadLocal = new java.lang.ThreadLocal<>();
    integerThreadLocal.set(50505);
    Integer integer = integerThreadLocal.get();
    System.out.println("integer = " + integer);
  }
}

/*
다루려는 데이터의 적합한 데이터 타입을 이용하라, 없다면 만들어라
직접 작성할 때, 다른 곳에서 참조되지 않는다면 private static class 로 선언해서 만든다
숫자, 문자, 숫자 데이터가 필요할 때 얘네를 합쳐서 1panda2처럼 사용하지 말고 아래와 같이 작성하도록 한다
private static class {
  숫자
  숫자
  문자
}

오홍 ThreadLocal 은 hashCode 로 서로를 구분하는데
내부 구현은 AtomicInteger 연산으로 (HASH_INCREMENT = 0x61c88647) 만큼 늘려나간다
이래서 멀티 스레드 상황에서도 안전할 수 있고만

String 을 그냥 쓰는 것은 문자열 파싱 때문에 느리고 어떤 오류가 터질지 모른다
 */