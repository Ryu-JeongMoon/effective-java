package chap04.item22;

/**
 * 인터페이스는 타입을 정의하는 용도로만 사용하라
 */
public class Item22 {

  public static void main(String[] args) {
    System.out.println(PhysicalConstants.AVOGADROS_NUMBER);
    System.out.println(PhysicalConstants.BOLTZMANN_CONST);
    System.out.println(PhysicalConstants.ELECTRON_MASS);
  }
}

/*
인터페이스는 타입 정의에만 사용해야 한다
상수만 갖는 인터페이스를 작성하고 여러 클래스에서 공통된 상수를 이용하고 싶어서 인터페이스를 구현하는 방식이 있는데 안티 패턴이다
또한 상수를 인터페이스에 넣어 사용하는 방식은 바이너리 호환성을 깨트린다
클라이언트에서 상수를 사용하지 않는 경우에도, 다음 릴리즈에서 문제가 발생할 여지를 가지고 있다

enum, Constants 클래스를 이용하자
 */