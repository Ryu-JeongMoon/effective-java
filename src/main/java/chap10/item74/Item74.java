package chap10.item74;

/**
 * 메서드가 던지는 모든 예외를 문서화하라
 */
public class Item74 {

  public static void main(String[] args) {

  }
}

/*
많은 이에게 쓰이는 API 일수록 문서화는 중요하다
예외도 빠질 수 없는 요소이고 API 로 인해 발생하는 모든 예외를 알려줘야 한다
귀찮다고 해서 @throws Exception, Throwable 로 선언해버리면 다른 예외까지 집어 삼킬 수 있다

비검사 예외는 자주 바뀔수도 있지만 그래도 신경써서 문서화 해놓는다면
사용하는 이에게 발생 가능한 예외에 대해 정보를 줄 수 있고 그에 따라 사용자는 대비를 할 수 있다
 */