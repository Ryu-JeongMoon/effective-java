package chap08.item50;

import java.util.Date;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public final class Period {

  private final Date start;
  private final Date end;

//    public Period(Date start, Date end) {
//        if (start.compareTo(end) > 0) {
//            throw new IllegalArgumentException(start + " should be earlier than " + end);
//        }
//        this.start = start;
//        this.end = end;
//    }

  // 방어적 복사를 사용한 버전, parameter 로 들어오는 녀석에게 생성 후에는 의존하지 않는다
  public Period(Date start, Date end) {
    this.start = new Date(start.getTime());
    this.end = new Date(end.getTime());

    if (this.start.compareTo(this.end) > 0) {
      throw new IllegalArgumentException(this.start + " should be earlier than " + this.end);
    }
  }
}

/*
방어적 복사본을 만든 후에 유효성 검사한다 - 왜 ?
멀티스레딩 환경에서 유효성 검사를 하는 그 타이밍에 외부에서 값을 수정할 수도 있기 때문 !!

보안에서는 찰나를 이용하는 공격을 검사시점/사용시점 공격이라 한다
TOCTOU, time-of-check/time-of-use attack

 */