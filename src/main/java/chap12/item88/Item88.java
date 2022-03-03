package chap12.item88;

/**
 * readObject 메서드는 방어적으로 작성하라
 */
public class Item88 {

  public static void main(String[] args) {

  }
}

/*
readObject 는 실직적으로 또 다른 public 생성자다, 따라서 validation 해주고 매개변수를 방어적으로 복사하기도 해야한다
불변 클래스를 작성할 때 가변 필드를 사용하면 방어적 복사를 위해 코드가 길어진다, 그래도 해야한다!
readObject, writeObject 만들고 유효성 검사 수행 전 방어적 복사를 해야한다
그래야 기존 값을 변경시키지 않고 유효성 검사할 수 있기 때문임둥
 */