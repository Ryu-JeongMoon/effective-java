package chap11.item83;

import chap11.item83.BasicInitialization.FieldType;

public class InstanceInitialization {

  private volatile FieldType field;

  private FieldType getTypeWithSingleCheck() {
    FieldType result = field;
    if (result == null) {
      result = computeFieldValue();
    }
    return result;
  }

  private FieldType getTypeWithDoubleCheck() {
    FieldType result = field;
    if (result != null) {
      return result;
    }

    synchronized (this) {
      if (field == null) {
        field = computeFieldValue();
      }
      return field;
    }
  }

  private FieldType computeFieldValue() {
    return new FieldType(15);
  }
}

/*
** item3 참고하면 DCL 에도 문제가 있음을 알 수 있다 **
double check, 더블 체크
한번은 동기화 없이, 나머지 한번은 동기화하여 체크
필드가 초기화된 후로 값을 동기화하지 않으므로 반드시 필드에 volatile 선언할 것

getTypeWithDoubleCheck 에서 result 지역 변수를 왜 사용하는가?
result 에다가 field 값을 할당해버려서 값을 단 한번만 읽음을 보장하게 해주기 때문에 성능 최적화로 사용

인스턴스 지연 초기화에서의 변종
값을 여러번 초기화해도 상관 없는 놈의 경우 두번째 락 걸고 검사가 필요 없다
따라서 single-check 만 수행하면 된다
 */