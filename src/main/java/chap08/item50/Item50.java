package chap08.item50;

import java.time.Instant;
import java.util.Date;

/**
 * 적시에 방어적 복사본을 만들라
 */
public class Item50 {

  public static void main(String[] args) {
    Date start = new Date();
    Date end = new Date();
    Period p = new Period(start, end);

    // 매개변수로 Date 받고 Period 인스턴스 생성 후 기존 Date 값을 바꿔버린다면?
    // 자바 8 이후로는 Instant 사용하자 (LocalDateTime, ZonedDateTime 도 사용 가능)
    end.setYear(78);
    System.out.println("after changing date as parameter = " + p);

    // Instant 내부에서 변수 final 로 선언되어 있음
    Instant now = Instant.now();
    System.out.println("instant = " + now);

    // 2번째 공격, get method 를 이용함
    // 해결 -> get 에도 방어적 복사본으로 나간다
    p.getStart().setYear(55);
    p.getEnd().setYear(11);
    System.out.println("using defensive copy in getter = " + p);

    InstantPeriod instantPeriod = new InstantPeriod(Instant.now(), Instant.now());
    System.out.println("instantPeriod = " + instantPeriod);

    // record 좋당
    RecordPeriod recordPeriod = new RecordPeriod(Instant.now(), Instant.now());
    System.out.println("recordPeriod = " + recordPeriod);
    System.out.println(recordPeriod.start());
    System.out.println(recordPeriod.end());

    Date startForRecord = new Date();
    Date endForRecord = new Date(startForRecord.getTime() + 1);
    RecordDatePeriod recordDatePeriod = new RecordDatePeriod(startForRecord, endForRecord);
    System.out.println("recordDatePeriod = " + recordDatePeriod);
  }
}

/*
객체 내에서 인스턴스 변수 / 메서드를 private 으로 선언했다고 해서 값이 바뀌지 않는다는 것을 완전히 보장할 수 있는가?
- 클라이언트가 서버를 박살내려고 한다는 것을 가정하고 서버를 작성하라
- 다른 개발자의 실수로 인해 서버가 박살날 수 있다는 것을 인지하고 작성하라

Main 에서 볼 수 있듯 private 가시성을 가지더라도 getter 를 이용해 Date 반환받고 얘를 바꿔버리면 내부 변수 값이 바뀐다
이를 막기 위해서는 불변 객체를 이용하는 것이 제일 쉽고 (Date X -> Instant / LocalDateTime ... O)
하위 호환성을 반드시 고려해야하는 상황에서는 방어적 복사본을 반환토록 한다
단 이때 제 3자에 의해 확장될 수 있는 클래스라면 clone() 사용하지 말고 직접 복사하여 방어하자

Date -> LocalDateTime 으로 작성했다고 끝나는 것은 아니다
하위호환성을 지켜주는 자바는 그 댓가로 낡은 API 들이 여러 곳에 침투해있다

클라이언트로 반환할 값이 가변이라면 방어적 복사를 고려하자
단 복사 비용이 너무 비싼 경우, 클라이언트에 책임을 넘기고 이를 문서화하자
 */