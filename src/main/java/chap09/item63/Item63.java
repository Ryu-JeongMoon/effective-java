package chap09.item63;

/**
 * 문자열 연결은 느리니 주의하라
 */
public class Item63 {

  public static void main(String[] args) {
    long start = System.nanoTime();
    statement();
    long end = System.nanoTime();
    // 12430500
    System.out.println("(end - start) = " + (end - start));

    start = System.nanoTime();
    statementOptimization();
    end = System.nanoTime();
    // 65691
    System.out.println("(end - start) = " + (end - start));
  }

  private static String statement() {
    String result = "";
    for (int i = 0; i < 10; i++) {
      result += i;
    }
    return result;
  }

  private static String statementOptimization() {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < 10; i++) {
      result.append(i);
    }
    return result.toString();
  }
}

/*
String 에서의 + 연산자는 가볍게 사용할 땐 상관 없지만
얘에다가 수 많은 문자를 더하기 시작하면 성능이 답도 없다

어디서 주워 들었는데 + 쓰면 컴파일 시점에 StringBuilder 로 된다고 하던데?!
- "1" + "2" + "3" 과 같이 한 줄로 선언해놓은 경우에는 StringBuilder 로 최적화
- "1" +
  "2" +
  "3" 과 같이 여러 줄에 걸쳐 작성한다면 각 줄 별로 StringBuilder 가 생성되므로 오버헤드 발생
- 반복문 내에서도 마찬가지로 한번 돌때마다 StringBuilder 생성됨

따라서 한 줄로 작성했을 때만 최적화되고 나머지는 StringBuilder 생성되버린다

statement / statementOptimization
위 실험에서는 약 189 배의 성능 차이
 */