package chap11.item78;

import java.util.concurrent.atomic.AtomicLong;

public class NeedSynchronization {

  private static final AtomicLong nextSerialNumber = new AtomicLong();

  public static long generateSerialNumber() {
    return nextSerialNumber.getAndIncrement();
  }

  public static void main(String[] args) {
    System.out.println(generateSerialNumber());
    System.out.println(generateSerialNumber());
    System.out.println(generateSerialNumber());
    System.out.println(generateSerialNumber());
  }
}

/*
여기서 문제는 ++ 연산자
얘는 하나처럼 보이지만 사실 읽고 -> 쓰는 과정이다
따라서 두 연산 사이에 다른 스레드가 비집고 들어오면 똑같은 값 오류

java.util.concurrent 가 제공하는 Atomic 클래스들을 활용하면
lock-free thread safety 달성 가능~~!
 */