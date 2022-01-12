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
상수만 갖는 인터페이스는 안티 패턴
이렇게 쓸 이유가 있남?

enum, Constants 클래스를 이용하자
 */