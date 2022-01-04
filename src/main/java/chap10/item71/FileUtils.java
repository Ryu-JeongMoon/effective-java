package chap10.item71;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {

  private static final String ABSOLUTE_PATH = Paths.get("").toAbsolutePath().toString();

  public static boolean createNewFile(String filename) throws IOException {
    return new File(filename).createNewFile();
  }

  public static List<String> getFilePaths() {
    return List.of(
      ABSOLUTE_PATH + "/some/directory/1.txt",
      ABSOLUTE_PATH + "/some/directory/2.txt",
      ABSOLUTE_PATH + "/some/directory/3.txt",
      ABSOLUTE_PATH + "/some/directory/4.txt");
  }

  public static void main(String[] args) throws IOException {
    List<String> filePaths = getFilePaths();
    for (String filePath : filePaths) {
      createNewFile(filePath);
    }
  }
}

/*
파일 생성되는 디렉토리 헷갈린당
절대경로 구한 후 directory + filename 붙여서 만드는게 젤 쉽넹
 */