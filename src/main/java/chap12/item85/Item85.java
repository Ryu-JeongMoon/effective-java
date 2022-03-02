package chap12.item85;

import java.util.HashSet;
import java.util.Set;

/**
 * 자바 직렬화의 대안을 찾으라
 */
public class Item85 {

  public static void main(String[] args) {

  }

  /*
  deserialize bomb, 역직렬화 폭탄 짧은 스트림을 역직렬화하는 것만으로도 DoS 공격 가능
  HashSet 인스턴스를 역직렬화하려면 원소들의 hashCode 를 계산해야 한다
  반복문에 의해 깊이가 100단계까지 만들어진다??
   */
  static byte[] bomb() {
    Set<Object> root = new HashSet<>();
    Set<Object> s1 = root;
    Set<Object> s2 = new HashSet<>();

    for (int i = 0; i < 100; i++) {
      Set<Object> t1 = new HashSet<>();
      Set<Object> t2 = new HashSet<>();
      t1.add("foo");
      s1.add(t1);
      s1.add(t2);

      s2.add(t1);
      s2.add(t2);
      s1 = t1;
      s2 = t2;
    }
    return serialize(root);
  }

  private static byte[] serialize(Set<Object> root) {
    return new byte[0];
  }
}

/*
직렬화, 역직렬화에는 심각한 보안 결함이 존재한다

직렬화란 자바에서 사용하는 객체를 전송 가능한 byte stream 으로 만드는 것인데 이 과정에서 전송 가능한 형태로만 보내면
클라이언트에서 이해할 수 없으므로 보내려는 객체 instance 의 정보 역시 byte code 로 복사해서 보낸다
객체가 어떻게 생겨먹은 것인지 전부 보내니까 받는 쪽에서 이를 역직렬화하면 모든 정보를 이용해버릴 수 있는 것

예를 들어 ObjectInputStream 의 readObject 메서드를 호출하면 객체 그래프가 역직렬화되고
classpath 안의 모든 타입의 객체를 만들어낼 수 있다?!
그 타입들 안의 모든 코드를 수행할 수 있기 때문이라고 한다
따라서 이러한 타입들의 코드 전체가 공격 범위에 들어간다

이를 해결하기 위해서는 deserialize 수행 시 입력 값에 대한 validation 을 수행해야 한다
일일이 수행하는 것은 개귀찮은 일이므로 자바 9에 등장한 ObjectInputFilter 사용하면 된다
요놈은 이전 버전과의 호환을 위해 이식 가능하도록 설계되었다
역직렬화를 수행하기 전에 Filter 를 거치게 되는데 이 때
<기본 수용> 모드에서 블랙리스트로 등록된 클래스는 거부한다
<기본 거부> 모드에서는 화이트리스트로 안전성이 증명된 클래스만 허용한다
요런 선택지가 있을 때는 기본적으로 화이트리스트로 관리하는 것이 안전하다

일반적으로 사용하는 '직렬화'란 의미와 명확히 구분해야 한다
단순하게 객체를 전송 가능한 형태로 전환하는 것을 일반적인 의미의 직렬화로 사용하고 Java Serialization 을 쓰는 것이 자바 직렬화를 의미한다
자바 직렬화 위험을 피하는 가장 좋은 방법은 안 쓰는 것이다, 뭐여 그럼 뭐 써
임의 객체 그래프를 자동으로 직렬화/역직렬화하지 않는 cross-platform structured-data-representation 써라!
JSON - readable data, designed for javascript
Protocol Buffers (protobuf) - binary data (also provide readable data by pbtxt), designed for c++
 */