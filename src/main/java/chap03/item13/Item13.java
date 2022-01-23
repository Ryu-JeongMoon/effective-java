package chap03.item13;

import chap03.item11.PhoneNumber;

/**
 * clone 재정의는 주의해서 진행하라
 */
public class Item13 {

  public static void main(String[] args) {
    PhoneNumber p1 = new PhoneNumber(123, 456, 9999);
    PhoneNumber p2 = p1.clone();

    System.out.println("(p1==p2) = " + (p1 == p2));
    System.out.println("p1 = " + p1);
    System.out.println("p2 = " + p2);
  }
}

/*
Cloneable 은 복제 가능한 클래스임을 명시하는 marker interface 인데 의도한 목적을 이뤘다고는 할 수 없다?
clone 선언된 곳이 Object 이고, protected 접근 제한자를 가지고 있기 때문

실무에서는 Cloneable 을 구현한 클래스는 clone 을 public 으로 제공하며 기능이 제대로 동작됨을 보장해야 한다

Cloneable 은 왜 존재하는가?
단순히 인스턴스를 복제하는 경우엔 clone 을 구현할 필요가 없다, 그저 인스턴스 복사 메서드를 생성하고 사용하면 그만이다
클래스 계층을 따라 super.clone() 을 호출하는 경우에 필요한 것이다

특별히 필요한 경우가 아니라면 clone 이용하는 것보다는
복사하려는 인스턴스의 값들을 parameter 로 받아 생성자나 팩터리 메서드를 이용해 반환해주는 것이 깔끔
 */