package chap11.item81;

import java.util.concurrent.CountDownLatch;

/**
 * wait, notify 보다는 동시성 유틸리티를 애용하라
 */
public class Item81 {

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(5);
  }
}

/*
low-level 에서 동시성 처리를 하기는 아주 까다롭다
자바왕들보다 이쁘게 설계할 수 없다면 고수준 동시성 유틸리티를 사용하자

고수준 유틸리티는 Executor Framework, Concurrent Collection, Synchronizer 로 나눌 수 있다
Concurrent Collection -> List, Queue, Map 같은 표준 컬렉션 인터페이스에 동시성을 가미한 고성능 컬렉션이다

BlockingQueue 는 Queue 를 확장한 동시성 컬렉션 중 하나이고, take 메서드는 큐의 첫번째 원소를 꺼낸다
만약 없다면 원소가 추가될 때까지 기다린다
이런 특성 덕에 ThreadPoolExecutor 등 대부분의 실행자 서비스에서 구현체로 사용한다

동기화 장치는 특정 스레드가 다른 스레드를 기다릴 수 있게 한다
CountDownLatch, Semaphore, CyclicBarrier, Exchanger, Phaser 가 있다

CountDownLatch 는 일회성 장벽이다
 */