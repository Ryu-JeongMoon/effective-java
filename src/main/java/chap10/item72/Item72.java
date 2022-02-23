package chap10.item72;

/**
 * 표준 예외를 사용하라
 */
public class Item72 {

  public static void main(String[] args) throws BlahBlahException {
    throw new BlahBlahException("YAHOO");
  }
}

/*
자신의 프로그램에서 커스텀 예외를 만들 필요가 있을 것인가
- 표준 예외에서 이미 충분한 수의 예외를 제공한다
- 표준 예외를 사용하면 다른 사람이 익히기 쉽다
- 예외 클래스가 적을수록 애플리케이션에 적재되는 양도 적어진다

표준 예외를 사용해야 하는 이유 중 가장 큰 것은 재사용이 가능하다는 점이다
이미 만들어져 있는데 왜 같은 의미의 예외를 다른 이름으로 만드는가
커스텀 예외 만들기 전 자신이 표현하고자 하는 의미를 가진 예외가 있는지 먼저 찾아보자

예외 클래스의 조상님 Throwable 이 implements Serializable 이기 때문에 모든 예외는 직렬화할 수 있다
이는 특별한 설정 없이도 예외가 전달될 수 있도록 설계자가 디자인한 것이다
만약 Serializable 구현하지 않았더라면 설정에 따라 어떤 예외가 터졌을지 알 수 없는 상황이 된다

커스텀 예외 작성 시 개발 도구에 따라 serialVersionUID 설정하지 않으면 직렬화에 주의하라는 의미로 경고가 뜨기도 한다
serialVersionUID 선언되어 있지 않으면 클래스의 기본 해쉬값을 사용한다 (JVM 에게 serialVersionUID 관리를 맡겨버림)
단 고정된 값이 아니기 때문에 버전 별로 serialVersionUID 달라질 수 있다

직렬화된 데이터의 심각한 문제는 용량이다
내부에 참조하고 있는 모든 클래스에 대한 메타정보를 가지고 있기 때문에 용량이 비대해진다
직렬화된 데이터를 그냥 Redis 에 갖다 박아버리면 대규모 시스템의 경우는 리소스 낭비가 될 수 있다
 */