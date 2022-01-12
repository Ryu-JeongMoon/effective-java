package chap02.item03;

public class Singleton1 {

  private volatile static Singleton1 instance;

  private Singleton1() {
  }

  public static Singleton1 getInstance() {
    if (instance == null) {
      synchronized (Singleton1.class) {
        if (instance == null) {
          instance = new Singleton1();
        }
      }
    }

    return instance;
  }
}

/*
Double Checked Locking, DCL
broken idiom 이당

method 자체에 synchronized 가 걸려있던 것을 오버헤드를 줄이고자 메서드 내부에서 check 하는 과정에 synchronized 를 걸었다
인스턴스 생성이 끝나기 전에 메모리 할당은 가능하기 때문에 문제 발생 여지가 있다 (확률은 적으나 발생할 수도 있는 문제)

Thread-1 이 생성의 책임을 갖고 생성 중에 메모리 할당은 되었다
Thread-2 가 메모리 할당이 된 것을 보고 사용하려고 하는데 생성이 끝나지 않았다
-> 문제 발생
 */