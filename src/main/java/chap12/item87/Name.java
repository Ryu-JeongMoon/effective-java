package chap12.item87;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Name {

  private final String lastName;
  private final String firstName;
  private final String middleName;
}

/*
객체의 물리적 표현과 논리적 표현이 같다면 자바의 기본 직렬화도 오께이
그럼에도 불구하고 불변식 보장과 보안을 위해 readObject 제공해야 할 때가 많다?!
null check 땜시 인듯

private 으로 선언했더라도 문서화할 때는 주석이 필요하다
왜?! 직렬화를 통해 공개되므로
 */