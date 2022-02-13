package chap08.item49;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * 매개변수가 유효한지 검사하라
 */
public class Item49 {

  public static void main(String[] args) {
    System.out.println(mod(BigInteger.TWO));

    System.out.println(getStrategy("panda"));

//    System.out.println(getStrategy(null));

    sort(null, 1, 2);
  }

  public static BigInteger mod(BigInteger m) {
    if (m.signum() <= 0) {
      throw new ArithmeticException("m should be positive number");
    }
    return BigInteger.TEN.mod(m);
  }

  // 자신이 패키지 통제권을 전부 가지고 있다면 null 이 아님을 단언해버릴 수도 있다
  // assert 작동 시키려면 VM options 에 -ea 줘야한다, 이거 안 주면 작동 안 함
  private static void sort(long[] a, int offset, int length) {
    assert a != null;
    assert offset >= 0 && offset < a.length;
    assert length >= 0 && length <= a.length - offset;
    System.out.println("YAHOO" + Arrays.toString(a));
  }

  // 우왕 null 값 찍힌 곳으로 안내해준다
  private static String getStrategy(String strategy) {
    Objects.requireNonNull(strategy, "Null 값 안돼!!!!");
    return strategy + " 전략";
  }
}

/*
메서드 인자를 받을 때 정상적인 값이 들어오길 바란다
미숙하거나 악의적인 사용자의 경우 내가 만든 전제조건을 지키지 않을 수 있다
이러한 상황에 특히 문제가 되는 것은 음수가 아닌 정수를 받아야 하는 경우 int, long 으로 받을 때
음수 넘겨도 컴파일 타임에 문제 되지 않는다, 음수로 들어오면 터져야 한다

Integer, Long 으로 받을 때는 정상적인 로직에서 null 값이 들어와서는 안 된다고 하면
기본 타입으로 받거나 null 체크해서 터트리는 로직을 추가해줘야한다
이 경우엔 기본 타입을 받아서 컴파일 타임에 오류 뜨게 해주는 것이 좋다
가능한 한 빠르게 터트려줘야 수정하기 쉽다
컴파일 타임에 오류 나는게 가장 좋고 그게 아니라면 예외를 집어 던져줘야 한다

- Objects.requireNonNull()
널 검사를 수동으로 하지 않아도 된다
두번째 parameter 로 메세지도 지정 가능

- assert 단언
일단 선언해버림, 실패하면 AssertionError
런타임 시 성능 저하 없음

당장 쓰지는 않고 받아서 넘기기 위한 인자는 특별히 더 신경써서 검사하라
추후 NPE 터졌을 때 디버깅이 복잡해질 수 있기 때문
메서드 & 생성자를 작성할 때 매개변수의 제약을 고려하자
자신이 직접 컨트롤할 수 있는 값이 아니라면 명시적으로 검사하는 게 좋다

이번 장의 핵심을 매개변수에 제약을 두자 라고 이해해서는 안 된다
매개변수는 최대한 범용적으로 작성해야 다양한 상황에서 재사용이 가능하다
다만 비지니스 로직에서 원하는 올바른 값이 들어왔는지 확인하는 과정이 필요하다
 */