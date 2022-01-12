package chap04.item25;

/**
 * 톱레벨 클래스는 한 파일에 하나만 담으라
 */
public class Item25 {

  public static void main(String[] args) {
    System.out.println(Pan.NAME + Da.NAME);
  }

  private static class Pan {

    static final String NAME = "pan";
  }

  private static class Da {

    static final String NAME = "da";
  }
}

/*
소스 파일 하나에는 톱레벨 클래스 하나만 담자
컴파일 시 꼬임이 생길 수 있다
 */