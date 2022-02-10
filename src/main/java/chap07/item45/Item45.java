package chap07.item45;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 스트림은 주의해서 사용하라
 */
public class Item45 {

  public static void main(String[] args) throws FileNotFoundException {
    File dictionary = new File("src/main/java/chap07/item45/panda");
    int minGroupSize = 1;

    Map<String, Set<String>> groups = new HashMap<>();
    try (Scanner s = new Scanner(dictionary)) {
      while (s.hasNext()) {
        String word = s.next();
        groups.computeIfAbsent(alphabetize(word), (unused) -> new TreeSet<>()).add(word);
      }
    }

    for (Set<String> group : groups.values()) {
      if (group.size() >= minGroupSize) {
        System.out.println(group.size() + " : " + group);
      }
    }

    printWrongUseOfStream();
  }

  public static String alphabetize(String s) {
    char[] a = s.toCharArray();
    Arrays.sort(a);
    return Arrays.toString(a);
  }

  // 여기가 바로 지옥일까?
  private static void printWrongUseOfStream() {
    Path path = Paths.get("src/main/java/chap07/item45/panda");
    int minGroupSize = 1;

    try (Stream<String> words = Files.lines(path)) {
      words.collect(
          Collectors.groupingBy(
            word -> word.chars()
              .sorted()
              .collect(StringBuilder::new, (sb, c) -> sb.append((char) c), StringBuilder::append)
          )
        )
        .values().stream()
        .filter(group -> group.size() >= minGroupSize)
        .map(group -> group.size() + " : " + group)
        .forEach(System.out::println);

    } catch (IOException e) {
      System.out.println("e = " + e);
    }
  }
}

/*
Stream API 는 다량의 데이터 처리 작업을 돕고자 Java8부터 추가되었다
함수형 프로그래밍의 개념으로부터 파생되어 유한 / 무한의 데이터 흐름을 만들어낸다
무한의 데이터를 처리하기 위해 지연 연산을 사용해야 한다, 즉시 사용할 수가 없듬

Stream pipeline 은 연산의 단계를 표현한다
소스로부터 시작하는데 컬렉션이나 배열, 파일, 정규표현식 패턴 매처(얜 어떻게 쓰는고?)를 이용해 데이터 흐름을 만들 수 있고
직접 생성해도 된다 Stream.range(1..100) => 1~99 or Stream.rangeClosed(1..100) => 1~100
파이프라인은 중간 연산, 종단 연산으로 나뉘는데 데이터를 쌩으로 출력하거나 다른 파일에 쓰는 경우가 아니라면
무언가 가공이 필요한 데이터이므로 중간 연산이 들어갈 것이다
파이프라인이라는 단어에서 유추할 수 있듯이 중간 연산을 이어 붙일 수 있어야 하니 중간 연산은 연산 때리고 그 결과를 반환한다

무한 스트림을 다루려면 Lazy Evaluation, 지연 평가되어야 한다고 했는데 그 시점이 종단 연산이 호출될 때다
얘 없으면 아무 일도 안 한다, 파이프라인으로 쭉쭉 연결 시키는 모양새를 fluent API 라 한다
그렇기 때문에 짧으면 무슨 일을 하는지도 잘 파악되는데 길어질수록 for, if 문으로 풀어 쓴 형태보다 읽기 어렵다
즉 자바8에 등장했다고 해서 무조건 스트림만 써서 좋은게 아니다
풀어 쓴 형태보다 성능이 나은 상황도 많지 않다, 다만 요즘은 H/W 성능이 좋아져 무시할만한 상황이 많으니 가독성을 위해 주로 사용하고
특수한 경우에 (주로 처리해야 할 데이터가 엄청 많고 각각의 연산이 독립적일 때) 스트림을 사용하면 좋다

람다에서는 타입을 생략해버리는 경우가 많기 때문에 변수명에 특히 주의해야 한다
의미 있는 변수명을 짓지 못하면 람다 전체의 의미가 모호해진다

람다 사용시 주의점
1. 같은 스코프의 지역 변수 변경 불가 - 사이드 이펙트 만들지 않기 위함
2. return, break, continue 사용 불가
*/