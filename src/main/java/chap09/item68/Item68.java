package chap09.item68;

/**
 * 일반적으로 통용되는 명명 규칙을 따르라
 */
public class Item68 {

  public static void main(String[] args) {

  }
}

/*
package, class, method, variable 명세를 따르도록 한다
외부에서 사용할 수 있도록 만든 공개 프로그램일수록 중요도는 더 커지며 쓰잘데기 없는 혼란을 주지 않도록 한다
언어에서 흔히 쓰이는 관례들은 해당 언어의 뉘앙스를 나타내는 것이고 언어의 정체성을 규정한다
자바에서는 카멜 케이스로 변수 이름을 짓는데 개발자가 스네이크 케이스를 좋아해서 막 지어버리면 ??!

메서드, 필드는 장황하더라도 의미를 명확히 전달할 수 있으면 길게 지어도 되지만
패키지는 의미가 통용되는 한 짧게 짓는 것이 좋다 ex) utilities -> util
역시 의미가 통한다면 약어로 지어도 오께이       ex) awt

객체를 생성할 수 없는 클래스는 (보통 util 관련) 복수로 짓는다
타입 변환 시에는 toType 으로 짓고 (toString)
객체의 내용을 다른 형태로 보여준다면 asType 으로 짓는다 (asList)
 */