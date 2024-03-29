package chap04.item24;

import chap04.item24.StaticMemberClass.NonStaticMember;

/**
 * 멤버 클래스는 되도록 static 으로 만들라
 */
public class Item24 {

  public static void main(String[] args) {
    StaticMemberClass staticMemberClass = new StaticMemberClass(10);
    NonStaticMember nonStaticMember = staticMemberClass.new NonStaticMember();
    nonStaticMember.bear();

    StaticMemberClass.StaticMember.panda();
  }
}

/*
정적 내부 클래스는 왜 만드는가
outer class 와 함께 쓰일 때만 의미 있는 클래스로써 private field, method 에 접근하기 위해 사용한다

nested class
- 정적 멤버 클래스
- 비정적 멤버 클래스
- 익명 클래스
- 지역 클래스

static member class & member class 차이는
정적 멤버 클래스는 외부 클래스 인스턴스와 연결되지 않지만
비정적 멤버 클래스는 외부 클래스 인스턴스와 연결됨

nested static class 는 outer class 의 private 필드, 메서드에도 접근 가능하며
정규화된 this, (ClassName.this) 형태로 바깥 클래스를 사용할 수 있다

특별한 이유가 없다면 외부 클래스와의 연결을 갖지 못하도록 static 선언을 하라
static 선언하지 않을 때 두가지 문제가 있다
1. 숨은 참조를 갖고 있기 때문에 추가적인 메모리 공간이 필요하고
2. 외부 클래스를 내부 클래스가 참조하고 있기 때문에 외부 클래스가 GC 대상에서 벗어나게 된다
 */