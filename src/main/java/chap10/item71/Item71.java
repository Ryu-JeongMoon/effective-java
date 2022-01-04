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
checked exception 던지는 메서드는 스트림 안에서 직접 사용할 수 없다?!
스트림 안에서 직접 사용할 수 없는건 아니고 가독성 측면에서 권장하지 않는 것인듯
- 스트림 안에서 try-catch 처리
- wrapper class 만들어서 직접 처리 (FunctionalInterface, custom method 필요)
 */