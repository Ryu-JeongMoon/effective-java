package chap09.item58;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * 전통적인 for 문보다는 for-each 문을 사용하라
 */
public class Item58 {

    static Collection<Suit> suits = List.of(Suit.values());
    static Collection<Rank> ranks = List.of(Rank.values());

    public static void main(String[] args) {
        List<Card> deck = new ArrayList<>();

        // 5줄의 코드지만 썩은내가 진동한다
        for (Iterator<Suit> i = suits.iterator(); i.hasNext(); ) {
            Suit suit = i.next();
            for (Iterator<Rank> j = ranks.iterator(); j.hasNext(); ) {
                deck.add(new Card(suit, j.next()));
            }
        }

        // 으음 굿스멜
        for (Suit suit : suits) {
            for (Rank rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    enum Suit {
        CLUB, DIAMOND, HEART, SPADE
    }

    enum Rank {
        ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING
    }

    static class Card {

        private Suit suit;
        private Rank rank;

        public Card(Suit suit, Rank rank) {
            this.suit = suit;
            this.rank = rank;
        }
    }
}

 /*
 for 문을 쓰는 목적이 무엇인가
 for -> index 를 이용해 값을 늘려가면서 일정한 값까지만 한정해두고 값을 더한다던지, 뺀다던지 등 지지고 볶는 연산이 있을 수 있고
 for-each -> Collection 넣어서 그 안의 요소들로 쌈싸먹는 연산이 있을 수 있다
 그럼 for-each 안 쓰고 for 쓴 놈들 대가리 깨야할까??, 놉 그건 아님
 1. destructive filtering, 순회하면서 요소 삭제해야할 때 iterator.remove() 쓰거나 Collection.removeIf() 써야함
 2. transforming, 순회하면서 값을 변경해야 할 때
 3. parallel iteration, 여러 컬렉션을 병렬로 순회해야 할 때 ?!
 병렬 반복이란 뭘까??
  */