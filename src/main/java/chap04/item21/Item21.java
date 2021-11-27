package chap04.item21;

/**
 * 인터페이스는 구현하는 쪽을 생각해 설계하라
 */
public class Item21 {

    public static void main(String[] args) {

    }

}

/*
SynchronizedCollection
클라이언트가 제공한 객체로 락을 거는 기능 추가 제공

새롭게 추가된 removeIf 를 구현하지 않아 기본 구현을 물려 받게 되었고, 이를 사용하면 ConcurrentModificationException 발생
SynchronizedCollection 가 원래 제공하려던 목적에 맞지 않게 된다

인터페이스 설계는 신중히 ..
default method 는 만능이 아니다!
 */