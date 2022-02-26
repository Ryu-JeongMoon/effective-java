package chap11.item78;

import java.util.concurrent.TimeUnit;

public class AdvancedStopThread {

  private static volatile boolean stopRequested;

  private static synchronized void requestStop() {
    stopRequested = !stopRequested;
  }

  private static synchronized boolean isStopRequested() {
    return stopRequested;
  }

  public static void main(String[] args) throws InterruptedException {
    Thread backgroundThread = new Thread(() -> {
      int i = 0;
      while (!isStopRequested()) {
//      while (!stopRequested) {
        i++;
      }
      System.out.println("i = " + i);
    });
    backgroundThread.start();

    TimeUnit.SECONDS.sleep(1);
//    requestStop();

    stopRequested = true;
  }
}

/*
Thread.stop 은 안전하지 않아 오래전부터 deprecated
올바른 방법은 flag 필드를 두고 한 스레드는 계속해서 풀링으로 값 확인 후 작업 수행
다른 스레드에서 flag 필드 변화시켜 작업 멈추도록 만듬
위의 예시는 동기화 되지 않을 시 올바른 방법이 수행되지 않음을 보여준다

requestStop / isStopRequested 모두에 synchronized 걸어야 한다
쓰기와 읽기 모두가 동기화되어야 올바른 동작을 보장한다
requestStop 에는 안 걸어도 멈추던데?!

되는 것 같아 보이지만 이 메서드가 단순해서 그렇슴
배타적 수행 & 스레드 간 통신 중, 여기서는 통신 목적으로 사용했기 때문임둥

volatile 키워드를 사용해 가장 최근에 기록된 값을 매번 읽어오게 한다
오우 얘가 synchronized 보다 훨씬 빠르넹, 단 얘는 주의해서 사용해야 함, race condition 발생하기 때문
 */