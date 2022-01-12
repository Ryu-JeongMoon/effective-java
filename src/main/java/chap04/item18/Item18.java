package chap04.item18;

import java.util.List;

/**
 * 상속보다는 컴포지션을 사용하라
 */
public class Item18 {

  public static void main(String[] args) {
    InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
    s.addAll(List.of("yaho", "panda", "bear"));

    // 오잉 6?!
    System.out.println("s = " + s.getAddCount());
  }
}

/*
패키지를 통제할 수 있으며 그 안에서 이뤄지는 상속은 금지해야 할 정도는 아니다
그러나 캡슐화를 깨트린다는 점에서 보자면 통제되는 패키지 안에서의 상속 역시 좋다고는 할 수 없다
캡슐화를 깨트린다는 것은 곧 외부의 변경에 쉽게 영향 받을 수 있다는 뜻

InstrumentedHashSet 에서 add, addAll method 를 사용해 구현했는데
addAll 사용하여 얻은 addCount 값이 6이 나왔다
HashSet addAll 은 내부적으로 add 를 이용해 구현했기 때문 (self-use)
사용자는 InstrumentedHashSet 을 사용하는 것 뿐인데 결과가 잘못되어 HashSet 구현까지 까봐야 하면 설계가 잘못 된거제

상속을 하기 전 질문을 하나하고 진행해보자
상속하려는 클래스 API 에 결함이 없는가?!
 */