package chap11.item83;

/**
 * 지연 초기화는 신중히 사용하라
 */
public class Item83 {

  public static void main(String[] args) {

  }
}

/*
lazy initialization 왜 사용하는가?
- 값이 실제 필요할 때 초기화하므로 성능 최적화에 사용
- 인스턴스 초기화 중 발생할 수 있는 순환을 끊어내는 용도로 사용

지연 초기화가 무조건적으로 좋은 것은 아니다, 따라서 그 필요성이 확실히 입증되었느냐에 따라 도입해야 한다
해당 클래스의 인스턴스를 사용하는 비율은 낮으나 초기화하는데 비용이 클 경우에 유용하다
 */