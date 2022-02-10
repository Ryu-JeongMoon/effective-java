package chap07.item46;

import chap07.item45.Item45;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 스트림에서는 부작용 없는 함수를 사용하라
 */
public class Item46 {

  public static void main(String[] args) {
    Path path = Paths.get("src/main/java/chap07/item45/panda");

    ConcurrentHashMap<String, Long> frequency = new ConcurrentHashMap<>();
    try (Stream<String> words = Files.lines(path)) {
      words
        .forEach(word ->
          frequency.merge(word.toLowerCase(), 1L, Long::sum)
        );
    } catch (IOException e) {
      e.printStackTrace();
    }

    frequency
      .forEach((key, value) -> System.out.println(key + " : " + value));

    getGoodExample(path)
      .forEach((key, value) -> System.out.println(key + " : " + value));

    getAlphabetizedMap(path)
      .forEach((key, value) -> System.out.println(key + " : " + value));
  }

  private static Map<String, Long> getGoodExample(Path path) {
    // 요래 선언하고 할당하니까 컴파일 에러 잡힘 Collectors.groupingBy 로 반환되는 놈이 Map 이기 때문
    // ConcurrentHashMap<String, Long> frequency = new ConcurrentHashMap<>();

    Map<String, Long> frequency = new ConcurrentHashMap<>();

    try (Stream<String> words = new Scanner(new File(path.toString())).tokens()) {
      frequency = words.collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return frequency;
  }

  private static Map<String, List<String>> getAlphabetizedMap(Path path) {
    Map<String, List<String>> result = new ConcurrentHashMap<>();

    try (Stream<String> words = Files.lines(path)) {
      result = words.collect(Collectors.groupingByConcurrent(Item45::alphabetize));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return result;
  }
}

/*
스트림의 장점이 무엇인가?
기존과 다른 API 일 뿐만 아니라 함수형 프로그래밍에 기초한 패러다임이다
표현력, 속도, 병렬성을 얻으려면 이 패러다임까지 받아들여야 한다

핵심은 계산을 일련의 변환으로 재구성하는 것이다 (transformation)
일일이 for, if 로 제어하고 전체에 때려박는 방식에서 fluent API 사용하여 물 흐르듯 연산을 표현할 수 있다

각 변환 단계는 이전 단계의 결과를 받아 처리하는 순수 함수여야 한다
오직 입력만이 결과에 영향을 주어야 한다
외부 변수를 참조하지 않고, 함수에서 다른 상태를 변경하지 말아야 한다

위에서 작성된 코드는 fluent API 로 스트림을 기가 막히게 잘 사용한 것 같아보이지만 외부 변수에 값을 저장 해버렸다
이는 코드 블록에서 짜던 방식 그대로 사용한 것인데 스트림을 사용할 때는 이러한 사고방식을 버려야 한다

forEach 연산은 스트림 계산 결과를 보고할 때만 사용, 계산하는 데에는 사용 XXX
Collectors 수집기, 축소 전략을 캡슐화한 블랙 박스라 생각하자
축소란 스트림의 원소를 객체 하나에 취합한다는 것

Collectors 개어렵둥, 복습복습
 */