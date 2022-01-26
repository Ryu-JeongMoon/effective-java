package chap04.item17;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * 변경 가능성을 최소화하라
 */
public class Item17 {

  public static void main(String[] args) {
    Complex c1 = Complex.valueOf(5.5, 6.6);
    Complex c2 = Complex.valueOf(511.5, 6.63434);
    Complex c3 = c1.clone();

    System.out.println(c1.im());
    System.out.println(c1.re());

    System.out.println(c1.plus(c2));
    System.out.println(c1.minus(c2));

    // clone == 비교는 false
    System.out.println("(c1 == c2) = " + (c1 == c2));
    System.out.println("(c1 == c3) = " + (c1 == c3));
    System.out.println("(c2 == c3) = " + (c2 == c3));

    // clone equals 비교는 true
    System.out.println("(c1.equals(c2)) = " + (c1.equals(c2)));
    System.out.println("(c1.equals(c3)) = " + (c1.equals(c3)));
    System.out.println("(c2.equals(c3)) = " + (c2.equals(c3)));

    BigBigInteger bigBigInteger = new BigBigInteger(5, 5, new SecureRandom());
    bigBigInteger.bitLength = 10;

    // 오마이갓 ~~
    System.out.println(bigBigInteger.bitLength);
    System.out.println(bigBigInteger.bitLength());
  }

  private static class BigBigInteger extends BigInteger {

    public BigBigInteger(int bitLength, int certainty, Random rnd) {
      super(bitLength, certainty, rnd);
    }

    // 상속받고 내부 필드 공개해버리기
    public int bitLength;
  }
}

/*
참조 비교 ==, 값 비교 equals
간단하지만 까묵지 말장

불변 객체는 근본적으로 thread safe, 동기화할 필요 없듬
생성 후 애플리케이션 종료시 까지 값이 절대 변하지 않으므로!!
불변 클래스는 복사해봐야 자신이랑 다를게 없으므로 clone, 복사 생성자를 제공하지 않는게 좋당

함수형 프로그래밍의 목적 자체가 외부 상태 변이(side effect)를 일으키지 않는 것이므로
필요하다면 기존 불변 객체의 값을 복사해 새로운 인스턴스로 반환해야 한다

불변 클래스에서 자주 사용되는 인스턴스를 캐싱하여 중복 방지할 수 있다
이 것이 바로바로 싱글턴, 하나만 있으면 뭐가 좋을까?
생성, GC 비용이 줄어든다

불변 객체는 실패 원자성을 제공한다
실패 원자성이 무엇인고 하니 이를테면 트랜잭션 걸고 처리하는데 에러 터져서 롤백되도 해당 인스턴스는 변하지 않는다는 것이다

불변 클래스는 어떻게 만드는가
1. setter 금지
2. 모든 필드 final (간접 접근 수정 방어)
3. 모든 필드 private (직접 접근 수정 방어, 값 수정을 막는 것 뿐만 아니라 public 은 뭐든지 공개 API 가 되버리는 것에 주의)
4. 자신 외에 내부 가변 필드 접근 방어 final List<BlahBlah> 해봤자 리스트에 재할당이 안 되고 값은 변경가능, 엄밀한 불변이 아님
5. 놓치기 쉬운 부분인데 클래스 자체를 상속하지 못하도록 만들어야 한다, 그렇지 않으면 확장해서 내부 값을 휘져어 버릴 수 있듬!!

5번을 구현하는 방법은 클래스를 final 로 선언하거나, 모든 생성자를 private 으로 만들면 된다!
요래 안 했던 예가 BigInteger, BigDecimal 인데 하위 호환성 문제로 아직까지 고치지 못 했다
특별한 이유가 없다면 객체는 불변이어야 한다
그렇지 않으면 수 많은 문제에 직면한다, 동기화 / 보안 / 성능 / 유지보수 등등등..
 */