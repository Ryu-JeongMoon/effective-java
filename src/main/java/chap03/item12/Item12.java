package chap03.item12;

import chap03.item10.PhoneNumber;

/**
 * toString 을 항상 재정의하라
 */
public class Item12 {

    public static void main(String[] args) {
        PhoneNumber phoneNumber = new PhoneNumber(191, 455, 343);
        System.out.println("phoneNumber = " + phoneNumber);
    }
}

/*
기본 toString 의 반환 형식은 '클래스이름'@'클래스이름을 16진수로 변환한 해시코드' 형태
toString 의 재정의는 사람이 읽기 쉬운 형태로 반환하는 것이 목표

toString 의 오버라이드에서는
1. 포맷을 정하여 일관성 있는 사용성을 제공하거나
2. 포맷을 정하지 않고 유연성을 제공한다 (추후 변경 시 대응이 쉽다?)

디버깅과 가독성을 위해 객체의 중요 정보를 담은 toString 으로 재정의하자
포맷을 정한 경우에는 규칙에 따르고 정하지 않은 경우엔 간단하게 lombok 쓰자
 */