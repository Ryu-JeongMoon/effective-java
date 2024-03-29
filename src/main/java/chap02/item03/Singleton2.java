package chap02.item03;

public class Singleton2 {

  private Singleton2() {
  }

  public static Singleton2 getInstance() {
    return LazyHolder.INSTANCE;
  }

  private static class LazyHolder {

    private static final Singleton2 INSTANCE = new Singleton2();
  }
}

/*
LazyHolder

synchronized 필요 없고 Java 버전에 구애 받지 않으며 성능도 좋다!
getInstance()를 호출할 때, LazyHolder 의 초기화가 일어나며 static field 인 INSTANCE 의 초기화가 일어난다
제일 효율적인 것 같고, 멀티 스레드를 신경써야 할 때 이 방식을 사용하도록 하자

LazyHolder 는 어떻게 thread-safety 를 보장하는가
LazyHolder 는 스레드 관리를 JVM 에 넘김으로써 해결한다
초기화되면서 INSTANCE 반환하는데 JVM 은 클래스 초기화 과정에서 원자성을 보장한다
 */