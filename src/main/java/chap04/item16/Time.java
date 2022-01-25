package chap04.item16;

public record Time(int hour, int minute) {

  private static final int HOURS_PER_DAY = 24;
  private static final int MINUTES_PER_HOUR = 60;

  public Time {
    if (hour < 0 || hour >= HOURS_PER_DAY) {
      throw new IllegalArgumentException("hour: " + hour);
    }

    if (minute < 0 || minute >= MINUTES_PER_HOUR) {
      throw new IllegalArgumentException("minute: " + minute);
    }
  }

  @Override
  public int hour() {
    return hour;
  }

  @Override
  public int minute() {
    return minute;
  }
}

/*
record 참으로 신기하구나
일반 클래스일때 필드로 선언한 녀석들이 클래스 옆자리 parameter 로 들어간당
사실 parameter 는 아니고 저게 필드 변수 선언??
코틀린하고 비슷한 느낌이넹

-> kotlin data class 와 동일하며 클래스 레벨에서 암묵적으로 final 이고 abstract 선언 불가하다
현재 클래스를 예로 들면 매개변수로 받는 hour, minute 이 final 이 아니므로 얘네는 변경 가능해버림
toString, equals, hashCode 자동 생성~!
다른 클래스를 상속 받을 수 없는데, interface 구현은 가능하다
JDK14부터 나왔고 JDK16에서 정식 스펙으로 포함되어 사실상 JDK17부터 제대로 쓰일 녀석이다
 */