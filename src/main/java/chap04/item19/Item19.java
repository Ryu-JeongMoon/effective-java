package chap04.item19;

/**
 * 상속을 고려해 설계하고 문서화하라 그러지 않았다면 상속을 금지하라
 */
public class Item19 {

  public static void main(String[] args) {
    Sub sub = new Sub();
    sub.overrideMe();
  }
}

/*
구현 상속은 코드 재사용 목적으로는 참 매혹적이지만 구현에 얽매이게 된다는 점에서 쓰지 말아야 한다
자바의 여러가지 디자인 패턴, 설계 원칙 등은 결국 재사용성과 확장성에 초점을 둔 것들인데
코드레벨 보다 높은 차원에서의 재사용성과 확장성이다
코드 몇줄 덜 쳐보겠다고 구체 클래스 상속말고 합성을 쓰던지 인터페이스 상속하거라~~

상속용으로 설계하더라도 주의할 점이 있다
만약 super 클래스가 Serializable, Cloneable 을 구현할 시 상속을 위해
clone(), readResolve(), writeReplace() 같은 메서드는 최소 protected 로 열어두어야 한다
이전 아이템들에서 설명했듯 protected 도 엄연히 따지고 보면
상속 받은 클래스에서 재정의하여 오동작할 수 있으므로 public 과 다를 바 없는 수준이다
extends 한방으로 상속 끝~ 을 외칠게 아니라 오동작 가능성까지 같이 가져가는 것을 잊지 말자

재정의 가능 메서드의 위험성을 줄이고 싶다면
private method 인 helper method 로 재정의 가능 메서드들을 호출하는 코드를 넘기고
직접 재정의 가능 메서드를 호출하는 코드를 helper method 를 호출하도록 변경한다
일종의 wrapper 로써 self-use 를 하긴 하나 상속 받은 클래스에서 재정의 가능 메서드를 오버라이드하여
클래스 기능을 망치지 못 하도록 할 수 있다

만약 죽어도 써야겠다 싶으면 override 가능한 method 에서 self-use 를 최대한 줄여라, 그리고 문서화해놓아라
상속용으로 작성된 클래스가 아닐 경우에는 빠이널 클래스가 쉬우니까 저거 쓰고 상속 금지 때려버려
1. final class 선언
2. 생성자를 private / package-private 으로 선언 후 정적 팩터리 메서드로 인스턴스 제공 (생성자 못 쓰게 만들어라)
 */