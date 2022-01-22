package chap02.item09;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * try-finally 보다는 try-with-resource 를 사용하라
 */
public class Item09 {

  static int BUFFER_SIZE = 255;

  // 간단하게 쓸 때는 사용할 수 있을지도 ?!
  // 근디 인텔리센스가 친절하게도 바꾸라고 알려준다
  static String firstLineOfFile(String path) throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
      return br.readLine();
    } finally {
      br.close();
    }
  }

  // 자원을 둘 이상 사용하면 아주 더러워지는구만
  static void copy(String src, String dst) throws IOException {
    InputStream in = new FileInputStream(src);
    try {
      OutputStream out = new FileOutputStream(dst);
      try {
        byte[] buf = new byte[BUFFER_SIZE];
        int n;
        while ((n = in.read(buf)) >= 0) {
          out.write(buf, 0, n);
        }
      } finally {
        out.close();
      }
    } finally {
      in.close();
    }
  }

  // 두개의 자원을 하나의 try-finally 로 묶으면 더 간편하지 않나?
  static void simpleCopy(String src, String dst) throws IOException {
    InputStream in = new FileInputStream(src);
    OutputStream out = new FileOutputStream(dst);
    try {
      byte[] buf = new byte[BUFFER_SIZE];
      int n;
      while ((n = in.read(buf)) >= 0) {
        out.write(buf, 0, n);
      }
    } finally {
      in.close();
      out.close();
    }
  }
}
