package chap05.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * raw type 사용하지 말라
 */
public class Item26 {

  // stamps 인스턴스만 취급하려는 의도지만 제네릭을 사용하지 않기 때문에 강제되지 않는다
  private static final Collection stamps = new ArrayList();
  private static final List<Coin> coins = new ArrayList<>();

  public static void main(String[] args) {
    stamps.add(new Stamp());
    stamps.add(new Coin());

    for (Object stamp : stamps) {
      System.out.println("stamp = " + stamp);
    }

    // compile error
    // coins.add(new Stamp());

    coins.add(new Coin());
    coins.add(new Coin());

    // Generics 덕분에 형변환도 필요없다, 따봉 제네릭스
    for (Coin coin : coins) {
      System.out.println("coin = " + coin);
    }
  }
}

/*
Generics -> 타입 명시를 통해 type secure programming 목표

옛날 옛적, Collection 에 타입이고 나발이고 일단 집어넣고 unchecked call 을 바라만 봤더랬다
혹시라도 for 문을 돌며 형변환 후에 출력 & 추가 로직이 있는 경우
타입이 맞지 않으면 ClassCastException 터져버림
제일 좋은 에러는 컴파일 에러, 그래서 제네릭스 쓰는 것임둥

프로그래밍에서의 자유를 제한하는 것은 뛰어난 사람과 그렇지 않은 사람들 모두에게 일종의 제약을 걸어
성능이나 활용성을 조금 깎아먹더라도 통일성 있고 안전한 개발을 하기 위함이라고 생각했었는데
최근에 타입스크립트를 쓰며 언어를 막론하고 type safe 가 중요한 것임을 다시 한번 느낀다
개발자의 수고를 덜어주고 애매한 것보다 확실한 것이 나으니 선택한 것이겄지

자바는 호환성을 대표적 특징으로 갖고 있다
Generics 가 나오기 전 코드까지 커버해야 되므로 raw type 을 열어두고 Generics 는 소거 방식으로 운용된다
그렇담 컴파일 시점에 체크하고 사용할 때는 암묵적 형변환이 이뤄지는 것일까?!
 */