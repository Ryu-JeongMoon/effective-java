package chap06.item39;

/**
 * 명명 패턴보다 애너테이션을 사용하라
 */
public class Item39 {

  public static void main(String[] args) {

  }
}

/*
특별하게 다뤄야 할 프로그램 요소에는 명명 패턴이 적용되어왔다
JUnit3 에서 test 케이스로 다뤄야 할 메서드는 testXXX 로 시작되어야 했는데 오타가 나는 경우 무시된다

오타 검증이 이루어지거나 조금 더 명시적으로 보여줘야할 필요가 있다
그래서 @Test 애너테이션이 등장
얘를 좀 살펴보면 아래 두 애노테이션이 붙어있다
애노테이션에 붙이는 애노테이션으로써 meta-annotation 이라 칭한다
@Retention(RetentionPolicy.RUNTIME) -> 애노테이션이 유지되는 범위를 알려준다
@Target(ElementType.METHOD) -> 적용 범위를 알려준다

@Test 처럼 매개변수를 받지 않고 단순히 애노테이션이 붙은 대상에
표시만 남기는 애노테이션을 marker-annotation 이라 한다
marker-annotation 을 사용하는 이유는 오타 나면 컴파일 시 잡아주고
annotation processor 로 컴파일 타임 or 런타임에 특정 작업을 처리한다

명명 패턴으로 처리할 수 있는 일은 모두 애노테이션으로 바꿀 수 있다
애노테이션 처리기 때문에 코드 양은 늘어나지만 컴파일 타임에 오류도 잡아주고 가독성도 좋은 애노테이션 사용하자
 */