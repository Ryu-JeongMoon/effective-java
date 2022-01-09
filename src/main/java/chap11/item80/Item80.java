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
Thread 를 개발자가 직접 사용한다면 안전 실패 & 응답 불가 여지를 없애는 코드를 추가해야 한다
java.util.concurrent 패키지는 Executor Framework 라고 하는 유연한 태스크 실행 기능을 가지고 있다

newCachedThreadPool 은 요청받은 태스크들이 큐에 쌓이지 않고 스레드에 즉시 위임되고, 가용한 스레드가 없다면 새로 생성한다
가벼운 서버에서는 유용하지만 요청이 많다면 thread 생성 비용이 어마어마해진당
 */