package chap06.item39;

public class Sample2 {

  @ExceptionTest({ArithmeticException.class, IndexOutOfBoundsException.class})
  public static void e1() {
    int i = 0;
    i /= i;
  }

  @ExceptionTest({ArithmeticException.class, NullPointerException.class})
  public static void e2() {
    int[] a = new int[0];
    int i = a[1];
  }

  @ExceptionTest({ArithmeticException.class, ArrayIndexOutOfBoundsException.class})
  public static void e3() {
  }
}

/*
java8 부터 @Repeatable 로 하나의 대상에 애노테이션을 여러번 달 수 있게 하나
원리는 동일하므로 컨테이너 애노테이션을 정의하고 @Repeatable 에서 받은 것을 매개변수로 넘겨야 한다
개 쓸데 없어 보이는데 왜 만든거지??
 */