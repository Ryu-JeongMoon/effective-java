package chap04.item19;

import java.time.Instant;

public final class Sub extends Super {

  private final Instant instant;

  public Sub() {
    // super() 이 자식 숨어있음
    instant = Instant.now();
  }

  @Override
  public void overrideMe() {
    System.out.println("instant = " + instant);
  }
}

/*
하위 클래스의 생성자가 호출되는 시점에 super() 생성자 자동 호출,
상위 클래스에서 overrideMe() 를 호출하고 있고 이는 상위 클래스가 아닌 overriding 된 하위 클래스의 메서드를 호출한다
따라서 instant 가 할당되기 전에 호출되었으므로 처음엔 null 이 뜨고 overrideMe() 호출했을 때에는 생성이 끝난 시점이므로
Instant.now() 로 할당된 값이 출력된다

생성자에서 재정의 가능한 메서드를 호출할 시에 요런 흐름을 타고 파악해야되니께 작성자 외에는 유지보수에 부담이 간다
생성자에서 private 메서드가 아닌 public, protected 메서드 호출 지양하자
 */