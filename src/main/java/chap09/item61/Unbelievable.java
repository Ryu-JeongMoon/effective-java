package chap09.item61;

public class Unbelievable {

  private static Integer i;

  public static void main(String[] args) {
    if (i == 42) {
      System.out.println("OMG");
    }
  }
}

/*
박싱된 타입은 객체 타입이기 땜시 null 로 초기화되어 NPE 뿜뿜
기본 타입 <-> 박싱 타입 비교 시 보통 언박싱이 이루어짐
 */