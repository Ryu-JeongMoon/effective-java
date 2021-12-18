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
 */