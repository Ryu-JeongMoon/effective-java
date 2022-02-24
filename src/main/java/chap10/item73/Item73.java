package chap10.item73;

/**
 * 추상화 수준에 맞는 예외를 던지라
 */
public class Item73 {

  public static void main(String[] args) {

    // Exception Translation
    try {
      throw new LowLevelException();
    } catch (LowLevelException e) {
      // Exception Chaining
      throw new HighLevelException(e);
    }
  }
}

/*
로우 레벨에서 사용하는 예외를 직접 던지게 되면 무슨 일이 발생하는가
로우 레벨 소스의 다음 버전에서 예외가 변경될 수 있다면 이는 예외를 전파함으로써 OCP 를 어긴 것이다
기존에 던져진 예외를 처리하는 방식으로 작성되어 있다면 다음 버전으로 올라가 새로운 예외가 던져졌을 때 프로그램이 깨질 수 있다
Spring Data 에서 Exception 처리하는 방식을 차용하자
Vendor 특화 Exception -> Spring Data Layer Exception 으로 바꿔서 던져준다

보는 이로 하여금 DB 에 관해 자세히 알지 못해도 표준화된 예외를 알고 있다면 어떤 부분이 문제인지 쉽게 파악 가능하게 해준다
이를 Exception Translation, 예외 번역이라 한다

저수준 예외가 디버깅에 도움이 된다면 저수준 예외를 상위 수준으로 실어 보낸다
throw new BlahBlah(e); 와 같은 형태로 던지는 것이고 이를 Exception Chaining, 예외 연쇄라 한다

저수준에서 예외가 터지지 않도록 하는 것이 최선이다
저수준으로 내려보내기 전 고수준에서 검사를 수행한 후 예외를 던지는게 낫다
사소한 예외라면 로그 찍고 API 에 전파하지 않도록 하는 것이 좋다
예를 들어 라이브러리 버전은 맞지 않지만 프로그램 수행에 지장 없는 경우?!
 */