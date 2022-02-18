package chap09.item60;

import java.math.BigDecimal;

/**
 * 정확한 답이 필요하다면 float, double 은 피하라
 */
public class Item60 {

  public static void main(String[] args) {
    // 0.6100000000000001
    System.out.println(1.03 - 0.42);

    calculateRemains();

    calculateRemainsByInt();

    long unsignedLong = Long.parseUnsignedLong("17916881237904312345");
    System.out.println("unsignedLong = " + unsignedLong);

    String unsignedLongString = Long.toUnsignedString(unsignedLong);
    System.out.println("unsignedLongString = " + unsignedLongString);

    System.out.println("(unsignedLong - Long.MAX_VALUE) = " + (unsignedLong - Long.MAX_VALUE));
  }

  static void calculateRemains() {
    final BigDecimal TEN_CENTS = new BigDecimal(".10");

    int itemsBought = 0;
    BigDecimal funds = new BigDecimal("1.00");
    for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
      funds = funds.subtract(price);
      itemsBought++;
    }
    System.out.println(itemsBought + "개 구입");
    System.out.println("잔돈 : " + funds);
  }

  // 소수점 없이 달러 -> 센트로 올리고 int 사용해 계산
  static void calculateRemainsByInt() {
    int itemsBought = 0;
    int funds = 100;
    for (int price = 10; funds >= price; price += 10) {
      funds -= price;
      itemsBought++;
    }
    System.out.println(itemsBought + "개 구입");
    System.out.println("잔돈 : " + funds);
  }
}

/*
float / double 은 과학 & 공학 계산용으로 설계되었다
정확성 보다는 근사치에 중점을 둔 것

BigDecimal 은 느리고 사용법이 일반 +, - 연산과 달라 불편하다
But 소수점 관리를 시스템에 맡겨버리고, 성능 저하에도 신경 쓰지 않는다면 사용하자

int -> 21억 까지 사용 가능
long -> 900경 까지 사용 가능
그 외에는 BigDecimal 사용 해야함

자바8 이상에서는 parseUnsignedLong 으로 long 범위 초과하는 수를 받을 수 있긴 하나
출력하려 하면 오버플로우 나기 때문에 음수로 출력된다, toUnsignedString 사용하여 문자열로 바꿔 출력해야 한다
매번 형태 바꿔줘야 하고 범위를 벗어난 계산 수행 시 오버플로우 나게 되니
큰 수를 다뤄야 한다면 성능 저하가 오더라도 BigInteger 사용해 안전 코딩
 */