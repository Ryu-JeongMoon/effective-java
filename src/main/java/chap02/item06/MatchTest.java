package chap02.item06;

import java.util.regex.Pattern;

public class MatchTest {

  public static void main(String Args[]) {
    String first = "@\\{message.headers\\.?([^\\}]*)\\}";
    String second = "@\\{message.headers.wolla\\}";
    long start, end, total;
    float avg;
    int NUM_OF_ITERATIONS = 100;

    Pattern pattern = Pattern.compile(first);

    total = 0;
    start = 0;
    end = 0;
    avg = 0;

    for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
      start = System.nanoTime();
      pattern.matcher(second).matches();
      end = System.nanoTime();
      total = total + (end - start);
    }
    avg = total / NUM_OF_ITERATIONS;
    System.out.println("Duration pre compiled: " + avg);

    total = 0;
    start = 0;
    end = 0;
    avg = 0;

    for (int i = 0; i < NUM_OF_ITERATIONS; i++) {
      start = System.nanoTime();
      first.matches(second);
      end = System.nanoTime();
      total = total + (end - start);
    }
    avg = total / NUM_OF_ITERATIONS;
    System.out.println("In place compiled: " + avg);
  }
}
