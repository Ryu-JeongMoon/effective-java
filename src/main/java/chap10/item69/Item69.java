package chap10.item69;

/**
 * 예외는 진짜 예외 상황에만 사용하라
 */
public class Item69 {

  public static void main(String[] args) {
    int[] range = new int[]{1, 2, 3, 4, 5};

    // 요상한 코드
    try {
      for (int m : range)
        System.out.println("m = " + m);

      int i = 0, sum = 0;
      while (true)
        sum += range[i++];
    } catch (ArrayIndexOutOfBoundsException ignored) {
    }

    for (int i : range)
      System.out.println(i + " yaho");
  }
}

/*
위 코드가 왜 어설픈 최적화인가
JVM 이 배열에 접근할 때마다 bounds 를 넘는지 아닌지 검사한다
명시적인 검사를 사용하면 중복된다는 점에서 착안한 최적화이다
똑똑이 JVM 이 이미 처리해놨다, 명시적인 검사를 써놓더라도 중복 검사를 수행하지 않는다

예외를 예외 상황에서 쓰지 않았다는 점만으로도 다른 이의 혼란을 일으킬 수 있다
try-catch 문 안에서는 JVM 이 수행하는 최적화에 한계가 생긴다

설계 의도대로 사용하자
요상한 방식으로 최적화를 이루어냈더라도 읽는 이를 배려해서 작성해야 한다
다른 이가 저 코드를 보면 catch 절이 왜 들어갔는지 이해할 수 없다

최적화할 부분이 있고 아닌 부분이 있다
대부분의 웹 프로그램에서는 DB I/O 쪽에서 성능을 깎아먹는다
나머지에서는 많은 수의 객체를 요청 마다 생성하더라도 GC 성능이 좋아지면서 문제 여지가 계속 줄고있다
 */