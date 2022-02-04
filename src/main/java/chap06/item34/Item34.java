package chap06.item34;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * int 상수 대신 열거 타입을 사용하라
 */
public class Item34 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    double weightInEarth = Double.parseDouble(br.readLine());
    double mass = weightInEarth / Planet.EARTH.surfaceGravity();

    for (Planet planet : Planet.values()) {
      System.out.printf("%s에서의 무게는 %f이다\n", planet, planet.surfaceWeight(mass));
    }

    Planet mercury = Planet.valueOf("MERCURY");
    System.out.println("mercury = " + mercury);
  }
}

/*
int enum pattern
- public static final int APPLE_FUJI = 0;
- public static final int APPLE_PIPPIN = 1;

- public static final int ORANGE_FUJI = 0;
- public static final int ORANGE_PIPPIN = 0;

APPLE_FUJI == ORANGE_FUJI => true
type safety 보장할 수 없으며 단순 값이기 때문에 혼용이 가능하다는 것이 큰 문제
값을 찾아보려고 할 때도 의미 있는 값이 아닌 단순 정수로 표현되기 때문에 파악이 쉽지 않다

자바의 Enum 은 특별하다?
열거 타입 자체는 클래스이고 상수 하나당 자신의 인스턴스를 하나만 생성하여 (싱글턴으로 사용할 수 있는 이유)
public static final 로 공개하기 때문에 외부에서 건드릴 건덕지가 없어 사실상 final 이다
 */