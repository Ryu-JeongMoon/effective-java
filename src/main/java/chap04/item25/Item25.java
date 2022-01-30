package chap04.item25;

/**
 * 톱레벨 클래스는 한 파일에 하나만 담으라
 */
public class Item25 {

  public static void main(String[] args) {
    System.out.println(Pan.NAME + Da.NAME);
  }
}

class Pan {

  static final String NAME = "pan";
}

class Da {

  static final String NAME = "da";
}

/*
소스 파일 하나에는 톱레벨 클래스 하나만 담자
컴파일 시 꼬임이 생길 수 있다

애초에 탑레벨에는 여러 클래스 두지도 않을 뿐더러
클래스명이 겹치게 작성하지 않기 때문에 큰 신경 쓸 필요는 없다
 */