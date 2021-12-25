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
        long start = System.currentTimeMillis();
        System.out.println(pi(pow));
        long end = System.currentTimeMillis();
        System.out.println("end-start = " + (end - start) + "ms");
    }

    static long pi(long n) {
        return LongStream.rangeClosed(2, n)
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }
}

/*
데이터 소스가 Stream.iterate 이거나 중간 연산으로 limit 을 쓰는 경우에 파이프라인 병렬화는 무력화된다
스트림의 소스가 ArrayList, HashMap, HashSet 등의 구현체의 인스턴스일 때, 배열 & int / long 범위일 때 효과가 가장 좋다
1. 데이터를 원하는 크기로 잘라 스레드에 분배하기 좋기 때문
2. 이들은 참조 지역성이 좋다, 즉 저장된 위치들에 연속성이 있어 데이터를 가져올 때 최대한 유사한 애들까지 같이 가져온다

계산이 올바르게 수행되는지, 성능이 정말 향상됐는지 파악하고 스트림 병렬화를 이용해야 한다
동시성은 어려운 문제고 성능 지표를 직접 따져보며 사용해야 하므로 잘 모르겠으면 일단 쓰지 말고
충분한 테스트를 거친 후에 사용하도록 한다
일반적으로 CPU 집중적인 연산이 많은 상황에 도입을 고려해볼만 하다
 */