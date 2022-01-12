package chap08.item49;

import java.math.BigInteger;

/**
 * 매개변수가 유효한지 검사하라
 */
public class Item49 {

  public static void main(String[] args) {
    System.out.println(mod(BigInteger.TWO));
  }

  public static BigInteger mod(BigInteger m) {
    if (m.signum() <= 0) {
      throw new ArithmeticException("m should be positive number");
    }
    return BigInteger.TEN.mod(m);
  }

  private static void sort(long[] a, int offset, int length) {
    assert a != null;
    assert offset >= 0 && offset < a.length;
    assert length >= 0 && length <= a.length - offset;
  }
}

/*
Objects.requireNonNull() 로 널 검사를 수동으로 하지 않아도 된다
두번째 parameter 로 메세지도 지정 가능

asset 단언
일단 선언해버림, 실패하면 AssertionError
런타임 시 성능 저하 없음

당장 쓰지는 않고 받아서 넘기기 위한 인자는 특별히 더 신경써서 검사하라
추후 NPE 터졌을 때 디버깅이 복잡해질 수 있기 때문

메서드 & 생성자를 작성할 때 매개변수의 제약을 고려하자
자신이 직접 컨트롤할 수 있는 값이 아니라면 명시적으로 검사하는 게 좋다
 */