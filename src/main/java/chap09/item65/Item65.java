package chap09.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

/**
 * 리플렉션보다는 인터페이스를 사용하라
 */
public class Item65 {

  public static void main(String[] args) throws ClassNotFoundException {
    Class<? extends Set<String>> cl = null;
    try {
      cl = (Class<? extends Set<String>>) Class.forName("java.util.TreeSet");
    } catch (ClassNotFoundException e) {
      System.out.println("Fatal Error");
    }

    Constructor<? extends Set<String>> cons = null;
    try {
      assert cl != null;
      cons = cl.getDeclaredConstructor();
    } catch (NoSuchMethodException e) {
      System.out.println("No Constructor Error");
    }

    Set<String> s = null;
    try {
      s = cons.newInstance();
    } catch (InvocationTargetException e) {
      System.out.println("Cannot access constructor");
    } catch (InstantiationException e) {
      System.out.println("Cannot instantiate instance");
    } catch (IllegalAccessException e) {
      System.out.println("Error");
    }

    assert s != null;
    s.addAll(List.of("TreeSet", "HashSet", "NormalSet", "YahooSet").subList(1, 4));
    System.out.println("s = " + s);
  }
}

/*
코드 분석 도구, DI 프레임워크에서는 종종 사용하지만 (JdkProxy 또한 reflection 기반)
성능이 더럽게 안 좋기 때문에 사용하지 말아야 한다
단순 성능 문제를 넘어서 코드가 장황해지고, 권한을 뛰어넘는 행위를 할 수 있기 때문에 개발자가 직접 사용하는 것은 좋지 않다
쓰잘데기 없는 Runtime Exception 터지지 않게 하려면 reflection 방어 코드도 필요하고 등등 단점이 너무 많다
 */