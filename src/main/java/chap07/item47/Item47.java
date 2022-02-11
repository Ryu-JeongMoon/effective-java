package chap07.item47;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 반환 타입으로는 스트림보다 컬렉션이 낫다
 */
public class Item47 {

  public static void main(String[] args) {
//        java: method reference not expected here
//        for (ProcessHandle ph : ProcessHandle.allProcesses()::iterator) {
//            System.out.println("yaho");
//        }

    // 사용할 순 있지만 (Iterable<ProcessHandle>) 추잡하다
    for (ProcessHandle ph : (Iterable<ProcessHandle>) ProcessHandle.allProcesses()::iterator) {
      System.out.println("ph = " + ph);
    }

    // Adapter 사용하자
    for (ProcessHandle processHandle : iterableOf(ProcessHandle.allProcesses())) {
      System.out.println("processHandle = " + processHandle);
    }

    Set<String> stringSet = new HashSet<>();
    stringSet.add("yaho1");
    stringSet.add("yaho2");
    stringSet.add("yaho3");
    stringSet.add("yaho4");

    Collection<Set<String>> sets = PowerSet.of(stringSet);
    for (Set<String> set : sets) {
      System.out.println("set = " + set);
    }
  }

  private static <E> Iterable<E> iterableOf(Stream<E> stream) {
    return stream::iterator;
  }
}

/*
왜 컬렉션이 나은가?
자바 7까지는 Collection & Iterable Interface 사용해 일련의 원소를 반환했다
자바 8이 Stream 을 들고 오면서, 어떤 타입으로 반환할 지 선택하는 것이 어려워졌다

왜 어려운가?
스트림을 이용해 일련의 원소를 다룰 때 List, Map 등으로 고려하고 시작하지 않나?!
Stream<Type> 방식으로도 반환이 가능한데 얘는 for-each 반복을 지원해주지 않는다
Iterable 인터페이스가 정의한 대로 동작하지만 Iterable 을 확장하지 않았기 때문이다

문제 해결 방법에는 여러 가지가 있을 수 있다
문제 해결 했다고 끝이 아니다, 어떤 방식이 왜 좋은지 알고 쓰자
파일을 읽어들일 때 Scanner 이용한 방식과 Files.lines() 이용하는 방식이 있는데
둘 중 Files.lines() 는 파일을 읽을 때 발생할 수 있는 문제를 해결한다는 점으로 보아 얘가 우수한 것을 알 수 있다
lines 의 상세구현으로 들어가 createFileChannelLinesStream 얘를 살펴보면 FileChannelLinesSpliterator 로 읽어들이고
StreamSupport 를 이용해 Stream 형태로 반환해준다 try-catch 로 예외도 다 잡고 처리해놨다

또한 널리 알려진 것이나 만들어진 형태가 없으면 직접 만들어 해결하는 방법도 있다
iterableOf() 가 그 예시인데 단순히 내부에서 stream::iterator 를 조지는 것 뿐인데
외부에서 사용할 때 더러운 (Iterable<ProcessHandle>) 형변환을 하지 않게끔 만드는 유용한 메서드다
이를 변환 시켜준다는 의미의 Adapter 라 하는데 재사용성을 높이기 위해 디자인 패턴을 잘 알아야 하겠다

Stream <-> Iterable 상호 호환이 완벽하지 않기 때문에 Collection 을 사용하는 편이 낫다
Collection 은 Iterable 구현하고 있고 stream() 메서드로 이미 어댑터가 구현되어 있기 때문이다
Stream 을 반환하게 되면 직접 어댑터를 작성해서 해결할 수 있는데 귀찮슴둥

반환할 시퀀스의 크기에 따라 메모리에 올려도 될 정도라면 ArrayList, HashSet 등의 구현체로 반환해도 되지만
지나치게 큰 경우 AbstractList 를 이용해야 한다

멱집합을 구성하는 각 원소를 반환할 때 모든 원소 집합을 반환하지 말고
원소의 유무를 비트 벡터와 매핑 시켜 반환토록 한다
예를 들어 a, b, c를 반환할 때 111을 반환하면 각 자릿수에 맞게 원소가 들어있음을 표현할 수 있다
 */