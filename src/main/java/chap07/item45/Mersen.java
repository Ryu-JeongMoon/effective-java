package chap07.item45;

import chap09.item58.Item58.Card;
import chap09.item58.Item58.Rank;
import chap09.item58.Item58.Suit;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mersen {

  public static void main(String[] args) throws IOException {
    primes()
      .limit(20)
      .forEach(mp -> System.out.println(mp.bitLength() + " : " + mp));

    long start = System.nanoTime();
    newDeck()
      .forEach(System.out::println);
    System.out.println("For => " + (System.nanoTime() - start));

    start = System.nanoTime();
    newDeckStreamVersion()
      .forEach(System.out::println);
    System.out.println("Stream  => " + (System.nanoTime() - start));

    // For => 12172359
    // Stream  => 6803298
  }

  public static Stream<BigInteger> primes() {
    return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
  }

  private static List<Card> newDeck() {
    List<Card> result = new ArrayList<>();
    for (Suit suit : Suit.values()) {
      for (Rank rank : Rank.values()) {
        result.add(new Card(suit, rank));
      }
    }
    return result;
  }

  private static List<Card> newDeckStreamVersion() {
    return Stream.of(Suit.values())
      .flatMap(
        suit -> Stream.of(Rank.values())
          .map(rank -> new Card(suit, rank))
      )
      .collect(Collectors.toList());
  }
}

/*
스트림과 함수형 프로그래밍 방식에 익숙하다면 스트림을 사용하는게 좋다구나
여기서는 스트림이 약 두배 정도 빠르지만 항상 스트림이 좋은건 아니다
결국 어느 방식이 본인 제외, 남이 봤을 때 이해하기 쉬운지가 젤 중요하다 이해할 수 있는 코드를 짜자
 */