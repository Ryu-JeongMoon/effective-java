package chap12.item86;

/**
 * Serializable 을 구현할지는 신중히 결정하라
 */
public class Item86 {

  public static void main(String[] args) {

  }
}

/*
자바 직렬화를 지원하기 위해서는 implements Serializable 해주면 땡이지만 사실은 더 복잡한 일이 숨어있다
item85에서 말했듯이 객체 정보를 byte code 로 넘기므로 모든 필드, 메서드에 접근 가능하고 원격 코드까지 수행할 수 있게 된다
이 부분에서 심각한 보안 취약점으로 작용하고 최악의 경우 native code 까지 원격으로 수행된다
Serializable 구현 시 release 이후 수정이 어렵고 모든 부분을 까서 보여줬으므로 이 부분까지 API 의 일부분인 것이다
캡슐화가 깨지는 것은 덤이다

모든 직렬화된 클래스는 serialVersionUID, 고유 식별 번호를 부여 받는다
직접 지정해주지 않는다면 런타임에 SHA-1을 사용해 자동으로 클래스 안에 생성된다
이 값은 클래스 이름, 구현한 인터페이스 등 클래스의 정보를 고려해 생성된다
편의 메서드 추가, 필드 변경으로 인해 serialVersionUID 달라질 수 있고 이 값이 달라진다면 InvalidClassException 터짐
따라서 자동으로 생성되는 값에 의존한다면 호환성이 보장되지 않는다

Serializable 의 비용은 적지 않으니 새로 클래스를 작성할 시 고민하여 결정하도록 한다
일반적으로 BigInteger, Instant 와 같은 값 클래스는 구현하고, ThreadPool 처럼 동작하는 클래스는 구현하지 않는다
 */