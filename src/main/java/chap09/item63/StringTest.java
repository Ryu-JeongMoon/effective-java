package chap09.item63;

public class StringTest {

  // slow elapsed 791 ms
  // fast elapsed 8 ms
  // advanced-fast elapsed 1.22874 ms
  public static void main(String[] args) {
    long now = System.currentTimeMillis();
    slow();
    System.out.println("slow elapsed " + (System.currentTimeMillis() - now) + " ms");

    now = System.currentTimeMillis();
    fast();
    System.out.println("fast elapsed " + (System.currentTimeMillis() - now) + " ms");

    now = System.nanoTime();
    advancedFast();
    System.out.println("advanced-fast elapsed " + ((System.nanoTime() - now) / 1000000.0) + " ms");
  }

  private static void slow() {
    String s = "";
    for (int i = 0; i < 100000; i++) {
      s += "*";
    }
  }

  private static void fast() {
    StringBuilder s = new StringBuilder();
    for (int i = 0; i < 100000; i++) {
      s.append("*");
    }
  }

  // wow repeat 개꿀, jdk11부터 사용 가능
  private static void advancedFast() {
    StringBuilder s = new StringBuilder();
    s.append("*".repeat(100000));
  }
}
