package chap11.item81;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

/**
 * wait, notify 보다는 동시성 유틸리티를 애용하라
 */
public class Item81 {

  private static final ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();

  public static void main(String[] args) throws InterruptedException {
    CountDownLatch countDownLatch = new CountDownLatch(5);
  }

  // 최적화 수행 전
  public static String intern(String s) {
    String previousValue = concurrentMap.putIfAbsent(s, s);
    return previousValue == null ? s : previousValue;
  }

  // ConcurrentHashMap 은 검색 기능에 최적화되어 있으므로 검색 후 필요할 때만 putIfAbsent 수행하도록 최적화
  public static String internWithOptimization(String s) {
    String result = concurrentMap.get(s);
    if (result == null) {
      result = concurrentMap.putIfAbsent(s, s);
      if (result == null) {
        return s;
      }
    }
    return result;
  }
}

/*
low-level 에서 동시성 처리를 하기는 아주 까다롭다
예전에는 wait / notify 를 이용해 동시성을 처리했는데 java.util.concurrent 가 대신 다 처리해준다
자바왕들보다 이쁘게 설계할 수 없다면 고수준 동시성 유틸리티를 사용하자

java.util.concurrent 고수준 유틸리티는 세가지 범주로 나눌 수 있다
1. Executor Framework
2. Concurrent Collection
3. Synchronizer

Executor Framework -> 작업 단위와 실행을 분리하고 thread-safety, deadlock 회피 등 번잡한 작업을 프레임워크 내부에서 처리한다
개발자가 직접 스레드를 다루는 일을 피하게 해준다

Concurrent Collection -> List, Queue, Map 같은 표준 컬렉션 인터페이스에 동시성을 가미한 고성능 컬렉션이다
얘네는 내부에서 동시성을 처리하므로 외부에서 동시성을 무력화하려는 시도는 무의미하고 할 필요도 없다

Synchronizer -> 특정 스레드가 다른 스레드를 기다릴 수 있게 한다
CountDownLatch, Semaphore, CyclicBarrier, Exchanger, Phaser 가 있다
이 중에서 CountDownLatch, Semaphore 가 자주 쓰인다, CountDownLatch 는 일회성 장벽이다

동시성 컬렉션 ?!
BlockingQueue 는 Queue 를 확장한 동시성 컬렉션 중 하나이고, take 메서드는 큐의 첫번째 원소를 꺼낸다
만약 없다면 원소가 추가될 때까지 기다린다
이런 특성 덕에 ThreadPoolExecutor 등 대부분의 실행자 서비스에서 구현체로 사용한다
 */