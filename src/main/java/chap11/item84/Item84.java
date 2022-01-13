package chap11.item84;

/**
 * 프로그램의 동작을 스레드 스케줄러에 기대지 말라
 */
public class Item84 {

  public static void main(String[] args) {
    SlowCountDownLatch slowCountDownLatch = new SlowCountDownLatch(5);
    slowCountDownLatch.countDown();
    slowCountDownLatch.await();
  }
}

/*
여러 스레드가 실행 중일 때 운영체제의 스레드 스케줄러가 스레드 실행 시간을 결정한다
정상적인 운영체제라면 문제 없지만 운영체제 별로 스레드 스케줄링이 다르기 때문에 운영체제에 기대서는 안 된다
간단하고 빠른 프로그램을 위해서는 실행 가능한 스레드의 평균을 프로세서 수보다 지나치게 많지 않도록 조절해야 한다

스레드는 당장 실행할 작업이 없다면 실행되서는 안 된다
작업이 없더라도 실행되는 경우를 busy waiting 이라 한다

운영체제에 의존하지 않아야 하는 이유와 마찬가지로 Thread.yield() 에도 의존해서는 안 된다
 */