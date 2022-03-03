package chap12.item87;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.io.Serializable;

/**
 * 커스텀 직렬화 형태를 고려해보라
 */
public class Item87 {

  public static void main(String[] args) {

  }

  public static final class StringList implements Serializable {

    private int size = 0;
    private Entry head = null;

    private static class Entry implements Serializable {

      String data;
      Entry next;
      Entry previous;
    }
  }

  public static final class ReasonableStringList implements Serializable {

    private transient int size = 0;
    private transient Entry head = null;

    // Java Object Serialization Specification 에 의해
    // 직렬화 과정에 포함되는 로직은 @Serial 붙여주라 한당
    @Serial
    private void writeObject(ObjectOutputStream s) throws IOException {
      s.defaultWriteObject();
      s.writeInt(size);

      for (Entry e = head; e != null; e = e.next)
        s.writeObject(e.data);
    }

    public final void add(String s) {
      // YAHOO~!
    }

    @Serial
    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
      s.defaultReadObject();
      int numElements = s.readInt();

      for (int i = 0; i < numElements; i++)
        add((String) s.readObject());
    }

    private static class Entry {

      String data;
      Entry next;
      Entry previous;
    }
  }
}

/*
항상 우아한 코드만 만들 수 있는 것은 아니다
비지니스에서는 항상 개발 일정을 고려해야 하고 코드 품질보다 기능 추가가 우선시 되는 일도 많다
일단 릴리즈하고 다음에 고치자는 마인드인데 보통은 문제 없는 전략이다
단 implements Serializable, 자바 직렬화 형태를 사용하면 이 형태는 변경할 수 없을지도 모른다 ?!

자바의 기본 직렬화 형태는 공개된다
StringList 를 기본 직렬화하면 노드의 양방향 연결 정보, Entry 를 모두 기록해버린다
결국 요놈들의 구현이 굳어져 버리는 것

왜냐믄 자바 직렬화는 객체의 모든 정보를 넘기기 때문에 모든 것이 노출된다 (객체 그래프, 객체 간 topology)
이로 인해 발생하는 문제들은 다음과 같다
1. 공개 API 가 현재 내부 표현 방식에 영구히 묶인다
다음 버전에서 구현을 바꾸더라도 이미 노출된 API 가 있기 때문에 기존 코드를 삭제할 수 없고
deprecated 설정을 해두어 새로운 API 사용을 권장하는 방식 밖에 없다
하위 호환을 유지하는 방식이라지만 모두가 재빠르게 새 방식을 도입하지 않기 때문에 대량의 legacy 생산한다

2. 너무 많은 공간을 차지할 수 있다
StringList 의 경우 엔트리와 연결 정보는 내부 구현에 해당하므로 직렬화 형태에 포함할 가치가 없다
그럼에도 직렬화 형태에 포함되어 쓸데 없는 부가 정보가 들어가니 디스크에 저장 / 네트워크 전송 시 추가 비용이 든다

3. 시간이 너무 많이 걸릴 수 있다
직렬화 로직은 객체 그래프와 위상에 관한 정보가 없으니 그래프를 직접 순회해야 한다

4. Stack Overflow 를 일으킬 수 있다
기본 직렬화 과정에서 그래프를 재귀 순회하는데 명확히 정해진 방식이 없고
오버플로우를 발생시키는 크기가 실행 마다 달라지는 문제가 있다

defaultWriteObject, defaultReadObject 왜 때리는가?
직렬화 명세에서 요구하기 때문
이렇게 해야 향후 요구사항이 바뀌어 필드가 추가되더라도 상호 호환성이 유지된다

긍게 물리적 & 논리적 표현이 다를 때는 필드를 transient 로 선언해서 직렬화 과정에 포함되지 않게 하고
readObject, writeObject 를 통해서 기본 구현 호출한 후에 추가 작업을 실행해서 역직렬화 했을 때
불변식을 깨트리지 않고 다시 값을 야무지게 표현할 수 있다는 것?!

상호 호환성을 위해 필수로 serialVersionUID 붙여줘야 한다
붙이지 않으면 실행 마다 컴파일러에 의해 자동 생성되어 달라지니 호환이 되지 않는다
그런데 고유할 필요는 없다공?!
serial utility 를 통해 얻은 값으로 보통 한다는 구만
 */