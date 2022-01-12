package chap05.item26;

import java.util.ArrayList;
import java.util.List;

public class Unsafe {

  public static void main(String[] args) {
    // Integer -> String 형변환은 원래 가능한 것이지만 Generics 로 명시했기 때문에 ClassCastException 터짐
    List<String> strings = new ArrayList<>();
    unsafeAdd(strings, Integer.valueOf(42));
    String s = strings.get(0);
    System.out.println("s = " + s);

    // 얘는 raw type 이니까 잘 출력됨, but unchecked call 발생
    List rawStrings = new ArrayList();
    unsafeAdd(rawStrings, Integer.valueOf(84));
    Object o = rawStrings.get(0);
    System.out.println("o = " + o);

    // 타입 불변식을 훼손할 수 없도록 비한정적 와일드 카드 사용, compile error 발생
    // List<?> wildStrings = new ArrayList<>();
    // wildStrings.add("yaho");
  }

  private static void unsafeAdd(List list, Object o) {
    list.add(o);
  }
}
