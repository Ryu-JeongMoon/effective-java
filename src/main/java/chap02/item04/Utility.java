package chap02.item04;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 인스턴스화를 막으려면 private 생성자를 사용하라
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utility {

    public static final String YAHOO = "YAHOO~~";
}

// final class 로 안 해줘도 super 생성자 초기화 못 해줘서 상속이 안 된당!!