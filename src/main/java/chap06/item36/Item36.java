package chap06.item36;

/**
 * 비트 필드 대신 EnumSet 을 사용하라
 */
public class Item36 {

  public static void main(String[] args) {

  }
}

/*
비트필드 ?
고대의 방법, 비트별 OR 를 통해 하나의 집합으로 모을 수 있고 이를 비트필드라 칭한다
public static final int STYLE_BOLD = 1 << 0;
public static final int STYLE_ITALIC = 1 << 1;
public static final int STYLE_UNDERLINE = 1 << 2;
public static final int STYLE_STRIKETHROUGH = 1 << 3;

열거 타입을 사용하더라도 정수 집합을 넘겨야 할 때가 있는데 이 때도 비트필드를 넘기는 경우가 있다
상수 필드 사용 대신 EnumSet 을 넘길 수 있다는데 와닿지가 않네
어쨋든 추상화 시켜서 low-level 처리 다 해주므로 에러와 성능 걱정 없이 사용할 수 있다

EnumSet 내부적으로 비트 필드를 이용해 구현되어 있고, 원소 개수 64개 이하인 경우에는 성능 차이가 없다
복잡한 비트 구현은 EnumSet 이 전부 해놨으니 안 쓸 이유가 없음!!
 */