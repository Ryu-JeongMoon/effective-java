package chap11.item83;

import chap11.item83.BasicInitialization.FieldType;

public class StaticFieldInitialization {

  public static FieldType getField() {
    return FieldHolder.field;
  }

  private static FieldType computeFieldValue() {
    return new FieldType(10);
  }

  private static class FieldHolder {

    static final FieldType field = computeFieldValue();
  }
}

/*
성능 때문에 지연 초기화를 사용하기로 했다면 LazyHolder 방식으로 접근하라

정적 필드용 지연 초기화 홀더 클래스
getField() 호출되는 순간 FieldHolder 클래스가 초기화될 것이고 이 때만 동기화되고
초기화가 끝난 후부터는 VM 이 동기화 코드를 제거하여 이후에 검사 & 동기화 없이 필드에 접근할 수 있게 된다
상수 값을 초기화하는 것이므로 초기화하는 순간에만 동기화를 걸어 해결하는 방법이다
동기화 책임을 JVM 에게 맡기고 개발자가 신경 쓰지 않아도 된다
 */