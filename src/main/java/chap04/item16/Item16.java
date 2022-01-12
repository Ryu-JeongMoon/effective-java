package chap04.item16;

/**
 * public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라
 */
public class Item16 {

  public static void main(String[] args) {
    Time time = new Time(5, 35);
    System.out.println("time = " + time.hour());
    System.out.println("time = " + time.minute());
  }
}

/*
가변 필드를 직접 노출하지 마라

캡슐화의 의미를 생각해보자
구현과 사용을 분리해 사용하려는 클래스의 내부 동작을 모르더라도 사용하는데 지장이 없게 하기 위함이다
가변 필드를 노출해버리면 클래스에서 정해놓은 동작을 무시한채 값을 바꿀 수 있으므로 위험

불변 필드더라도 위험은 덜하지만 완전히 안심할 수는 없다
불변 필드는 왜죠??
불변 필드더라도 값이 아니라 참조를 가져가는 녀석이면 ex) Array, CustomObject 클라이언트 측에서 값을 바꿔버릴 수 있당
 */