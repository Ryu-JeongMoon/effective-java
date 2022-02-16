package chap09.item57;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 지역변수의 범위를 최소화하라
 */
public class Item57 {

  public static void main(String[] args) {
    List<String> strings = new ArrayList<>(List.of("y", "a", "h", "o"));

    // 이와 같은 반복문을 두개 쓴다고 가정했을 때,
    // 두번째 반복문에서 실수로 앞의 반복자 i 를 쓰게 되면 수행되지 않고 넘어감
    Iterator<String> i = strings.iterator();
    while (i.hasNext()) {
      System.out.println(i.next());
    }

    for (String string : strings) {
      System.out.println(string);
    }

    compareForStatement();
  }

  // n에 계산 (22756) vs j < 범위에 계산 (578185), 약 20~30배 차이 범위는 계산을 계속 하나?
  private static void compareForStatement() {
    long start = System.nanoTime();
    int sum = 0;
    for (int j = 0, n = expensiveComputation(); j < n; j++) {
      sum += j;
    }
    System.out.println(System.nanoTime() - start);

    start = System.nanoTime();
    sum = 0;
    for (int j = 0; j < expensiveComputation(); j++) {
      sum += j;
    }
    System.out.println(System.nanoTime() - start);
  }

  private static int expensiveComputation() {
    int sum = 0;
    for (int i = 0; i < 57; i++) {
      sum += i;
    }
    return sum;
  }
}

/*
item15 와 취지가 비슷하다
어떤 변수든 가능하면 최소 범위를 유지하라 가독성이 좋아지고, 오류 발생이 적어진다
위 효과를 얻기 위해 사용할 수 있는 가장 강력하고 간단한 방법은 사용하기 바로 전에 선언하는 것
C와 같이 고대의 언어에서는 변수를 제일 위쪽에 몰아 넣는 관례가 있었다고 한다
메모리 부족 시 빨리 뻑나게 하려고 그런건가?

현재의 관점에서는 매우 부적절한 관례다
H/W 의 발전으로 메모리 부족에 시달릴 염려는 많이 줄었으니 컨텍스트에 더 신경 써야한다
코드가 잘 작성된 글처럼 보이려면 문맥을 적절하게 제공해줘야한다
변수, 메서드 네이밍이 중요하고 어디에 선언했는지도 매우 중요하다
손쉬운 유지보수도 염두에 둔다면 객체지향의 역할과 책임 개념도 잘 고려하자
사용 범위에 따라 if, for, try 블록 등은 예외가 될 수 있다

자신이 코드를 짤 때는 모를 수 있다
코드 작성에는 항상 남이 볼 때 어떤 모습일지 염두해두어야 한다
잘 쓴 소설과 같이 보일지 vs 맘대로 끄적인 낙서 같이 보일지
반복자를 직접 써야하는 경우가 아니라면 while 보다는 for 쓸 것
while - 반복 범위 외부에서 iterator 로 변수 선언해줘야 하고 이 변수의 영향이 반복문 외부로 퍼져나갈 수 있다
for - 반복 범위 내에서만 유효한 변수 선언되고 유효 범위가 반복문 내로 한정된다
*/