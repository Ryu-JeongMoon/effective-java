package chap08.item50;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.ToString;

@ToString
public final class Period implements Serializable {

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

  // 바깥 클래스의 인스턴스 대신 static inner class 의 인스턴스를 반환하게 한다
  // jdk14 부터 등장했고 Serialization mechanism 에 사용될 수 있음을 명시해준다
  // 안 써도 되지만 스펙 상 필요한듯?
  @Serial
  private Object writeReplace() {
    return new SerializationProxy(this);
  }

  public Date getStart() {
    return new Date(start.getTime());
  }

  public Date getEnd() {
    return new Date(end.getTime());
  }

  @Serial
  private void readObject(ObjectInputStream stream) throws InvalidObjectException {
    throw new InvalidObjectException("it needs proxy");
  }

  private static class SerializationProxy implements Serializable {

    @Serial
    private static final long serialVersionUID = 2345678910293434L;

    private final Date start;
    private final Date end;

    public SerializationProxy(Period p) {
      this.start = p.start;
      this.end = p.end;
    }

    @Serial
    private Object readResolve() {
      return new Period(start, end);
    }
  }
}

/*
방어적 복사본을 만든 후에 유효성 검사한다 - 왜?
멀티스레딩 환경에서 유효성 검사를 하는 그 타이밍에 외부에서 값을 수정할 수도 있기 때문 ?!
보안에서는 찰나를 이용하는 공격을 검사시점/사용시점 공격이라 한다
TOCTOU, time-of-check/time-of-use attack

Date 와 같이 final 이 아닌 클래스를 이용할 때
손 쉬운 복사를 위해 clone() 사용하면 악의적인 사용자는 Date 상속해서 클래스 만들고 얘를 매개변수로 넣고
Date 확장한 클래스에서 만든 악의적인 clone() 으로 우리의 애플리케이션을 조져버릴 수 있다
확장 가능한 타입을 매개변수로 받는다면 clone() 대신 직접 생성해 복사하자
 */