package chap02.item08;

/**
 * finalizer, cleaner 사용을 피하라
 */
public class Item08 {

  public static void main(String[] args) {

    // close() -> cleanable.clean() -> state.run() 호출하여 청소함
    Room room = new Room(99);
    System.out.println("YAHOO");
    room.close();
  }
}

/*
자바는 finalizer, cleaner 두 가지 객체 소멸자를 제공한다
그러나 예측할 수 없고 상황에 따라 위험할 수 있기 때문에 사용을 자제해야 한다
즉시 수행된다는 보장이 없어 제때 실행되어야 하는 작업을 맡길 수 없다

finalizer 는 수행할 스레드를 지정할 수도 없다
cleaner 는 스레드를 제어할 수 있기에 그나마 낫다

그럼에도 확실하게 실행을 보장할 수 없기 때문에 사용해서는 안 된다
파일 닫기, 인스턴스 자원 회수, 분산 락 해제 등의 작업이 밀리면 프로그램이 죽는다

그럼 이딴 쓰레기를 왜 쓰나요??
1. 자원 소유자가 close() 호출 안 할 경우를 대비한 안전망 역할 (이 역시 언제 수행될지 보장 못함)
2. 네이티브 피어의 자원 회수

자원 회수를 안 하는 것보다 늦게라도 하는 것이 낫기 때문에 안전망을 설치해두는 것인데
애초에 명시적으로 close() 호출하도록 코드 작성하면 finalizer, cleaner 쓸 일이 없다
네이티브 피어 또한 마찬가지로 close()를 통해 즉시 자원 회수할 수 있다

close() 사용을 위해서는 클래스가 AutoCloseable 을 구현해야 한다
 */