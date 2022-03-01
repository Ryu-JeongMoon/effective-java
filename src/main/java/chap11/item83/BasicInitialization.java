package chap11.item83;

public class BasicInitialization {

  // 일반적인 초기화 방법
  private final FieldType field = computeFiledValue();

  // 지연 초기화가 initialization circularity 깨트릴 위험이 있다면 synchronized 로 감싸준다
  private synchronized FieldType computeFiledValue() {
    return new FieldType(5);
  }

  record FieldType(int yaho) {

  }
}

/*
항상 일반적이고 쉬운 방법을 먼저 고려하자
대부분의 상황에서는 이렇게 생성하는 것이 최선
 */