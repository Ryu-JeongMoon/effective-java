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
  }
}

/*
int enum pattern
- public static final int APPLE_FUJI = 0;
- public static final int APPLE_PIPPIN = 1;

- public static final int ORANGE_FUJI = 0;
- public static final int ORANGE_PIPPIN = 0;

type safety 보장할 수 없으며 혼용이 가능하다는 것이 큰 문제
APPLE_FUJI == ORANGE_FUJI => true
단순 값이기 때문


 */