package chap06.item35;

public enum Ensemble {

//    유지보수하기에 끔찍한 녀석
//    SOLO, DUET, TRIO, QUARTET, QUINTET,
//    SEXTET, SEPTET, OCTET, NONET, DECTET;

  SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
  SEXTET(6), SEPTET(7), OCTET(8), NONET(9), DECTET(10);

  private final int numberOfMusicians;

  Ensemble(int numberOfMusicians) {
    this.numberOfMusicians = numberOfMusicians;
  }

  public int numberOfMusicians() {
    return numberOfMusicians;
  }
}

/*
ordinal 의 존재 의의는 EnumSet, EnumMap 다룰 때 쓰기 위함이라고 한당
 */