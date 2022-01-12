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

    // intellisense 바꾸라고 알려준다
    Iterator<String> i = strings.iterator();
    while (i.hasNext()) {
      System.out.println(i.next());
    }

    for (String string : strings) {
      System.out.println(string);
    }
  }
}

 /*
 item15 와 취지가 비슷하다
 어떤 변수든 가능하면 최소 범위를 유지하라 가독성이 좋아지고, 오류 발생이 적어진다
 위 효과를 얻기 위해 사용할 수 있는 가장 강력하고 간단한 방법은 사용하기 바로 전에 선언하는 것
 자신이 코드를 짤 때는 모를 수 있다
 코드 작성에는 항상 남이 볼 때 어떤 모습일지 염두해두어야 한다
 잘 쓴 소설과 같이 보일지 vs 맘대로 끄적인 낙서 같이 보일지
 반복자를 직접 써야하는 경우가 아니라면 while 보다는 for 쓸 것
 while - 반복 범위 외부에서 iterator 로 변수 선언해줘야 하고 이 변수의 영향이 메서드 내부로 퍼져나갈 수 있다
 for - 반복 범위 내에서만 유효한 변수 선언되고 유효 범위가 반복문 내로 한정된다
  */