package chap12.item90;

import chap08.item50.Period;
import java.util.Date;

/**
 * 직렬화된 인스턴스 대신 직렬화 프록시 사용을 검토하라
 */
public class Item90 {

  public static void main(String[] args) {
    // Period 참조할 것
    Period period = new Period(new Date(), new Date());
  }
}

/*
implements Serializable 을 사용하는 순간, 또 다른 생성자를 사용할 수 있다는 의미이고 버그와 보안 문제가 발생할 수 있다
이를 해결하기 위해 serialization proxy pattern 을 사용할 수 있다
방어적 복사보다 약간 느리지만 그래도 더 안전!
 */