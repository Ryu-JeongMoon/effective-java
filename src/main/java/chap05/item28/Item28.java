package chap05.item28;

/**
 * 배열보다는 리스트를 사용하라
 */
public class Item28 {

    public static void main(String[] args) {

        // 컴파일 에러로 안 잡힘!!, intellij 에서 인텔리센스로 Mismatched read and write of array 로 알려주긴 함
        // Object[] o1 = new Long[1];
        // o1[0] = "panda";

        // 얘는 컴파일 타임에 잡아준다
        // List<Object> o2 = new List<Long>();
        // o2.add("bear");

        // 컴파일되지 않음
        // List<String>[] stringLists = new List<String>[1];
        // List<Integer> intList = List.of(42);
        // Object[] objects = stringLists;
        // objects[0] = intList;
        // String s = stringLists[0].get(0);
    }
}

/*
배열 - covariant
공변이란? 함께 변하는 것
Sub 가 Super 의 하위 타입일 때, Sub[] 는 Super[] 의 하위 타입이 된다

Generics - invariant
불공변? 상위 타입, 하위 타입 나발이고 서로 다른 타입이다
List<Super>, List<Sub> 다른 타입

개념 상으로는 배열이 맞는 것처럼 느껴지는데 제네릭스가 맞고 배열이 틀리다
왜요?? - 컴파일 타임에 잡혀야할 에러가 런타임이 되서야 알 수 있기 때문

배열은 실체화된다, 런타임에도 자신이 담을 원소의 타입을 체크한다
제네릭은 컴파일 타임에 타입 잡아주고, 호환성 유지를 위해 런타임에 타입 정보가 소거된다

요 파트 좀 어려운데?!
 */