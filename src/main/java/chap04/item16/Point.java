package chap04.item16;

class Point {

  public double x;
  public double y;
}

/*
인스턴스 필드들을 모아두기 위해 사용하는 클래스가 존재한다
요런 놈은 왜 쓰는걸까?
Constant, Util 목적으로 사용하는 건가?

-> parameter 로 여러 변수를 받을 때, 관리 용이성을 위해 객체로 묶어버리는 경우가 있다
예를 들어 DTO 가 있는데 이름, 나이, 주소를 받아 Entity 생성할 수 있으나
받으려는 매개변수가 변할 때 혹은 매개변수의 이름이 변할 때 등의 상황에서 DTO 로 묶어놓으면 훨씬 관리하기 쉽다
가독성 측면에서도 DTO 하나만 받으면 되니까 더 좋아진다
 */