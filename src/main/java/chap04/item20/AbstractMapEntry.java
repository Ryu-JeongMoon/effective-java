package chap04.item20;

import java.util.Map;
import java.util.Objects;

public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

  // 구현을 강제하도록 예외 집어던짐
  @Override
  public V setValue(V value) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof Map.Entry<?, ?> e)) {
      return false;
    }
    return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
  }

  @Override
  public String toString() {
    return getKey() + "=" + getValue();
  }
}

/*
^ 비트연산자

interface 에서 equals, hashCode, toString 과 같은 Object 의 메서드들을 재정의할 수 없다
인터페이스에서 구현은 default method 로만 가능하고, Object 의 메서드들은 public 접근 제한자를 가지고 있다
Sub 는 Super 보다 접근 제한을 줄일 수 없다는 제약이 있기 때문에 재정의를 위해서는
기본 골격이 되는 abstract class 에서 구현하여 제공한다

의미상 SkeletalBlahBlah 이 되어야 하는데 관례상 AbstractBlahBlah 가 되었다
Abstract 도 딱히 틀린건 아니니께
 */