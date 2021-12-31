package chap08.item56;

/**
 * 공개된 API 요소에는 항상 문서화 주석을 작성하라
 */
public class Item56 {

    public static void main(String[] args) {

    }
}

 /*
 상속용으로 설계된 클래스가 아니라면 how 가 아닌 what 을 작성하라
 메서드를 호출하기 위한 사전조건, 메서드가 하는 일, 메서드가 수행된 후 필요한 사후조건 등을 나열해야 한다
 사전조건은 @throws 태그로 unchecked exception 명시적으로 선언해둔다
 또 한가지 중요한 점은 side effect 또한 작성해두어야 한다는 점
 백그라운드 스레드를 사용한다거나 전역변수의 값을 바꾼다거나 등등
 직접 사용하는 API 라면 자주 바뀔 수 있다는 가정 하에 @params, @return 등은 생략해도 된다
 공개 API 라면 코드에서 모든 것을 파악할 수 있도록 제공해주는 것이 좋다
  */