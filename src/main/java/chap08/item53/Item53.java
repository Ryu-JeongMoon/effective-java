package chap08.item53;

import java.util.Arrays;

/**
 * 가변인수는 신중히 사용하라
 */
public class Item53 {

  public static void main(String[] args) {
    System.out.println(sum(1, 3, 5, 5, 3, 1));
    System.out.println(graciousSum(1, 3, 5, 5, 3, 1));

    System.out.println(sum());
    System.out.println(graciousSum(1));
  }

  static int sum(int... args) {
    // 가변인수는 들어온 인수 개수와 같은 길이의 배열을 만들고 거따가 저장한다
    System.out.println("args = " + Arrays.toString(args));

    int sum = 0;
    for (int arg : args) {
      sum += arg;
    }
    return sum;
  }

  static int graciousSum(int firstArg, int... args) {
    int sum = firstArg;
    for (int arg : args) {
      sum += arg;
    }
    return sum;
  }
}

/*
가변인수가 반드시 1개 이상이어야 할 때는 코드를 작성하는 측에서 예외 처리를 해줘야한다
if (args.length == 0)
    throw new 어쩌구익셉션

여기서 문제는 인수를 안 넘기고 호출해도 컴파일이 된다는 것과 그 결과로 예외가 터진다는 점
이를 우아하게 해결하기 위해서 인수를 두개 받는다
첫번째를 고정 인수로 받고 두번째를 가변인수로 받아서 클라이언트의 의도에 부합할 수 있듬!

단 가변인수를 가진 메서드가 호출될 때마다 배열이 새로 만들어진다는 점이 찜찜하다
이를 해결하기 위해서 overloading 사용하자
인수 몇개까지 다중정의할 것인지는 클라이언트가 몇개나 사용하는지 그 필요에 따라 달라진다
무식한 방법처럼 보이지만 성능 상 유리하니 overloading 씁시다!
 */