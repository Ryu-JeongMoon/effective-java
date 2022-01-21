package chap02.item08;

import java.lang.ref.Cleaner;

public class Room implements AutoCloseable {

  private static final Cleaner cleaner = Cleaner.create();

  private final State state;

  private final Cleaner.Cleanable cleanable;

  public Room(int numJunkPiles) {
    state = new State(numJunkPiles);
    cleanable = cleaner.register(this, state);
  }

  @Override
  public void close() {
    System.out.println("청소하겠습니당");
    cleanable.clean();
  }

  // 회수해갈 자원을 담은 클래스
  // static 으로 선언하지 않으면 자동으로 외부 객체에 대한 참조를 갖게 된다
  // 이것이 중요한 이유는 외부에 대한 참조를 갖고 있으면 GC 대상이 되지 않기 때문
  private static class State implements Runnable {

    int numJunkPiles;

    State(int numJunkPiles) {
      this.numJunkPiles = numJunkPiles;
    }

    @Override
    public void run() {
      System.out.println("청소청소");
      numJunkPiles = 0;
    }
  }
}
/*
close() -> State run() 호출

네이티브 피어란?
일반 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체를 의미한다

stack overflow 질문
A simple example would be a native window vs. a JFrame.
A JFrame is a Java peer, but it needs a (platform dependent) native peer to actually display graphics.
This is why you need to call dispose() when getting rid of a JFrame.
You need to get rid of the native component explicitly, because the GC can't touch it.

JFrame 과 같은 네이티브 피어는 명시적으로 제거를 해줘야 한다
finalizer, cleaner 는 안전망 역할을 하는 것이당
클라이언트 측에서 안 했을 경우 만약에 대비해 cleaner 를 사용해야 되는데 확실히 이루어지리라는 보장이 없다
게다가 느리고 위험하당

그럼 우짠디요?
1. 명시적으로 닫게 만들어버리자
2. try-with-resource, try-finally 를 이용해 닫아주자
 */