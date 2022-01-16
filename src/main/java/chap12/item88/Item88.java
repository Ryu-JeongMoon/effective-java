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
 */