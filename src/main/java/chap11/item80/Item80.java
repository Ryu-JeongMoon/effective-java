package chap11.item80;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 스레드보다는 실행자, 태스크, 스트림을 애용하라
 */
public class Item80 {

  public static void main(String[] args) {
    ExecutorService exec = Executors.newSingleThreadExecutor();
    exec.execute(() -> {
      for (int i = 0; i < 1000000; i++) {
        i++;
      }
      System.out.println("yaho~! : " + Thread.currentThread().getName());
    });
    exec.shutdown();

    ExecutorService cachedExec = Executors.newCachedThreadPool();
    cachedExec.execute(() -> {
      for (int i = 0; i < 1000000; i++) {
        i++;
      }
      System.out.println("yaho~!! : " + Thread.currentThread().getName());
    });
    cachedExec.shutdown();

    System.out.println("yaho~!!! : " + Thread.currentThread().getName());
  }
}

/*
초판에서 작업 큐를 만들고 thread-safety, deadlock 피하기 위한 코드까지 추가해 보여줬다는데 그 양이 어마어마했나봄
java.util.concurrent 가 이런 부가적인 코드를 해결해줬다?!
ExecutorService exec = Executors.newSingleThreadExecutor(); 만 하면 끝~~!
exec.shutdown(); 요건 우아한 종료 (graceful shutdown)

Thread 를 개발자가 직접 사용한다면 안전 실패 & 응답 불가 여지를 없애는 코드를 추가해야 한다
java.util.concurrent 패키지는 Executor Framework 라고 하는 유연한 태스크 실행 기능을 가지고 있다

newCachedThreadPool 은 요청받은 태스크들이 큐에 쌓이지 않고 스레드에 즉시 위임되고, 가용한 스레드가 없다면 새로 생성한다
가벼운 서버에서는 유용하지만 요청이 많다면 thread 생성 비용이 어마어마해진당

Executor Framework 보다 잘 만들 자신이 없다면 스레드를 직접 다루는 일은 피해야 한다
실행자 프레임워크에서는 작업 단위(Task) 와 실행 메커니즘이 분리되어있다
Task 는 Runnable, Callable 로 나뉘어지며 Callable 은 응답값을 받을 수 있고 임의의 예외도 던질 수 있다
근디 이것도 그리 가벼운 작업은 아니라 Java23 부터 project loom 이 만든 가상 스레드를 사용할 수 있을거 같은데 과연~~?
 */