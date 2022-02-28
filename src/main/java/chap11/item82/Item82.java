package chap11.item82;

/**
 * 스레드 안전성 수준을 문서화하라
 */
public class Item82 {

  public static void main(String[] args) {

  }
}

/*
Design By Contract, DBC
한 메서드를 여러 스레드가 호출할 때 메서드의 동작 방식은 해당 클래스와 클라이언트 간 계약과 같다

Thread-safety 는 안전 / 불안전으로 나뉘는 게 아니고 안전성의 수준에 따라 나눠진다

immutable
- 해당 클래스의 인스턴스는 상수와 같아서 외부 동기화도 필요 없다
- ex) String, Long, BigInteger 등

unconditionally thread-safe
- 인스턴스 수정될 수 있으나 내부에서 충실히 동기화하므로 외부에서 신경 쓸 필요 없다
- ex) AtomicLong, ConcurrentHashMap 등

conditionally thread-safe
- unconditionally 와 같으나 일부 메서드가 외부 동기화가 필요하다
- ex) Collections.synchronized 래퍼 메서드가 반환한 컬렉션

not thread-safe
- 수정될 수 있고, 외부에서 직접 동기화 메커니즘으로 감싸야 한다
- ex) ArrayList, HashMap 기본 컬렉션

thread-hostile
- 얘네는 외부 동기화로 감싸도 안전하지 않다?!
- static data 를 동기화 없이 수정해버린다!!

자바 병렬 프로그래밍.. 언젠가 참고
 */
