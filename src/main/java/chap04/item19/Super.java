package chap04.item19;

public class Super {

  public Super() {
    /**
     * @implSpec
     * 여기서 부르는 놈은 무조건 Super 의 overrideMe() 가 아니다
     * context 에 따라 달라지며 하위 클래스에서 호출된 것이라면 Sub 의 overrideMe() 가 호출된다
     */
    overrideMe();
  }

  public void overrideMe() {
    System.out.println("YAHOO");
  }
}

/*
좋은 API 문서란 '어떻게'가 아닌 '무엇'을 하는지 나타나야 한다
재정의 메서드 사용 시 주의점을 나타낼 때는 이 격언을 위반하게 된다
 */