package chap07.item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

/**
 * 스트림 병렬화는 주의해서 적용하라
 */
public class Item48 {

  public static void main(String[] args) {
//         요녀석에 parallel 붙이면 아무런 결과도 출력하지 못 하면서 끝나지 않는다
//        primes()
//            .parallel()
//            .map(p -> BigInteger.TWO.pow(p.intValueExact()).subtract(BigInteger.ONE))
//            .filter(mersenne -> mersenne.isProbablePrime(50))
//            .limit(20)
//            .forEach(System.out::println);

    long pow = (long) (Math.pow(10, 8));
    long start = System.nanoTime();
    System.out.println(pi(pow));
    long end = System.nanoTime();
    System.out.println("end-start = " + (end - start) + "ns");
  }

  static long pi(long n) {
    return LongStream.rangeClosed(2, n)
      .parallel()
      .mapToObj(BigInteger::valueOf)
      .filter(i -> i.isProbablePrime(50))
      .count();
  }
}

/*
데이터 소스가 Stream.iterate 이거나 중간 연산으로 limit 을 쓰는 경우에 파이프라인 병렬화는 무력화된다
스트림의 소스가 ArrayList, HashMap, HashSet 등의 구현체의 인스턴스일 때, 배열 & int / long 범위일 때 효과가 가장 좋다
1. 데이터를 원하는 크기로 잘라 스레드에 분배하기 좋기 때문 (데이터를 잘라주는 작업은 Spliterator 가 해준다)
2. 이들은 참조 지역성이 좋다, 즉 저장된 위치들에 연속성이 있어 데이터를 가져올 때 최대한 유사한 애들까지 같이 가져온다

참조 지역성이 나쁘면 어떻게 되는가?
Disk -> Memory -> Cache 계속 퍼올려야 하고 Disk 부터 퍼올리는 작업은 개같이 느리기 때문에 CPU 는 노는 중
이 때문에 인덱스를 가지고 다닥다닥 붙어있는 배열 쓰는게 성능 상 제일 좋다

스트림 사용할 때 종단 연산의 중요성이 큰데 일단 이 놈이 있어야 연산이 끝마쳐지고
종단에서 어떤 연산을 하느냐에 따라 성능이 좌우된다
중간 연산 야무지게 해놓고 종단에서 순차적 연산을 걸면 병렬화 무력화
종단 연산에서 연산 효율이 가장 좋은 것은 결과를 줄여주는 reduce 일족 -> 그래서 빅데이터 처리 시 Map-Reduce 작업이라 하는고만
anyMatch, allMatch, noneMatch 처럼 early return 가능한 놈들도 굳임

계산이 올바르게 수행되는지, 성능이 정말 향상됐는지 파악하고 스트림 병렬화를 이용해야 한다
동시성은 어려운 문제고 성능 지표를 직접 따져보며 사용해야 하므로 잘 모르겠으면 일단 쓰지 말고
충분한 테스트를 거친 후에 사용하도록 한다
일반적으로 CPU 집중적인 연산이 많은 상황에 도입을 고려해볼만 하다

스트림 원소 수 x 원소 당 수행되는 코드 줄 => 수십만줄 이상일 때 성능 향상
데이터를 자르고 연산 처리하고 데이터를 다시 모을 때 다 오버헤드 발생하니께
스트림 병렬화를 자바 애플리케이션에서 쓸 일은 거의 없겠구먼
이런 작업은 빅데이터 처리에 특화된 툴을 이용하겄다
 */