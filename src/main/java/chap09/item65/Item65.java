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
      assert cons != null;
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
Reflection 사용의 단점으로 인해 점점 Code Generate 방식으로 옮겨가고 있다 (ByteBuddy 알아볼 것)
심각한 성능 저하가 올 수 있기 때문에 사용 시 주의해야한다
단순 성능 문제를 넘어서 코드가 장황해지고,
권한을 뛰어넘는 행위를 할 수 있기 때문에 개발자가 직접 사용하는 것은 좋지 않다
쓰잘데기 없는 Runtime Exception 터지지 않게 하려면 reflection 방어 코드도 필요하고 등등 단점이 너무 많다

Reflection 왜 사용하는가
참고 - https://stackoverflow.com/questions/37628/what-is-reflection-and-why-is-it-useful
동적으로 클래스 생성이 가능하며 메서드 호출 또한 가능하다
대표적으로 JUnit 에서 사용하며 런타임에 클래스 생성 후 테스트를 수행한다
컴파일 타임에 모르고 있는 객체를 사용하기 위함이다

모르는 객체를 왜 사용해야 하는가
주로 여러 가지 버전을 가진 라이브러리를 이용할 때 필요하다
컴파일은 제일 오래된 버전 기준으로 하고
최신 버전의 기능을 이용하는 곳에서 리플렉션으로 최신 버전 객체를 만들고 메서드 호출한다

단 아래와 같은 단점으로 사용 시 주의해야 한다
1. performance overhead
미리 컴파일된 객체를 이용하는 것이 아니므로 생성과 사용에 비용이 추가된다
2. security restrictions
컴파일 시 걸어 두었던 제약이 깨질 수 있다
3. exposure of internals
private 접근 제한자로 막아둔 것을 우회하기 때문에 내부 구현이 노출된다
 */