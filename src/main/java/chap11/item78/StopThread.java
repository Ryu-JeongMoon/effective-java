package chap11.item78;

import java.util.concurrent.TimeUnit;

public class StopThread {

  private static boolean stopRequested;

  private static synchronized boolean stopRequested() {
    return stopRequested;
  }

  public static void main(String[] args) throws InterruptedException {
    Thread backgroundThread = new Thread(() -> {
      int i = 0;

//      while (!stopRequested)
      while (!stopRequested())
        i++;
    });

    backgroundThread.start();
    TimeUnit.SECONDS.sleep(1);
    stopRequested = true;
  }
}

/*
1초 재우고 stopRequested 바꿔주더라도 Thread 코드 블록에서 stopRequested 를 볼 수 없다?!
 */