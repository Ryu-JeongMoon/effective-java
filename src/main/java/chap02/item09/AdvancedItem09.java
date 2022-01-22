package chap02.item09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class AdvancedItem09 {

  static int BUFFER_SIZE = 255;

  public static void main(String[] args) {
    System.out.println(firstLineOfFile("java"));

    copy("naver", "kakao");
  }

  static String firstLineOfFile(String path) {
    try (BufferedReader br = new BufferedReader(new FileReader(path))) {
      return br.readLine();
    } catch (IOException e) {
      return "PANDA";
    }
  }

  // 하나의 try() 안에 여러개 넣을 수 있구만 ??
  static void copy(String src, String dst) {
    try (InputStream in = new FileInputStream(src);
      OutputStream out = new FileOutputStream(dst)) {
      byte[] buf = new byte[BUFFER_SIZE];
      int n;
      while ((n = in.read(buf)) >= 0) {
        out.write(buf, 0, n);
      }
    } catch (IOException e) {
      System.out.println("바보 자식아");
    }
  }
}

/*
try-finally 를 중첩해서 쓰는 경우엔 첫 예외가 두번째, 세번째 예외에 의해 가려지는 경우가 생긴다
일반적인 firstLineOfFile 메서드에서 H/W 가 잘못되어 readLine() 메서드에서 예외가 터지는 경우, close() 호출 시에도 예외가 터진다
close() 예외 때문에 readLine() 예외가 가려지는 것을 의미한다
Throwable 이 제공하는 getSuppressed() 를 이용해 꺼낼 수 있는데 이럴 노력으로 try-with-resource 써라

try-with-resource 를 사용할 때는 readLine() 메서드에서 예외가 터진 경우
문제의 올바른 진단을 위해서 close() 예외를 숨기고 첫 예외만 반환한다

try-with-resource 에서도 catch 를 활용해 적절한 예외처리를 해줄 수 있다
꼭 회수해가야하는 자원을 쓰는 경우에는 try-with-resource 를 쓰자
가독성 측면에서도 좋고 예외가 묻히는 경우도 없다

try-with-resources 를 사용하기 위해서는 try() 절 안에 들어가는 자원은 AutoCloseable 을 구현해야 한다
close() 만 있는 간단한 인터페이스로 JVM 이 알아서 닫아준다

stackoverflow
When you don't use try-with-resources there's a potential pitfall called exception-masking.
When code in a try block throws an exception, and the close method in the finally also throws an exception,
the exception thrown by the try block gets lost and the exception thrown in the finally gets propagated.
This is usually unfortunate, since the exception thrown on close is something unhelpful while the useful exception is the informative one.
(So instead of seeing the SQLException that tells you which referential integrity constraint was violated,
you're shown something like BrokenPipeException where closing the resource failed.)

try-finally -> try-with-resources 왜 대체해야 하는가
가독성도 좋아지지만 이는 부차적인 장점이고 핵심은 적합한 예외를 보기 위이다
SQLException 를 봐야 정확한 문제 파악이 가능한데, close() 예외 터지면 BrokenPipeException 을 보게 된다
어차피 찾아 올라가면 알 수는 있겠지만 한 눈에 쏘옥 들어와야 문제 해결이 쉬워진다
 */