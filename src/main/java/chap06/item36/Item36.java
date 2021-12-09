package chap06.item36;

/**
 * 비트 필드 대신 EnumSet 을 사용하라
 */
public class Item36 {

}

/*
EnumSet 내부적으로 비트 필드를 이용해 구현되어 있고, 원소 개수 64개 이하인 경우에는 성능 차이가 없다
복잡한 비트 구현은 EnumSet 이 전부 해놨으니 안 쓸 이유가 없음!!
 */