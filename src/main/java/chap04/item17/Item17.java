package chap04.item17;

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
  }
}

/*
참조 비교 ==, 값 비교 equals
간단하지만 까묵지 말장

불변 객체는 근본적으로 thread safe, 동기화할 필요 없듬
함수형 프로그래밍의 목적 자체가 외부 상태 변이를 일으키지 않는 것

불변 클래스는 복사해봐야 자신이랑 다를게 없으므로 clone, 복사 생성자를 제공하지 않는게 좋당
 */