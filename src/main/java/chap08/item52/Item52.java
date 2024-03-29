package chap08.item52;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 다중정의는 신중히 사용하라
 */
public class Item52 {

  public static void main(String[] args) {
    Collection<?>[] collections = {
      new HashSet<String>(),
      new ArrayList<Integer>(),
      new HashMap<String, String>().values()
    };

    // Collection 만 세번 나온다
    for (Collection<?> collection : collections) {
      System.out.println(CollectionClassifier.classify(collection));
    }

    for (Collection<?> collection : collections) {
      System.out.println(CollectionClassifier.classifyByType(collection));
    }
  }
}

/*
overloading 된 메서드는 어느 메서드가 호출될지는 compile time 에 결정된다
override 는 동적 선택, overloading 은 정적 선택

Overloading 은 애매함이 있으므로 ObjectOutputStream 을 참고해서 비슷한 기능을 하지만 이름을 다르게 짓는 방법이 있다
writeBoolean(boolean), writeInt(int), writeLong(long)
근데 엄밀히 보면 얘는 중복이 아닐까?
넘어오는 매개변수 타입을 일일이 instanceof 검사해서 다른 메서드를 호출하게 만드는 방법이 있을 수 있다
개귀찮겠다.. 확장성은 어따 팔아먹고..?

생성자는 이름을 다르게 지을 수 없으니 생성자를 여러개 만든다면 무조건 다중정의다
이럴 때 정적 팩토리 메서드를 사용해 혼돈을 줄인다

Collection 의 remove 메서드는 Object 타입의 인자를 받아 얘를 값으로 그냥 지워버리는데
List 에서 remove(index) 를 받아버리는 짓거리를 해서 remove 에 기본 타입의 정수를 넘기면 인덱스로 인식해서 지워버린다

아니 그럼 오버로딩은 언제 쓰나요?!
- 매개변수 타입이 근본적으로 다를 때 (서로 간 형변환이 불가능할 때, 어떤 메서드가 호출될지 런타임에 결정됨)
 */