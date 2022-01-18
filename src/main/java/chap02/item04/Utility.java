package chap02.item04;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utility {

  public static final String YAHOO = "YAHOO~~";
}

// final class 로 안 해줘도 super 생성자 초기화 못 해줘서 상속이 안 된당!!