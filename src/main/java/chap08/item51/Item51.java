package chap08.item51;

/**
 * 메서드 시그니처를 신중히 설계하라
 */
public class Item51 {

    public static void main(String[] args) {

    }
}

/*
언제 어디서든 이름이 가장 중요하다
서술적인 이름을 쓰되 지나치게 긴 이름을 피하고, 어렵다면 자바가 제공하는 기본 라이브러리 API 를 참고하자

매개변수 개수는 적을수록 좋다
메서드가 하나의 책임만 다 한다면 많아야 할 필요가 있을까? 없다!!
매개변수 3개 이상이라면 메서드를 쪼갤 필요가 있을 수 있다

직교성이 높다
수학에서 서로 직각을 이루며 교차한다는 뜻이다 ㄱ- 이거로구만
서로 영향을 주는 성분이 전혀 없다 / 기능이 원자적으로 잘 쪼개졌다 / 두 메서드가 겹치는 부분 없이 독립적으로 다른 기능을 수행할 수 있다
이러한 메서드를 조합하여 더 큰 기능의 API 를 사용할 수 있다
직접 덩치 큰 메서드를 만드려 하지 말고 작은 메서드 여러개 던져주고 클라이언트가 알아서 만들게 하자
단 무한정 늘리는 것만이 답은 아니다, 결국 프로그램을 사용할 클라이언트에게 초점이 맞춰줘야 한다
클라이언트 녀석이 사용하기 쉽고 어떠한 행동을 할 것이라는 기대에 부응하는 API 를 제공해야 한다, 그래서 네이밍이 중요

매개변수를 넘길 때는 확장성 측면에서 구현 클래스보다 인터페이스가 좋다
인터페이스를 사용하면 아직 구현되지 않은 클래스조차 추후에 넘길 수 있기 때문이다

boolean flag 를 넘기는 짓거리는 하지 말자
대놓고 메서드에서 여러가지 책임을 가지고 있다는 것을 홍보하는 것이며
또 확장성 측면에서 true / false 외에도 다른 경우가 필요할 때 곤란해진다
enum 을 사용해서 가독성도 챙기고 확장성도 챙기자 ~!
*/