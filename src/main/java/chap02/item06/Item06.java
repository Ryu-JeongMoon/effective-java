package chap02.item06;

import java.util.regex.Pattern;

/**
 * 불필요한 객체 생성을 피하라
 */
public class Item06 {

  private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD] |D?C{0,3})" + "(X[CL] |L?X{0,3})(I[XV] |V?I{0,3}$)");

  public static void main(String[] args) {
    System.out.println(isRomanNumeralAfter("XI"));
  }

  // 개어렵당
  private static boolean isRomanNumeralBefore(String s) {
    return s.matches("^(?=.)M*(C[MD] |D?C{0,3})" + "(X[CL] |L?X{0,3})(I[XV] |V?I{0,3}$)");
  }

  private static boolean isRomanNumeralAfter(String s) {
    return ROMAN.matcher(s).matches();
  }

  // 단순히 sum 을 Long 으로 캐스팅했을 뿐인데 for 문 돌 때 i가 Long 으로 캐스팅되기 땜시 성능 차이 어마무시
  // 의도치 않은 오토박싱 죽여버려
  private static long sum() {
    Long sum = 0L;
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      sum += i;
    }
    return sum;
  }
}

/*
- String.matches() 는 문자열 형태를 확인하는 메서드인데 성능이 중요한 상황에서 반복적으로 사용하기엔 적합하지 않다?!

내부에서 Pattern 인스턴스를 만드는데 정규표현식에 해당하는 유한 상태 머신(finite state machine)을 만들기 땜시 인스턴스 생성 비용이 비싸다고 한다
Pattern.compile()로 미리 만들어두고 Pattern.matchers('확인할 문자열').matches() 형태로 사용해야 한다

static 으로 선언했기 때문에 Class 가 로딩되는 시점에 Pattern 도 같이 초기화된다
사용하기도 전에 초기화되는 것이니 Lazy Initialization 으로 최적화를 시킬 수 있긴 하지만 비추
코드가 복잡해지는데 비해 성능 향상은 크지 않은 경우가 많다고 한다
 */