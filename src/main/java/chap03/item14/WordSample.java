package chap03.item14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class WordSample {

  public static void main(String[] args) throws IOException {
    TreeSet<String> strings = new TreeSet<>();

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    while (n-- != 0) {
      strings.add(br.readLine());
    }

    System.out.println("strings = " + strings);
  }
}
