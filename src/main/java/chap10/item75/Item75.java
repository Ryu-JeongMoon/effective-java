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
 */