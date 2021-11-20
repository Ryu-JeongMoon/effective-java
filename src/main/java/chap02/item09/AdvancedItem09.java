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
Throwable 이 제공하는 getSuppressed() 를 이용해 꺼낼 수 있는데 이럴 노력으로 try-with-resource 써라

try-with-resource 에서도 catch 를 활용해 적절한 예외처리를 해줄 수 있다
꼭 회수해가야하는 자원을 쓰는 경우에는 try-with-resource 를 쓰자
가독성 측면에서도 좋고 예외가 묻히는 경우도 없다
 */