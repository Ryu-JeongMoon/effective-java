package chap05.item29;

/**
 * 이왕이면 제네릭 타입으로 만들라
 */
public class Item29 {

  public static void main(String[] args) {

  }
}

/*
GenericStack<E> 처럼 해두면 모든 타입을 열어둔 것이기 땜시 전부 다 가능
단 GenericStack<int> 와 같은 기본형은 되지 않는다
제네릭 타입 시스템의 근본적인 문제라고 하는데
자바는 모든 것이 객체인 언어와 다르게 성능 상 이점을 위해 기본형도 쓰니까 틀린건 아니다
필요한 경우 박싱된 타입으로 쓰거라
 */