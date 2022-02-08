package chap06.item40;

import java.util.Objects;

public class Bigram {

  private final char first;
  private final char second;

  public Bigram(char first, char second) {
    this.first = first;
    this.second = second;
  }

  // 망한 버전
//  public boolean equals(Bigram b) {
//    return b.first == first && b.second == second;
//  }
//
//  @Override
//  public int hashCode() {
//    return Objects.hash(first, second);
//  }

  // 굳 버전
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bigram bigram = (Bigram) o;
    return first == bigram.first && second == bigram.second;
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }
}

/*
메서드 시그니처가 달라 재정의가 아니라 다중 정의가 되어버린 equals
요 때 @Override 붙여놓으면 컴파일 에러로 잡힘

추상 클래스의 메서드를 구현할 때는 안 붙여도 된다지만 그래도 붙이는게 맘 편함
어차피 IDE 가 대부분 해주므로 이 악물고 없애버리는게 아니라면 냅두자
 */