package chap11.item78;

/**
 * 공유 중인 가변 데이터는 동기화해 사용하라
 */
public class Item78 {

  public static void main(String[] args) {

  }
}

/*
Thread-Safety 달성을 위한 방법
1. synchronized
synchronized 키워드는 해당 메서드나 블록을 한번에 한 스레드씩 수행하도록 보장

2. volatile
volatile 키워드는 값을 읽어올 때 CPU memory 가 아닌 Main memory 에서 읽어오도록 함
가시성 문제는 해결할 수 있으나 race condition 은 해결하지 못 한다
따라서 하나의 스레드에서 쓰고, 다른 쓰레드들에서는 읽는 상황에서는 문제 없으나
여러 쓰레드에서 서로 쓰려고 할 때는 문제가 발생한다
실제 환경에서는 서로 쓸라고 할테니까 이 역시 완전한 해법이 될 수 없다

3. Atomic 연산
CAS, compare-and-swap 기반으로 동작한다
do-while 을 이용하며 값을 읽고 비교 후 연산 수행

int current;
do {
  // 읽고
  current = get();

  // 여기서 비교 후 연산 수행
} while(!compareAndSet(current, current + 1));


동기화가 없다면 다른 스레드가 만든 변화를 볼 수 없다
A 스레드에서 종료 플래그를 설정하더라도 동기화 없이는 B 스레드는 계속 돈다 -> StopThread 에서 확인
동기화는 배타적 실행 이 외에도 스레드 사이의 안정적인 통신에 필수적
 */