package chap10.item75;

import java.util.Arrays;

/**
 * 예외의 상세 메시지에 실패 관련 정보를 담으라
 */
public class Item75 {

  public static void main(String[] args) {
    CustomIndexOutOfBoundsException customIndexOutOfBoundsException = new CustomIndexOutOfBoundsException(16, 20, 5);

    System.out.println("customIndexOutOfBoundsException.getLowerBound() = " + customIndexOutOfBoundsException.getLowerBound());
    System.out.println("customIndexOutOfBoundsException.getUpperBound() = " + customIndexOutOfBoundsException.getUpperBound());
    System.out.println("customIndexOutOfBoundsException.getIndex() = " + customIndexOutOfBoundsException.getIndex());

    System.out.println("customIndexOutOfBoundsException.getMessage() = " + customIndexOutOfBoundsException.getMessage());
    System.out.println(Arrays.toString(customIndexOutOfBoundsException.getStackTrace()));
  }
}

/*
예외의 상세 메세지를 최종 사용자에게 보여줄 오류 메세지와 혼동하지 말라
문제 해결에 필요하다면 일반적인 Exception 형태와 다르게 작성해도 된다

가능하다면 예외 메세지를 상세하게 적는 것이 좋다
CustomIndexOutOfBoundsException 예처럼 어떤 값 때문에 잘못 됐는지 알려주면 더 좋다
로그로 스택 트레이스 찍히니까 완전 자세하게 찍을 필요는 없는데
자세한 예외 정보를 넘겨주면 해결하기 쉽기 때문에 유용하다
커스텀 예외 작성 시 웬만하면 메세지만 남기지 말고 던져진 원인(cause)도 포함해서 던져주는게 나은 듯하다
 */