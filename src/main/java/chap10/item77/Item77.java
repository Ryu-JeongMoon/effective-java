package chap10.item77;

/**
 * 예외를 무시하지 말라
 */
public class Item77 {

  public static void main(String[] args) {

  }
}

/*
API 설계자가 제공하는 예외에 관한 명시를 흘려버리지 말자
try-catch 로 감싸기만 한다고 문제가 없어지는 것이 아니다
예외를 무시할 경우라도 최소한 로그는 찍어놓자, 추후 문제의 원인을 파악하기에 용이하다
대수롭지 않게 여긴 예외 때문에 프로그램이 죽을수도 있으니 예외 처리는 항상 주의할 것
 */