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
패키지를 통제할 수 있으면 그 안에서 이뤄지는 상속은 금지할 정도는 아니다
그러나 캡슐화를 깨트린다는 점에서 보자면 통제되는 패키지 안에서의 상속 역시 좋다고는 할 수 없다
캡슐화를 깨트린다는 것은 곧 외부의 변경에 쉽게 영향 받을 수 있다는 뜻

InstrumentedHashSet 에서 add, addAll method 를 사용해 구현했는데
addAll 사용하여 얻은 addCount 값이 6이 나왔다
HashSet addAll 은 내부적으로 add 를 이용해 구현했기 때문 (self-use)
사용자는 InstrumentedHashSet 을 사용하는 것 뿐인데 결과가 잘못되어 HashSet 구현까지 까봐야 하면 설계가 잘못 된거제

대신 composition 을 통해 내부 참조로 가지고 위임 시킨다, 단점이 거의 없는 방식
단 wrapper 클래스는 SELF 문제가 있는데 참고해볼 것 (래퍼 클래스는 콜백 프레임워크와 어울리지 않는다?!)
https://stackoverflow.com/questions/28254116/wrapper-classes-are-not-suited-for-callback-frameworks

CallBack 에는 보통 this (self 참조)를 넘겨서 일종의 등록 과정을 거치는데
wrapper 로 감싼 애를 넘기는 게 아니라 감싸기 전의 객체를 넘길 때 문제가 발생한다
2번째 예제에서 잘 설명하고 있다, wrapper 를 생략하고 이전 객체를 사용하는 문제

상속을 하기 전 질문을 하나하고 진행해보자
상속하려는 클래스 API 에 결함이 없는가?!
 */