package chap08.item54;

import java.util.List;

/**
 * null 이 아닌 빈 컬렉션이나 배열을 반환하라
 */
public class Item54 {

    public static void main(String[] args) {
        Cheese cheese = new Cheese(List.of(new Cheese(null)));
        System.out.println(cheese.cheeseInStock());
    }
}

/*
null 반환 시에 클라이언트 측에서 항상 null check or defend 코드 작성해야 한다
흠.. null check 는 전파되는게 문제? null check 후 값 할당이나 예외 터트리면 아닐터인디..

Optional 도 orElseGet, orElseThrow 방어코드 짜야하지 않는가?
길이 0인 리스트나 배열 반환해도 길이 0 체크하는 방어 코드 짜야하지 않는가? 참나!
 */