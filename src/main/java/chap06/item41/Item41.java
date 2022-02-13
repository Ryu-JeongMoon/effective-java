package chap06.item41;

/**
 * 정의하려는 것이 타입이라면 마커 인터페이스를 사용하라
 */
public class Item41 {

  public static void main(String[] args) {

  }
}

/*
마커 애노테이션 이전에는 이와 유사한 기능을 마커 인터페이스로 구현했다
아무 메서드도 갖지 않고 특정 속성을 가짐을 알려주는데 대표적으로 Serializable 이 있다
요놈을 구현한 클래스는 ObjectOutputStream 을 통해 쓸 수 있다고 알려준다

마커 애노테이션이 등장했다고 마커 인터페이스가 개쓰레기가 된 것은 아니다
얘는 어엿한 타입이기 때문에 구현한 놈과 아닌 놈을 구분해주고 인자로 받는 경우 컴파일 타임에 에러 잡아주기도 한다
ObjectOutputStream 은 암묵적으로 구현했을 거라 믿고 Object 로 받는데 이는 인터페이스의 장점을 살리지 못한 것
Serializable 로 받았으면 잡아줄텐데

마커 애노테이션은 거대한 애노테이션 시스템의 지원을 받는다는 장점이 있다
각각 장단점이 있는데 어떤 상황에서 애노테이션 / 인터페이스를 사용해야할까?
클래스가 아닌 경우에는 애노테이션 밖에 못 쓰고
컴파일 타임에 에러 잡아줄 수 있는 경우 인터페이스를 쓰는게 유리하다

애노테이션을 활발히 사용하는 프레임워크 (Spring, JPA) 에서는 통일성을 위해서라도 애노테이션을 사용하는 편이 좋다
 */