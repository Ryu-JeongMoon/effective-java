package chap11.item81;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

  // 오왕 어렵당
  public static long time(Executor executor, int concurrency, Runnable action) throws InterruptedException {
    CountDownLatch ready = new CountDownLatch(concurrency);
    CountDownLatch start = new CountDownLatch(1);
    CountDownLatch done = new CountDownLatch(concurrency);

    for (int i = 0; i < concurrency; i++) {
      executor.execute(() -> {
        // 타이머에게 준비됐음을 알린다??
        ready.countDown();
        try {
          // 모든 작업자 스레드가 준비될 때까지 기다린다??
          start.await();
          action.run();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        } finally {
          // 타이머에게 작업이 끝났음을 알린다??
          done.countDown();
        }
      });
    }

    // 모든 작업자가 준비될 때까지 기다린다??
    ready.await();
    long startNanos = System.nanoTime();
    // 작업자들을 깨운다??
    start.countDown();
    // 모든 작업자가 끝날 때까지 기다린다??
    done.await();
    return System.nanoTime() - startNanos;
  }

  // concurrency 인자보다 많은 개수의 스레드를 생성할 수 있는 Executor 를 넘겨야 한다
  // 수가 적은 경우, thread starvation deadlock 걸릴 수 있기 때문
  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(16);
    long yahoTime = time(executorService, 4, () -> System.out.println("YAHO"));
    System.out.println("yahoTime = " + yahoTime);
  }
}

/*
System.currentTimeMillis 대신 System.nanoTime 사용할 것
보다 더 정확하고 시스템의 실시간 시계에 보정 받지 않는다
 */