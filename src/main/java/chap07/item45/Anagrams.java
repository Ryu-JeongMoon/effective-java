package chap07.item45;

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class Anagrams {

  public static void main(String[] args) throws IOException {
    Path dictionary = Paths.get("src/main/java/chap07/item45/panda");
    int minGroupSize = new Random().nextInt(3);

    try (Stream<String> words = Files.lines(dictionary)) {
      words.collect(groupingBy(Anagrams::alphabetize))
        .values()
        .stream()
        .filter(group -> group.size() >= minGroupSize)
        .forEach(System.out::println);
    }

    primes()
      .limit(10)
      .forEach(System.out::println);
  }

  private static String alphabetize(String s) {
    char[] a = s.toCharArray();
    Arrays.sort(a);
    return new String(a);
  }

  public static Stream<BigInteger> primes() {
    return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
  }
}

/*
for-each 를 직접 쓰는 것에 비해 성능 저하가 일어나는 경우도 있다
성능이 중요한 경우에는 성능 테스트를 거친 후 결과에 따라 선택하도록 하고 스트림은 가독성을 위해 사용한다
스트림을 이용해 코드를 작성했는데 가독성이 헤쳐지는 경우에는 for 문 쓰는 것이 더 나을수도 있다
 */