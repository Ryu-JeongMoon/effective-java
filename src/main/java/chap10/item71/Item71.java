package chap10.item71;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 필요 없는 검사 예외 사용은 피하라
 */
public class Item71 {

  public static void main(String[] args) throws IOException {
    List<String> filePaths = FileUtils.getFilePaths();

    // try-catch 감싼 버전
    filePaths.stream()
      .map(Paths::get)
      .forEach(path -> {
        try {
          Files.deleteIfExists(path);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });

    // custom ConsumerWrapper 만들어 예외 축약한 버전
    filePaths.stream()
      .map(Paths::get)
      .forEach(FunctionalWrapperUtils.consumeWrapper(Files::deleteIfExists));
  }
}

/*
checked exception 도 올바로 사용하면 유용하다, 도대체 언제?
예측 가능하거나 예외 상황에서 복구할 합리적인 이유가 있을 때
이외의 경우는 모두 unchecked exception 던질 것
https://stackoverflow.com/questions/27578/when-to-choose-checked-and-unchecked-exceptions

예측 가능한 상황
사용자에게 해당 메서드는 예외 발생 가능함을 알려줄 때
ex) 특정 파일을 읽으려고 존재 여부 검사하고 읽기 바로 전 다른 이가 삭제한 경우

예측 불가하지만 복구할 합리적인 이유가 있는 상황
ex) 존재하지 않는 파일을 읽으려고 할 때, 프로그램 자체에서 해결해줄 수 없음, 사용자에게 해결 책임을 넘기고 예외를 발생시킨다

checked exception 던지는 메서드는 스트림 안에서 직접 사용할 수 없다?!
스트림 안에서 직접 사용할 수 없는건 아니고 가독성 측면에서 권장하지 않는 것인듯
- 스트림 안에서 try-catch 처리
- wrapper class 만들어서 직접 처리 (FunctionalInterface, custom method 필요)

기본적으로
1. 최대한 checked exception 쓰지 않는 방향으로 작성한 후
2. 필요하다면 Optional 반환을 고민한다
3. Optional 반환으로 부족한 경우 checked exception 사용한다
4. 검사 예외를 처리할 메서드를 따로 만들거나
5. 람다에서 주로 활용되는 경우, consumerWrapper 와 같은 녀석을 활용한다
 */