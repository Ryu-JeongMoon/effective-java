package chap07.item47;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * 반환 타입으로는 스트림보다 컬렉션이 낫다
 */
public class Item47 {

    public static void main(String[] args) {
//        java: method reference not expected here
//        for (ProcessHandle ph : ProcessHandle.allProcesses()::iterator) {
//            System.out.println("yaho");
//        }

//        사용할 순 있지만 (Iterable<ProcessHandle>) 추잡하다
        for (ProcessHandle ph : (Iterable<ProcessHandle>) ProcessHandle.allProcesses()::iterator) {
            System.out.println("ph = " + ph);
        }

//        Adapter 를 사용하자
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

    private static <E> Iterable<E> iterableOf(Stream<E>stream) {
        return stream::iterator;
    }
}

/*
왜 컬렉션이 나은가?
자바 7까지는 Collection & Iterable Interface 사용해 일련의 원소를 반환했다
자바 8이 Stream 을 들고 오면서, 어떤 타입으로 반환할 지 선택하는 것이 어려워졌다

왜 어려운가?
스트림을 이용해 일련의 원소를 다룰 때 List, Map 등으로 고려하고 시작하지 않나?!

반환할 시퀀스의 크기에 따라 메모리에 올려도 될 정도라면 ArrayList, HashSet 등의 구현체로 반환해도 되지만
지나치게 큰 경우 AbstractList 를 이용해야 한다

멱집합을 구성하는 각 원소를 반환할 때 모든 원소 집합을 반환하지 말고
원소의 유무를 비트 벡터와 매핑 시켜 반환토록 한다
예를 들어 a, b, c를 반환할 때 111을 반환하면 각 자릿수에 맞게 원소가 들어있음을 표현할 수 있다
 */