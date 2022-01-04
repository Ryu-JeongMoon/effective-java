package chap10.item70;

/**
 * 복구할 수 있는 상황에는 checked exception, 프로그래밍 오류에는 runtime exception 사용하라
 */
public class Item70 {

  public static void main(String[] args) {

  }
}

/*
- 호출하는 쪽에서 복구할 것이라 여겨지는 상황에서는 검사 예외
발생이 유력한 예외를 던지고 클라이언트가 처리하게 만드는 API

- 프로그래밍 오류를 나타낼 때는 런타임 예외
전제 조건 충족 실패, 해당 API 의 명세를 어긴 것

- Error 는 진짜 에러
자원 부족, 불변식 깨짐 등 더 이상 수행할 수 없거나 수행하면 안 되는 경우에 던져짐
개발자가 직접 Error class 상속 받아 던지는 일은 없어야 함
 */