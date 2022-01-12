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
 */