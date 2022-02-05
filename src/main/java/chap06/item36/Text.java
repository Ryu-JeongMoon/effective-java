package chap06.item36;

import java.util.Set;

public class Text {

  public enum Style {
    BOLD, ITALIC, UNDERLINE, STRIKETHROUGH
  }

  public void applyStyles(Set<Style> styles) {
    System.out.println("yaho");
  }
}

/*
method signature 의 parameter 를 가장 일반화된 타입으로 받아라
EnumSet<Style> 만 받더라도 Set<Style> 로 하는게 더 유연하다
프로젝트 전체를 통제하는 상황이 아니면 공개된 API 를 고치는 일은 쉽지 않다
가장 일반화된 형태로 받게 만들어두면 추후 다른 Set 구현체를 넣더라도 공개 API 에는 영향이 없다
 */